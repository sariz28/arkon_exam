package com.arkon.exam.graphql.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.arkon.exam.graphql.fetcher.MbUnitLocationDataFetchers;

import graphql.GraphQL;
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
    	InputStream is = resource.getInputStream();
		String schemaGraphql = getLoadSchema(is);
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaGraphql);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, 
        																		 runtimeWiring);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();

    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allMbUnitLocations", mbUnitLocFetchers.getAllDataFetcher())
                        .dataFetcher("mbUnitLocationsById", mbUnitLocFetchers.getMbUnitLocByIdDataFetcher())
                        .dataFetcher("mbUnitLocationsByTow", mbUnitLocFetchers.getMbUnitLocByTownDataFetcher())
                        .dataFetcher("mbUnitLocationsByFilter", mbUnitLocFetchers.getMbUnitLocFilterDataFetcher()))
                .build();
    }
    
	private String getLoadSchema(InputStream is) throws IOException{
		
		String schema = "";
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		
		while ((line = br.readLine()) != null) 
		{
			schema = schema + line;
		}
		br.close();
		isr.close();
		is.close();
		
		return schema;
		
	}
}
