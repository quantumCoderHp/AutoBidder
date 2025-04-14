package com.autobidder.vehicle.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobidder.vehicle.dto.VehicleRequest;
import com.autobidder.vehicle.dto.VehicleResponse;
import com.autobidder.vehicle.entity.Vehicle;
import com.autobidder.vehicle.entity.Vehicle.VehicleCondition;
import com.autobidder.vehicle.repository.VehicleRepository;
import com.autobidder.vehicle.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public VehicleResponse createVehicle(VehicleRequest request) {
		// Check if VIN already exists
		vehicleRepository.findByVehicleIdentificationNumber(request.getVehicleIdentificationNumber()).ifPresent(v -> {
			throw new RuntimeException("Vehicle with this VIN already exists");
		});

		Vehicle vehicle = mapToEntity(request);
		Vehicle savedVehicle = vehicleRepository.save(vehicle);
		return mapToResponse(savedVehicle);
	}

	@Override
	public VehicleResponse getVehicleById(Long id) {
		Vehicle vehicle = vehicleRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
		return mapToResponse(vehicle);
	}

	@Override
	public VehicleResponse getVehicleByVin(String vin) {
		Vehicle vehicle = vehicleRepository.findByVehicleIdentificationNumber(vin)
				.orElseThrow(() -> new RuntimeException("Vehicle not found with VIN: " + vin));
		return mapToResponse(vehicle);
	}

	@Override
	public List<VehicleResponse> getAllVehicles() {
		return vehicleRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	@Override
	public List<VehicleResponse> getVehiclesByMake(String make) {
		return vehicleRepository.findByMakeIgnoreCase(make).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	@Override
	public List<VehicleResponse> getVehiclesByModel(String model) {
		return vehicleRepository.findByModelIgnoreCase(model).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	@Override
	public List<VehicleResponse> getVehiclesByYearRange(Integer startYear, Integer endYear) {
		return vehicleRepository.findByYearBetween(startYear, endYear).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	@Override
	public List<VehicleResponse> getVehiclesByCondition(VehicleCondition condition) {
		return vehicleRepository.findByCondition(condition).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	@Override
	public List<VehicleResponse> getAvailableVehicles() {
		return vehicleRepository.findByIsAvailableTrue().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	@Override
	public List<VehicleResponse> getVehiclesByOwnerId(Long ownerId) {
		return vehicleRepository.findByOwnerId(ownerId).stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	@Override
	public VehicleResponse updateVehicle(Long id, VehicleRequest request) {
		Vehicle vehicle = vehicleRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));

		// If VIN is being changed, check if new VIN already exists
		if (!vehicle.getVehicleIdentificationNumber().equals(request.getVehicleIdentificationNumber())) {
			vehicleRepository.findByVehicleIdentificationNumber(request.getVehicleIdentificationNumber())
					.ifPresent(v -> {
						throw new RuntimeException("Vehicle with this VIN already exists");
					});
		}

		// Update vehicle fields
		vehicle.setMake(request.getMake());
		vehicle.setModel(request.getModel());
		vehicle.setYear(request.getYear());
		vehicle.setVehicleIdentificationNumber(request.getVehicleIdentificationNumber());
		vehicle.setColor(request.getColor());
		vehicle.setDescription(request.getDescription());
		vehicle.setBasePrice(request.getBasePrice());
		vehicle.setMileage(request.getMileage());
		vehicle.setCondition(request.getCondition());
		vehicle.setFuelType(request.getFuelType());
		vehicle.setTransmissionType(request.getTransmissionType());
		vehicle.setOwnerId(request.getOwnerId());
		vehicle.setUpdatedAt(LocalDateTime.now());

		Vehicle updatedVehicle = vehicleRepository.save(vehicle);
		return mapToResponse(updatedVehicle);
	}

	@Override
	public void deleteVehicle(Long id) {
		Vehicle vehicle = vehicleRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
		vehicleRepository.delete(vehicle);
	}

	@Override
	public List<VehicleResponse> searchVehicles(String keyword) {
		return vehicleRepository.searchByKeyword(keyword).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	private Vehicle mapToEntity(VehicleRequest request) {
		return Vehicle.builder().make(request.getMake()).model(request.getModel()).year(request.getYear())
				.vehicleIdentificationNumber(request.getVehicleIdentificationNumber()).color(request.getColor())
				.description(request.getDescription()).basePrice(request.getBasePrice()).mileage(request.getMileage())
				.condition(request.getCondition()).fuelType(request.getFuelType())
				.transmissionType(request.getTransmissionType()).isAvailable(true).createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now()).ownerId(request.getOwnerId()).build();
	}

	private VehicleResponse mapToResponse(Vehicle vehicle) {
		return VehicleResponse.builder().id(vehicle.getId()).make(vehicle.getMake()).model(vehicle.getModel())
				.year(vehicle.getYear()).vehicleIdentificationNumber(vehicle.getVehicleIdentificationNumber())
				.color(vehicle.getColor()).description(vehicle.getDescription()).basePrice(vehicle.getBasePrice())
				.mileage(vehicle.getMileage()).condition(vehicle.getCondition()).fuelType(vehicle.getFuelType())
				.transmissionType(vehicle.getTransmissionType()).available(vehicle.isAvailable())
				.createdAt(vehicle.getCreatedAt()).updatedAt(vehicle.getUpdatedAt()).ownerId(vehicle.getOwnerId())
				.build();
	}
}