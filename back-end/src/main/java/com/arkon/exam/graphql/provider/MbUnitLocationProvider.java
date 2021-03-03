package com.arkon.exam.graphql.provider;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.arkon.exam.graphql.fetcher.MbUnitLocationDataFetchers;

import graphql.GraphQL;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Component
public class MbUnitLocationProvider {
	
	@Autowired
	private MbUnitLocationDataFetchers mbUnitLocFetchers;
	
	@Value("classpath:mb_unit_location_schema.graphql")
    private Resource resource;
    private GraphQL graphQL;
    
    @Bean(name ="MbUnitLocationGraphQL")
    public GraphQL MbUnitLocationgraphQL() { 
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        File file = resource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(file);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, 
        																		 runtimeWiring);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();

    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
        		.scalar(ExtendedScalars.DateTime)
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allMbUnitLocations", mbUnitLocFetchers.getAllDataFetcher())
                        .dataFetcher("mbUnitLocationsById", mbUnitLocFetchers.getMbUnitLocByIdDataFetcher())
                        .dataFetcher("mbUnitLocationsByTow", mbUnitLocFetchers.getMbUnitLocByTownDataFetcher())
                        .dataFetcher("mbUnitLocationsByFilter", mbUnitLocFetchers.getMbUnitLocFilterDataFetcher()))
                .build();
    }
}
