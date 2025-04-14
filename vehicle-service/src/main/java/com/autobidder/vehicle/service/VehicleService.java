package com.autobidder.vehicle.service;

import java.util.List;

import com.autobidder.vehicle.dto.VehicleRequest;
import com.autobidder.vehicle.dto.VehicleResponse;
import com.autobidder.vehicle.entity.Vehicle.VehicleCondition;

public interface VehicleService {

	VehicleResponse createVehicle(VehicleRequest request);

	VehicleResponse getVehicleById(Long id);

	VehicleResponse getVehicleByVin(String vin);

	List<VehicleResponse> getAllVehicles();

	List<VehicleResponse> getVehiclesByMake(String make);

	List<VehicleResponse> getVehiclesByModel(String model);

	List<VehicleResponse> getVehiclesByYearRange(Integer startYear, Integer endYear);

	List<VehicleResponse> getVehiclesByCondition(VehicleCondition condition);

	List<VehicleResponse> getAvailableVehicles();

	List<VehicleResponse> getVehiclesByOwnerId(Long ownerId);

	VehicleResponse updateVehicle(Long id, VehicleRequest request);

	void deleteVehicle(Long id);

	List<VehicleResponse> searchVehicles(String keyword);
}