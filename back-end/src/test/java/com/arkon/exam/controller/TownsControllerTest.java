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
public class TownsControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(TownsControllerTest.class);
	private static final String API_ROOT_PAT = "/towns";
	private static final int ALL_TOWNS = 16;
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	  private WebApplicationContext webApplicationContext;
	
	@Before
	  public void setUp() {
	    this.mockMvc = webAppContextSetup(webApplicationContext).build();
	  }
	 
	 
	 @Test
	    public void postTownsControllerQueryTownTest() throws Exception {
		    String requestBody = "{town(id:8){name}}";
	        String response = mockMvc.perform(post(API_ROOT_PAT)
	                .content(requestBody)
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().is(HttpStatus.OK.value()))
	                .andExpect(jsonPath("$.data.town.name").value("La Magdalena Contreras"))
	                .andReturn().getResponse().getContentAsString();
	         
	        logger.info("Query town Response: " + response);
	    }
	 
	 @Test
	    public void postTownsControllerQueryAllTownsTest() throws Exception {
		    String requestBody = "{allTowns{name id}}";
	        String response = mockMvc.perform(post(API_ROOT_PAT)
	                .content(requestBody)
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().is(HttpStatus.OK.value()))
	                .andExpect(jsonPath("$.data.allTowns.length()").value(ALL_TOWNS))
	                .andReturn().getResponse().getContentAsString();
	         
	        logger.info("Query allTowns Response: " + response);
	    }
	 
	 @Test
	    public void postTownsControllerAllQueriesTest() throws Exception {
		    String requestBody = "{town(id:8){name} allTowns{name id}}";
	        String response = mockMvc.perform(post(API_ROOT_PAT)
	                .content(requestBody)
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().is(HttpStatus.OK.value()))
	                .andExpect(jsonPath("$.data.town.name").value("La Magdalena Contreras"))
	                .andExpect(jsonPath("$.data.allTowns.length()").value(ALL_TOWNS))
	                .andReturn().getResponse().getContentAsString();
	         
	        logger.info("All Queries Response: " + response);
	    }
	 
	 
}
