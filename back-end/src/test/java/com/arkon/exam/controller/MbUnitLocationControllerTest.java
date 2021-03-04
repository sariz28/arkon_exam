package com.arkon.exam.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MbUnitLocationControllerTest {
	
	
	private static final Logger logger = LoggerFactory.getLogger(MbUnitLocationControllerTest.class);
	private static final String API_ROOT_PAT = "/mb-unit-location";
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	  private WebApplicationContext webApplicationContext;
	
	@Before
	  public void setUp() {
	    this.mockMvc = webAppContextSetup(webApplicationContext).build();
	  }
	 
	 
	 @Test
	    public void postMbLoctionsControllerAllTest() throws Exception {
		    String requestBody = "{allMbUnitLocations{vehicleId dateUpdated town{name}}}";
	        String response = mockMvc.perform(post(API_ROOT_PAT)
	                .content(requestBody)
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().is(HttpStatus.OK.value()))
	                .andExpect(jsonPath("$.data.allMbUnitLocations.length()").value(205))
	                .andReturn().getResponse().getContentAsString();
	         
	        logger.info("allMbUnitLocations query  Response: " + response);
	    }
	 
	 
	
	 
	 @Test
	    public void postMbUnitLocationsByTowTest() throws Exception {
		    String requestBody = "{mbUnitLocationsByTow(townId:15){vehicleId dateUpdated town{name}}}";
	        String response = mockMvc.perform(post(API_ROOT_PAT)
	                .content(requestBody)
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().is(HttpStatus.OK.value()))
	                .andExpect(jsonPath("$.data.mbUnitLocationsByTow.length()").value(57))
	                .andReturn().getResponse().getContentAsString();
	         
	        logger.info("mbUnitLocationsByTow query  Response: " + response);
	    }
	 
	 
	 @Test
	    public void posMbUnitLocationsByFilterTest() throws Exception {
		    String requestBody = "{mbUnitLocationsByFilter(filter:{ date:\"2021-01-28 18:00:02\"}){vehicleId dateUpdated latitude longitude town{id}}}";
	        String response = mockMvc.perform(post(API_ROOT_PAT)
	                .content(requestBody)
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().is(HttpStatus.OK.value()))
	                .andExpect(jsonPath("$.data.mbUnitLocationsByFilter[0].vehicleId").value(449))
	                .andExpect(jsonPath("$.data.mbUnitLocationsByFilter[0].dateUpdated").value("2021-01-28T18:00:02-06:00"))
	                .andExpect(jsonPath("$.data.mbUnitLocationsByFilter[0].latitude").value(19.4035))
	                .andExpect(jsonPath("$.data.mbUnitLocationsByFilter[0].longitude").value( -99.170403))
	                .andExpect(jsonPath("$.data.mbUnitLocationsByFilter[0].town.id").value(15))
	                .andReturn().getResponse().getContentAsString();
	         
	        logger.info("mbUnitLocationsByFilter querie Response: " + response);
	    }
	 
	 
	 @Test
	    public void postMbUnitLocationByIdQueryTest() throws Exception {
		    String requestBody = "{mbUnitLocationsById(vehicleId:170){vehicleId dateUpdated latitude longitude town{id}}}";
	        String response = mockMvc.perform(post(API_ROOT_PAT)
	                .content(requestBody)
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().is(HttpStatus.OK.value()))
	                .andExpect(jsonPath("$.data.mbUnitLocationsById[0].vehicleId").value(170))
	                .andExpect(jsonPath("$.data.mbUnitLocationsById[0].dateUpdated").value("2021-01-27T18:00:02-06:00"))
	                .andExpect(jsonPath("$.data.mbUnitLocationsById[0].latitude").value(19.317499))
	                .andExpect(jsonPath("$.data.mbUnitLocationsById[0].longitude").value(-99.187798))
	                .andExpect(jsonPath("$.data.mbUnitLocationsById[0].town.id").value(3))
	                .andReturn().getResponse().getContentAsString();
	         
	        logger.info("mbUnitLocationsById query Response: " + response);
	    }
	 

}
