package com.autobidder.vehicle.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.autobidder.vehicle.entity.Vehicle;
import com.autobidder.vehicle.entity.Vehicle.VehicleCondition;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	Optional<Vehicle> findByVehicleIdentificationNumber(String vin);

	List<Vehicle> findByMakeIgnoreCase(String make);

	List<Vehicle> findByModelIgnoreCase(String model);

	List<Vehicle> findByYearBetween(Integer startYear, Integer endYear);

	List<Vehicle> findByCondition(VehicleCondition condition);

	List<Vehicle> findByIsAvailableTrue();

	List<Vehicle> findByOwnerId(Long ownerId);

	@Query("SELECT v FROM Vehicle v WHERE v.make LIKE %:keyword% OR v.model LIKE %:keyword% OR v.description LIKE %:keyword%")
	List<Vehicle> searchByKeyword(@Param("keyword") String keyword);
}