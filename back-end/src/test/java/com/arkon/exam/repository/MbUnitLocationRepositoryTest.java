package com.arkon.exam.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.arkon.exam.model.Town;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MbUnitLocationRepositoryTest {
	
	
	@Autowired
	private MbUnitLocationRepository mbUnitLocationRepository;
	
	private static final int ALL_MB_lOCATIONS = 205;
	private static final int VEHICLE_ID = 449;
	String DATE_TIME_SECOND_STRING = "yyyy-MM-dd HH:mm:ss";
	private Town town;
	
	
	@Test
    public void findAllMbUnitLocationTest() {
        assertEquals(ALL_MB_lOCATIONS, mbUnitLocationRepository.findAll().size());
        
	}
	
	@Test
    public void findMbUnitLocationByIdTest() {
        assertEquals(1, mbUnitLocationRepository.findByVehicleId(VEHICLE_ID).size());
        
	}
	
	@Test
    public void findMbUnitLocationByTowTest() {
		town = new Town(15);
        assertEquals(57, mbUnitLocationRepository.findByTown(town).size());
        
	}
	
	
	@Test
    public void findMbUnitLocationByFilterTest() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_SECOND_STRING);
		LocalDateTime parse = LocalDateTime.parse("2021-01-28 18:00:02", dateTimeFormatter);
		OffsetDateTime offsetDateTime = ZonedDateTime.of(parse, ZoneId.systemDefault()).toOffsetDateTime();
		LocalDateTime parse2 = LocalDateTime.parse("2021-01-27 18:00:02", dateTimeFormatter);
		OffsetDateTime OffsetDateTime2 = ZonedDateTime.of(parse2, ZoneId.systemDefault()).toOffsetDateTime();
		
        assertEquals(1, mbUnitLocationRepository.findByVehicleIdOrTownOrDateUpdated(449, null, offsetDateTime).size());
        assertEquals(63, mbUnitLocationRepository.findByVehicleIdOrTownOrDateUpdated(null, new Town(5), null).size());
        assertEquals(204, mbUnitLocationRepository.findByVehicleIdOrTownOrDateUpdated(null, null, OffsetDateTime2).size());
        
	}

}
