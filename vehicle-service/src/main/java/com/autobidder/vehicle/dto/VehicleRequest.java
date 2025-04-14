package com.autobidder.vehicle.dto;

import java.math.BigDecimal;

import com.autobidder.vehicle.entity.Vehicle.FuelType;
import com.autobidder.vehicle.entity.Vehicle.TransmissionType;
import com.autobidder.vehicle.entity.Vehicle.VehicleCondition;

import lombok.Data;

@Data
public class VehicleRequest {
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
	private Long ownerId;
}