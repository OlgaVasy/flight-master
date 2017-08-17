package com.cooksys.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.dto.BookedFlightDto;
import com.cooksys.dto.UserAccCreateDto;
import com.cooksys.dto.UserAccCredOnlyDto;
import com.cooksys.dto.UserAccDto;
import com.cooksys.mapper.BookedFlightMapper;
import com.cooksys.mapper.UserAccMapper;
import com.cooksys.service.UserAccService;


@RestController
@RequestMapping("user")
@CrossOrigin
public class UserAccController {

	private UserAccService uService;
	private UserAccMapper tMapper;	
	private BookedFlightMapper flightMapper;

	public UserAccController(UserAccService uService, UserAccMapper tMapper, BookedFlightMapper flightMapper) {
		this.uService = uService;
		this.tMapper = tMapper;	
		this.flightMapper = flightMapper;
	}
	
    @GetMapping("validate/credentials/exists/@{username}")
    public boolean credCheck(@RequestParam String password, @PathVariable String username, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");
        response.setStatus(HttpServletResponse.SC_ACCEPTED);     
        return uService.login(password, username);
    }  	
	
	//Checks whether or not a given username exists.
	@GetMapping("validate/username/exists/@{username}")
	public boolean exists(@PathVariable String username, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		return uService.exists(username);
	}
	
	//Checks whether or not a given username is available.
	@GetMapping("validate/username/available/@{username}")
	public boolean available(@PathVariable String username, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		return !uService.exists(username);
	}
	
	//Retrieves all active (non-deleted) users as an array.
	@GetMapping("users")
	public List<UserAccDto> getAll(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		return uService.getAll().stream()
				.filter(user -> user.getIsActive().equals(true))
				.map(tMapper::tUserDto)
				.collect(Collectors.toList());
	}
	
	@PostMapping("users")
	public UserAccDto create(@RequestBody UserAccCreateDto user,
			@RequestParam(required = false) String firstName, 
			@RequestParam(required = false) String lastName,
			@RequestParam(required = false) String phone,
			HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_CREATED);
		return tMapper.tUserDto(uService.save(tMapper.toUserAcc(user), firstName, lastName, phone));
		
	}
	
	@GetMapping("users/@{username}")
	public UserAccDto getUser(@PathVariable String username, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_FOUND);
		return tMapper.tUserDto(uService.getUser(username));
	}
	
	@PatchMapping("users/@{username}")
	public UserAccDto updateUser(@RequestBody UserAccCredOnlyDto user, 
			@RequestParam(required = false) String firstName, 
			@RequestParam(required = false) String lastName,
			@RequestParam(required = false) String phone,
			@PathVariable String username, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_FOUND);
		return tMapper.tUserDto(uService.updateAUser(user, username, firstName, lastName, phone));
	}
	
	@DeleteMapping("users/@{username}")
	public UserAccDto deleteUser(@RequestBody UserAccCredOnlyDto creds,  @PathVariable String username, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		return tMapper.tUserDto(uService.delete(username, tMapper.toUserAcc(creds)));
	}
	@GetMapping("users/@{username}/bookedFlights")
	public List<BookedFlightDto> getUserFlights(@PathVariable String username, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_FOUND);
		return uService.getUserBookedFlights(username).stream()
				.filter(tweet -> tweet.getIsDeleted().equals(false))
				.map(flightMapper::bookedFlightDto)
				.collect(Collectors.toList());
	}
	
}