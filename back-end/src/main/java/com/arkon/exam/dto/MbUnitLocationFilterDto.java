package com.arkon.exam.dto;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.arkon.exam.model.Town;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class MbUnitLocationFilterDto {
	
	Integer vehicleId;
	String date;
	Integer townId;
	Town town;
	
	public OffsetDateTime getDateUpdated() {
		
		OffsetDateTime offsetDateTime = null;
		if(this.date != null) {
			String DATE_TIME_SECOND_STRING = "yyyy-MM-dd HH:mm:ss";
			// First convert the time string to LocalDateTime
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_SECOND_STRING);
			LocalDateTime parse = LocalDateTime.parse(this.date, dateTimeFormatter);
			// Add the time zone to get the complete world time
			offsetDateTime = ZonedDateTime.of(parse, ZoneId.systemDefault()).toOffsetDateTime();
		}
		
		return offsetDateTime;
		
	}
	
	public Town getTown() {
		
	       return (this.townId !=null)? new Town(this.townId) : null;
	}

}
