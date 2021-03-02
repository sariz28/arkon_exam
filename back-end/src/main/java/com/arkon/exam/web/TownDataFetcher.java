package com.arkon.exam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class TownDataFetcher implements DataFetcher<Town>{

    @Autowired
    private TownRepository townRepository;

    @Override
    public Town get(DataFetchingEnvironment dataFetchingEnvironment) {
        Integer id = dataFetchingEnvironment.getArgument("id");
        return townRepository.findById(id).orElse(null);
    }
}
