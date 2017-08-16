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
	@Scheduled(fixedDelay=10000)
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
		//find the fastest flight
		if (routes.size() > 1){
			Flight theFlight = routes.get(0);		
			for (Flight flight: routes){
				if (flight.getFlightTime() > theFlight.getFlightTime())
					routes.remove(flight);
					else {
						routes.remove(theFlight);
						theFlight = flight;
					}					
			}
		}
		//find flights with layovers
		if (routes.isEmpty()){
			for (Flight flight: flightList){
				if (flight.getOrigin().equals(origin)){
					for (Flight connection: flightList){
						if (flight.getDestination().equals(connection.getOrigin())&&connection.getDestination().equals(destination)){
							if(flight.getOffset() + flight.getFlightTime() < connection.getOffset()){
								routes.add(flight);
								routes.add(connection);}
					
						}
				}
			}
		}}
		if (routes.size() > 2){
			if (routes.get(1).getFlightTime() + routes.get(1).getOffset() - routes.get(0).getOffset() < 
					routes.get(3).getFlightTime() + routes.get(3).getOffset() - routes.get(2).getOffset()){
				routes.remove(2);
				routes.remove(3);}
			else {
				routes.remove(0);
				routes.remove(1);
			}
		}		
		return routes;
	}
	
}
