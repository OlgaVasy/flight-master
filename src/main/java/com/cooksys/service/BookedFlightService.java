package com.cooksys.service;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cooksys.dto.FlightToBookDto;
import com.cooksys.entity.BookedFlight;
import com.cooksys.entity.UserAcc;
import com.cooksys.exception.EntityNotFoundException;
import com.cooksys.exception.InvalidArgumentPassedException;
import com.cooksys.mapper.BookedFlightMapper;
import com.cooksys.repository.BookedFlightRepository;

@Service
public class BookedFlightService {

	private Logger log = LoggerFactory.getLogger(getClass());
	private BookedFlightRepository tRepo;
	private UserAccService uService;	
	private BookedFlightMapper tMapper;	

	public BookedFlightService(BookedFlightRepository tRepo, UserAccService uService,  BookedFlightMapper tMapper) {
		this.tRepo = tRepo;
		this.uService = uService;		
		this.tMapper = tMapper;
	}
	
	public List<BookedFlight> getAll() {
		return tRepo.findAll();
	}

	public BookedFlight bookAFlight(FlightToBookDto flights) {
			
		UserAcc user = uService.checkUserCredentials(flights.getCredentials());
		
		if (user != null) {
			BookedFlight flightCrea = new BookedFlight();
			flightCrea.setFlight(flights.getFlight());
			flightCrea.setClient(user);	
			return tRepo.save(flightCrea);
		}
		
		throw new InvalidArgumentPassedException();
	}	

	public BookedFlight getById(Long id) {
		BookedFlight bookedFlight = tRepo.findOne(id);
		if (bookedFlight != null) {
			return bookedFlight;			
		}
		
		throw new EntityNotFoundException();
	}

	public BookedFlight deleteById(Long id) {
		if (tRepo.exists(id)) {
			BookedFlight flight = getById(id);
			flight.setIsDeleted(true);
			return flight;
		}
		
		throw new EntityNotFoundException();
	}	
}
