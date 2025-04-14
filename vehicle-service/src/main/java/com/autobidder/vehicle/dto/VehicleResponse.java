package com.autobidder.vehicle.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.autobidder.vehicle.entity.Vehicle.FuelType;
import com.autobidder.vehicle.entity.Vehicle.TransmissionType;
import com.autobidder.vehicle.entity.Vehicle.VehicleCondition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleResponse {
	private Long id;
	private String make;
	private String model;
	private Integer year;
	private String vehicleIdentificationNumber;
	private String color;
	private String description;
	private BigDecimal basePrice;
	private Integer mileage;
	private VehicleCondition condition;
	private FuelType fuelType;
	private TransmissionType transmissionType;
	private boolean available;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Long ownerId;
}