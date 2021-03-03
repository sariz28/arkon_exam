package com.arkon.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arkon.exam.model.Town;

public interface TownRepository extends JpaRepository<Town, Integer>{

}
