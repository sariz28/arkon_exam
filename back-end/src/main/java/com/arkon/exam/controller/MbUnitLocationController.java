package com.arkon.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import graphql.ExecutionResult;
import graphql.GraphQL;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mb-unit-location")
@Api(value="/mb-unit-location", tags = "Metrobus unit location Service")
public class MbUnitLocationController {
	
	@Autowired
	@Qualifier(value = "MbUnitLocationGraphQL")
	private GraphQL mbUnitLocationGraphQL;
	
	
	@PostMapping
	@ApiOperation(value = "Provides metrobus units locations data by Graphql")
	public ResponseEntity<Object> tonws(@RequestBody String query) {
		
		ExecutionResult excecute = mbUnitLocationGraphQL.execute(query);
		
		return new ResponseEntity<>(excecute, HttpStatus.OK);
	}
	
	
}
