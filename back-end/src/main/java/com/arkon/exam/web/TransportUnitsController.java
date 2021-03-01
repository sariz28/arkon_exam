package com.arkon.exam.web;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/transport-units")
@Api(value="/transport_units", tags = "Operaciones para unidades de metrobus de la CDMX")
public class TransportUnitsController {
	
	@Autowired
	private TownRepository townRepository;

	@GetMapping
	@ApiOperation(value = "consulta de unidades", 
    notes = "Retorna una lista ")
	public String index() {
		
		for(Town town: townRepository.findAll())
			System.out.println(town.getName());
		return "listoooooooooooo :*!";
	}

}