package com.autobidder.vehicle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autobidder.vehicle.dto.VehicleRequest;
import com.autobidder.vehicle.dto.VehicleResponse;
import com.autobidder.vehicle.entity.Vehicle.VehicleCondition;
import com.autobidder.vehicle.service.VehicleService;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@PostMapping
	public ResponseEntity<VehicleResponse> createVehicle(@RequestBody VehicleRequest request) {
		return new ResponseEntity<>(vehicleService.createVehicle(request), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<VehicleResponse> getVehicleById(@PathVariable Long id) {
		return ResponseEntity.ok(vehicleService.getVehicleById(id));
	}

	@GetMapping("/vin/{vin}")
	public ResponseEntity<VehicleResponse> getVehicleByVin(@PathVariable String vin) {
		return ResponseEntity.ok(vehicleService.getVehicleByVin(vin));
	}

	@GetMapping
	public ResponseEntity<List<VehicleResponse>> getAllVehicles() {
		return ResponseEntity.ok(vehicleService.getAllVehicles());
	}

	@GetMapping("/make/{make}")
	public ResponseEntity<List<VehicleResponse>> getVehiclesByMake(@PathVariable String make) {
		return ResponseEntity.ok(vehicleService.getVehiclesByMake(make));
	}

	@GetMapping("/model/{model}")
	public ResponseEntity<List<VehicleResponse>> getVehiclesByModel(@PathVariable String model) {
		return ResponseEntity.ok(vehicleService.getVehiclesByModel(model));
	}

	@GetMapping("/year")
	public ResponseEntity<List<VehicleResponse>> getVehiclesByYearRange(@RequestParam Integer startYear,
			@RequestParam Integer endYear) {
		return ResponseEntity.ok(vehicleService.getVehiclesByYearRange(startYear, endYear));
	}

	@GetMapping("/condition/{condition}")
	public ResponseEntity<List<VehicleResponse>> getVehiclesByCondition(@PathVariable VehicleCondition condition) {
		return ResponseEntity.ok(vehicleService.getVehiclesByCondition(condition));
	}

	@GetMapping("/available")
	public ResponseEntity<List<VehicleResponse>> getAvailableVehicles() {
		return ResponseEntity.ok(vehicleService.getAvailableVehicles());
	}

	@GetMapping("/owner/{ownerId}")
	public ResponseEntity<List<VehicleResponse>> getVehiclesByOwnerId(@PathVariable Long ownerId) {
		return ResponseEntity.ok(vehicleService.getVehiclesByOwnerId(ownerId));
	}

	@PutMapping("/{id}")
	public ResponseEntity<VehicleResponse> updateVehicle(@PathVariable Long id, @RequestBody VehicleRequest request) {
		return ResponseEntity.ok(vehicleService.updateVehicle(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
		vehicleService.deleteVehicle(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public ResponseEntity<List<VehicleResponse>> searchVehicles(@RequestParam String keyword) {
		return ResponseEntity.ok(vehicleService.searchVehicles(keyword));
	}
}