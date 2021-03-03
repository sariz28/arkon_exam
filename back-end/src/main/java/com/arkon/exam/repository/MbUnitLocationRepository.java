package com.arkon.exam.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arkon.exam.model.MbUnitLocation;
import com.arkon.exam.model.Town;



public interface MbUnitLocationRepository extends JpaRepository<MbUnitLocation, Integer>{
	
	List <MbUnitLocation> findByVehicleId(Integer vehicleId);
	List <MbUnitLocation> findByTown(Town town);
	List <MbUnitLocation> findByVehicleIdOrTownOrDateUpdated(Integer vehicleId,
															 Town town,
															 OffsetDateTime dateUpdated);

}
