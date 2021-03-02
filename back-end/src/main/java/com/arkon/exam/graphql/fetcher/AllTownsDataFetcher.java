package com.arkon.exam.graphql.fetcher;

import org.springframework.stereotype.Component;

import com.arkon.exam.model.Town;
import com.arkon.exam.repository.TownRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Component
public class AllTownsDataFetcher implements DataFetcher<List<Town>>{

	@Autowired
    private TownRepository townRepository;

    @Override
    public List<Town> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return townRepository.findAll();
    }
}
