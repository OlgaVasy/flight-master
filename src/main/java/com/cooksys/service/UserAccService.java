package com.cooksys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.dto.UserAccCredOnlyDto;
import com.cooksys.entity.BookedFlight;
import com.cooksys.entity.Credentials;
import com.cooksys.entity.UserAcc;
import com.cooksys.exception.EntityNotFoundException;
import com.cooksys.exception.UsernameExistsException;
import com.cooksys.repository.BookedFlightRepository;
import com.cooksys.repository.UserAccRepository;

@Service
public class UserAccService {
	
	private UserAccRepository userAccRepository;	
	private BookedFlightRepository bookedFlightRepository;
	
	public UserAccService(UserAccRepository userAccRepository, BookedFlightRepository bookedFlightRepository) {
		this.userAccRepository = userAccRepository;	
		this.bookedFlightRepository = bookedFlightRepository;	
	}		
	public boolean login(String password, String username) {	
        return userAccRepository.findByCredentials_UsernameEqualsAndCredentials_PasswordEquals(username, password) != null;
    }

	public boolean exists(String username) {
		return userAccRepository.findByCredentials_UsernameEquals(username) != null;
	}
	
	public List<UserAcc> getAll() {
		return userAccRepository.findAll();
	}

	public UserAcc save(UserAcc user, String firstName, String lastName, String phone) {
		
		//Checks if a user is created
		if (exists(user.getCredentials().getUsername())) {
			//Gets the user if the user was created
			UserAcc tUser = checkUserCredentials(user.getCredentials());
			
			//If the user was deleted will reactive the user
			if (tUser != null && tUser.getIsActive().equals(false)) {
				tUser.setIsActive(true);
				return userAccRepository.save(tUser);
			}
			
			throw new UsernameExistsException();
		} 
		
		user.getProfile().setFirstName(firstName);
		user.getProfile().setLastName(lastName);
		user.getProfile().setPhone(phone);
		
		//If all else fails then it creates a new user
		return userAccRepository.save(user);
	}
	
	public UserAcc save(UserAcc user) {
		return userAccRepository.save(user);
	}

	
	public UserAcc updateAUser(UserAccCredOnlyDto user, String username, String firstName, String lastName, String phone) {
		UserAcc userAcc = checkUserCredentials(user.getCredentials());
		
		if (userAcc != null && userAcc.getIsActive().equals(true) 
				&& userAcc.getCredentials().getUsername().equals(username)) {
			
			if (firstName != null) {
				userAcc.getProfile().setFirstName(firstName);
			}
			
			if (lastName != null) {
				userAcc.getProfile().setLastName(lastName);
			}
			
			
			if (phone != null) {
				userAcc.getProfile().setPhone(phone);
			}
			
			return userAccRepository.save(userAcc);
		}
		
		throw new EntityNotFoundException();
	}

	public UserAcc getUser(String username) {
		UserAcc userAcc = userAccRepository.findByCredentials_UsernameEquals(username);
		if (userAcc != null) {
			return userAcc;
		}
		
		throw new EntityNotFoundException();

	}

	public UserAcc delete(String username, UserAcc creds) {
		UserAcc user = checkUserCredentials(creds.getCredentials());	
		if (user != null && username.equals(user.getCredentials().getUsername())) {
			user.setIsActive(false);
		} else {
			throw new EntityNotFoundException();
		}
		
		return user;
	}
	
	
	public UserAcc checkUserCredentials(Credentials user) {
		return userAccRepository
				.findByCredentials_UsernameEqualsAndCredentials_PasswordEquals(
						user.getUsername(), user.getPassword());
	}
	
	public List<BookedFlight> getUserBookedFlights(String username) {
		UserAcc user = userAccRepository.findByCredentials_UsernameEquals(username);
		
		if (user != null && user.getIsActive().equals(true)) {
			return bookedFlightRepository.findByClientId(user.getId());
		}
		
		throw new EntityNotFoundException();
	}
	
}