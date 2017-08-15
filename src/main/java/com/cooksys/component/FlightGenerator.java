package com.cooksys.component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cooksys.entity.Flight;
import com.cooksys.entity.Location;
import com.cooksys.pojo.Cities;
import com.cooksys.repository.LocationRepository;

@Component
public class FlightGenerator {
	
	@Autowired
	LocationRepository repo;	
	
	public ArrayList<Flight> generateNewFlightList() {
		
		ArrayList<Flight> result = new ArrayList<>();
		List<Location> cities = repo.findAll();			

		for (int i = 0; i < 5; i++) {

			int originIndex = ThreadLocalRandom.current().nextInt(0, 4);

			int destinationIndex = ThreadLocalRandom.current().nextInt(0, 4);

			while (destinationIndex == originIndex)
				destinationIndex = ThreadLocalRandom.current().nextInt(0, 4);

			String origin = cities.get(originIndex).getCity();
			String destination = cities.get(destinationIndex).getCity();
			int flightTime = ThreadLocalRandom.current().nextInt(1, 4);
			int offset = ThreadLocalRandom.current().nextInt(0, 10);

			Flight f = new Flight(origin, destination, flightTime, offset);

			result.add(f);
		}
		return result;
	}

}
