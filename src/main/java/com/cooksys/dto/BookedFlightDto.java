package com.cooksys.dto;
import com.cooksys.entity.Flight;


public class BookedFlightDto {
	
	private Flight flight;		

	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}	
}
