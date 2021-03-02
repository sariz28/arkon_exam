package com.arkon.exam.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "metrobus_units_locations")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class MetrobusUnitLocation {
	
	@Id
	private Integer id;
	private Integer vehicle_id;
	private Timestamp dateUpdated;
	private Float latitude;
	private Float longitude;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name="town_id", referencedColumnName="id"),
    })
    private Town town;
}
