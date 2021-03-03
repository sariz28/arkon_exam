package com.arkon.exam.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TownRepositoryTest {
	
	@Autowired
	private TownRepository townRepository;
	
	private static final int ALL_TOWNS = 16;
	private static final int TOWN_ID = 10;
	private static final String TOWN_NAME = "Álvaro Obregón";
	
	
	@Test
	    public void findAllTownstest() {
	        assertEquals(ALL_TOWNS, townRepository.findAll().size());
	        
	}
	
	
	@Test
    public void fintTownById() {
		
        assertEquals(TOWN_NAME, townRepository.findById(TOWN_ID).get().getName());
        
	}

}
