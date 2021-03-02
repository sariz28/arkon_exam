package com.arkon.exam.web;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Table(name = "towns")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class Town {
	
	@Id
	private Integer id;
	private String name;
	

}
