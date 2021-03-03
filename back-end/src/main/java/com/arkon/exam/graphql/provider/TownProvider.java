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
import com.arkon.exam.graphql.fetcher.TownDataFetchers;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;


@Component
public class TownProvider {

	@Autowired
	private TownDataFetchers townDataFetchers;

	private GraphQL graphQL;

	@Value(value = "classpath:town_schema.graphql")
	private Resource resouce;


	@Bean(name = "townGraphQL")
	public GraphQL townGraphQL() { 
		return graphQL;
	}

	@PostConstruct
	public void init() throws IOException {
		InputStream is = resouce.getInputStream();
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
						.dataFetcher("allTowns", townDataFetchers.getAllDataFetcher())
						.dataFetcher("town", townDataFetchers.getTownByIdDataFetcher()))
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
