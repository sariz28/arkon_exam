package com.arkon.exam.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

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

public class MbUnitLocation {
	
	@Id
	private Integer id;
	private Integer vehicleId;
	private OffsetDateTime dateUpdated;
	
	@Column(precision=9, scale=6)
	@Type(type = "big_decimal")
	private BigDecimal latitude;
	
	@Column(precision=9, scale=6)
	private BigDecimal longitude;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name="town_id", referencedColumnName="id"),
    })
    private Town town;
}
