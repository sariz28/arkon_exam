package com.arkon.exam.web;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;


@Component
public class GraphQLProvider {
		    
	    @Autowired
	    private AllTownsDataFetcher allTownsDataFetcher;
	    
	    @Autowired
	    private TownDataFetcher townDataFetcher;
	    
	    @Value("classpath:town_schema.graphql")
	    private Resource resource;
	    private GraphQL graphQL;
	    
	    @Bean
	    public GraphQL graphQL() { 
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
	                .type("Query", typeWiring -> typeWiring
	                        .dataFetcher("allTowns", allTownsDataFetcher)
	                        .dataFetcher("town", townDataFetcher))
	                .build();
	    }

}
