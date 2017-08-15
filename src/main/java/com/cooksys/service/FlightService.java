package com.cooksys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cooksys.component.FlightGenerator;
import com.cooksys.entity.Flight;
import com.cooksys.repository.FlightRepository;

@Service
public class FlightService {

	@Autowired
	FlightGenerator generator;
	
	@Autowired
	FlightRepository repo;

	private ArrayList<Flight> flightList = new ArrayList<>();
	
	
	public ArrayList<Flight> getDailyFlightList()
	{
		return flightList;
	}
	
	//The fixedDelay parameter determines how often a new day is generated as expressed in milliseconds
	@Scheduled(fixedDelay=30000)
	private void refreshFlights()
	{
		flightList = generator.generateNewFlightList();
		repo.save(flightList);		
	}
	public List<Flight> getRoute(String origin, String destination){
		List<Flight> routes = new ArrayList<>();
		for (Flight flight: flightList){
			if (flight.getOrigin().equals(origin) && flight.getDestination().equals(destination)){
				routes.add(flight);
			}
		}
		return routes;
	}
	
}
