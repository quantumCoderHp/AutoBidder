package com.autobidder.vehicle.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String make;

	@Column(nullable = false)
	private String model;

	@Column(nullable = false)
	private Integer year;

	@Column(name = "vin", nullable = false, unique = true)
	private String vehicleIdentificationNumber;

	@Column(nullable = false)
	private String color;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(nullable = false)
	private BigDecimal basePrice;

	@Column(nullable = false)
	private Integer mileage;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private VehicleCondition condition;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private FuelType fuelType;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TransmissionType transmissionType;

	@Column(name = "is_available", nullable = false)
	private boolean isAvailable = true;

	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(name = "updated_at")
	private LocalDateTime updatedAt = LocalDateTime.now();

	@Column(name = "owner_id")
	private Long ownerId;

	public enum VehicleCondition {
		NEW, EXCELLENT, GOOD, FAIR, POOR
	}

	public enum FuelType {
		GASOLINE, DIESEL, ELECTRIC, HYBRID, PLUG_IN_HYBRID, OTHER
	}

	public enum TransmissionType {
		AUTOMATIC, MANUAL, SEMI_AUTOMATIC, CVT
	}
}