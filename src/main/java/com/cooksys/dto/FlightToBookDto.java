package com.cooksys.dto;

import com.cooksys.entity.Credentials;
import com.cooksys.entity.Flight;

public class FlightToBookDto {
	
	Flight flight;	
	Credentials credentials;
	
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

}
