package com.arkon.exam.web;

import org.springframework.stereotype.Component;
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
