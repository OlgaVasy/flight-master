package com.cooksys.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.cooksys.entity.User;
import com.cooksys.service.UserService;

@RestController
@RequestMapping("users")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService uService;
}
//	public UserController(UserService uService) {
//		this.uService = uService;		
//	}
	
//    @GetMapping("validate/credentials/exists/@{username}")
//    public boolean credCheck(@RequestParam String password, @PathVariable String username, HttpServletResponse response) {
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");
//        response.setStatus(HttpServletResponse.SC_ACCEPTED);
//        return uService.login(password, username);
//    }  	
	
	//Checks whether or not a given username exists.
//	@GetMapping("validate/username/exists/@{username}")
//	public boolean exists(@PathVariable String username, HttpServletResponse response) {
//		response.setStatus(HttpServletResponse.SC_ACCEPTED);
//		return uService.exists(username);
//	}
//	
//	//Checks whether or not a given username is available.
//	@GetMapping("validate/username/available/@{username}")
//	public boolean available(@PathVariable String username, HttpServletResponse response) {
//		response.setStatus(HttpServletResponse.SC_ACCEPTED);
//		return !uService.exists(username);
//	}
//	
//	//Retrieves all active (non-deleted) users as an array.
//	@GetMapping("users")
//	public List<User> getAll(HttpServletResponse response) {
//		response.setStatus(HttpServletResponse.SC_ACCEPTED);
//		return uService.getAll().stream()
//				.filter(user -> user.getIsActive().equals(true))				
//				.collect(Collectors.toList());
//	}
//	
////	@PostMapping("users")
////	public User create(@RequestBody User user,
////			@RequestParam(required = false) String firstName, 
////			@RequestParam(required = false) String lastName,
////			@RequestParam(required = false) String phone,
////			HttpServletResponse response) {
////		response.setStatus(HttpServletResponse.SC_CREATED);
////		return uService.save(user, firstName, lastName, phone);
////		
////	}
//	
//	@GetMapping("users/@{username}")
//	public User getUser(@PathVariable String username, HttpServletResponse response) {
//		response.setStatus(HttpServletResponse.SC_FOUND);
//		return uService.getUser(username);
//	}
//	
//	@PatchMapping("users/@{username}")
//	public User updateUser(@RequestBody User user, 
//			@RequestParam(required = false) String password, 
//			@RequestParam(required = false) String firstName, 
//			@RequestParam(required = false) String lastName,
//			@RequestParam(required = false) String phone,
//			@PathVariable String username, HttpServletResponse response) {
//		response.setStatus(HttpServletResponse.SC_FOUND);
//		return uService.updateAUser(user, username, password, firstName, lastName, phone);
//	}
//	
//	@DeleteMapping("users/@{username}")
//	public User deleteUser(@PathVariable String username, @PathVariable String password, HttpServletResponse response) {
//		response.setStatus(HttpServletResponse.SC_ACCEPTED);
//		return uService.delete(username, password);
//	}
//	
//}