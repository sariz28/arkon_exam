package com.arkon.exam.graphql.fetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arkon.exam.model.Town;
import com.arkon.exam.repository.TownRepository;

import graphql.schema.DataFetcher;

@Component
public class TownDataFetchers {

    @Autowired
    private TownRepository townRepository;
    
    
    public DataFetcher<List<Town>> getAllDataFetcher() {
		return dataFetchingEnvironment -> {
			return townRepository.findAll();
		};
	}
    
    
    public DataFetcher<Town> getTownByIdDataFetcher() {
		return dataFetchingEnvironment -> {
			Integer id = dataFetchingEnvironment.getArgument("id");
	        return townRepository.findById(id).orElse(null);
		};
	}
    

}
