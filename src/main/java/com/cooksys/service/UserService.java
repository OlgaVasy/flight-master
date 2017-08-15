package com.cooksys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.entity.User;
import com.cooksys.exception.EntityNotFoundException;
import com.cooksys.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repo;
	
	public List<User> getAll() {
		return repo.findAll();
	}
	
	public User save(User user, String firstName, String lastName, String phone) {
		
		//Checks if a user is created
		if (exists(user.getUsername())) {
			//Gets the user if the user was created
			User client = checkUserCredentials(user.getUsername(), user.getPassword());
			
			//If the user was deleted will reactive the user
			if (client != null && client.getIsActive().equals(false)) {
				client.setIsActive(true);
				return repo.save(client);
			}
			
			throw new com.cooksys.exception.UsernameExistsException();
		} 
		
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPhone(phone);
		
		//If all else fails then it creates a new user
		return repo.save(user);
	}
	
	public User save(User user) {
		return repo.save(user);
	}
	public boolean exists(String username) {
		return repo.findByUsername(username) != null;
	}
	
	public User checkUserCredentials(String username, String password) {
		return repo
				.findByUsernameAndPassword(username, password);
	}
	
	public User updateAUser(User user, String username, String password, String firstName, String lastName, String phone) {
		User client = checkUserCredentials(user.getUsername(), user.getPassword());
		
		if (client != null 
				&& client.getUsername().equals(username) && client.getPassword().equals(password)) {
			
			if (firstName != null) {
				client.setFirstName(firstName);
			}
			
			if (lastName != null) {
				client.setLastName(lastName);
			}			
			
			if (phone != null) {
				client.setPhone(phone);
			}
			
			return repo.save(client);
		}
		
		throw new EntityNotFoundException();
	}

	public User getUser(String username) {
		User user = repo.findByUsername(username);
		if (user != null) {
			return user;
		}
		
		throw new EntityNotFoundException();

	}

	public User delete(String username, String password) {
		User user = checkUserCredentials(username, password);	
		if (user != null && username.equals(user.getUsername())) {
			user.setIsActive(false);
		} else {
			throw new EntityNotFoundException();
		}
		
		return user;
	}

}
