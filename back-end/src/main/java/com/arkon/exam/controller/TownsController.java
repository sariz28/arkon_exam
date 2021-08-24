package com.arkon.exam.controller;

import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import graphql.GraphQL;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/towns")
@Api(value="/towns", tags = "Towns Service")
public class TownsController {
	
	@Autowired
	@Qualifier(value = "townGraphQL")
	private GraphQL graphQL;

	@PostMapping
	@ApiOperation(value = "Provides boroughs data by Graphql")
	public ResponseEntity<Object> tonws(@RequestBody String query) {
	
		ExecutionResult excecute = graphQL.execute(query);
		
		return new ResponseEntity<>(excecute, HttpStatus.OK);
	}

}