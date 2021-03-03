package com.arkon.exam.graphql.fetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arkon.exam.dto.MbUnitLocationFilterDto;
import com.arkon.exam.model.MbUnitLocation;
import com.arkon.exam.model.Town;
import com.arkon.exam.repository.MbUnitLocationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import graphql.schema.DataFetcher;

@Component
public class MbUnitLocationDataFetchers{

	@Autowired
	private MbUnitLocationRepository mbUnitLocationRepository;
	
	private final ObjectMapper objectMapper = new ObjectMapper();


	public DataFetcher<List<MbUnitLocation>> getAllDataFetcher() {
		return dataFetchingEnvironment -> {
			return mbUnitLocationRepository.findAll();
		};
	}
	
	public DataFetcher<List<MbUnitLocation>> getMbUnitLocByIdDataFetcher() {
		return dataFetchingEnvironment -> {
			Integer vehicleId = dataFetchingEnvironment.getArgument("vehicleId");
			return mbUnitLocationRepository.findByVehicleId(vehicleId);
		};
	}
	
	public DataFetcher<List<MbUnitLocation>> getMbUnitLocByTownDataFetcher() {
		return dataFetchingEnvironment -> {
			Integer townId = dataFetchingEnvironment.getArgument("townId");
			return mbUnitLocationRepository.findByTown(new Town(townId));
		};
	}
	
	public DataFetcher<List<MbUnitLocation>> getMbUnitLocFilterDataFetcher() {
		return dataFetchingEnvironment -> {
			Object  mbUnitLocationFilter = dataFetchingEnvironment.getArgument("filter");
			MbUnitLocationFilterDto inputFilter = objectMapper.convertValue(mbUnitLocationFilter, 
																			MbUnitLocationFilterDto.class);
			
			return mbUnitLocationRepository.findByVehicleIdOrTownOrDateUpdated(inputFilter.getVehicleId(),
																	           inputFilter.getTown(),
																	           inputFilter.getDateUpdated());
		};
	}
}
