package com.cooksys.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.dto.BookedFlightDto;
import com.cooksys.dto.FlightToBookDto;
import com.cooksys.entity.BookedFlight;
import com.cooksys.mapper.BookedFlightMapper;
import com.cooksys.mapper.UserAccMapper;
import com.cooksys.service.BookedFlightService;

@RestController
@CrossOrigin
@RequestMapping("bookedFlight")
public class BookedFlightController {
	
	private BookedFlightService tService;
	private BookedFlightMapper tMapper;	
	private UserAccMapper tUserMapper;

	public BookedFlightController(BookedFlightService tService, BookedFlightMapper tMapper, UserAccMapper tUserMapper) {
		this.tService = tService;
		this.tMapper = tMapper;		
		this.tUserMapper = tUserMapper;
	}
	
	@GetMapping
	public List<BookedFlightDto> getAll(HttpServletResponse response) {
		return tService.getAll()
				.stream()
				.filter(tweet -> tweet.getIsDeleted().equals(false) && tweet.getClient().getIsActive().equals(true))				
				.map(tMapper::bookedFlightDto)
				.collect(Collectors.toList());
	}
	
	@PostMapping("bookedFlights")
	public BookedFlightDto bookAFlight(@RequestBody FlightToBookDto flights, HttpServletResponse response) {
		return tMapper.bookedFlightDto(tService.bookAFlight(flights));
	}
	
	@GetMapping("bookedFlights/{id}")
	public BookedFlight getBookedFlightById(@PathVariable Long id, HttpServletResponse response) {
		return tService.getById(id);
	}
	
	@DeleteMapping("bookedFlights/{id}")
	public BookedFlight deleteBookedFlightById(@PathVariable Long id, HttpServletResponse response) {
		return tService.deleteById(id);
	}	
	
	
}
