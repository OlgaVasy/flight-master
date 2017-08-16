package com.cooksys.service;


import java.util.List;
import org.springframework.stereotype.Service;
import com.cooksys.entity.Credentials;
import com.cooksys.entity.User;
import com.cooksys.exception.EntityNotFoundException;
import com.cooksys.exception.UsernameExistsException;
import com.cooksys.repository.UserRepository;
@Service
public class UserService {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;		
	}
	
	public boolean login(String password, String username) {
		String user = username.substring(1, username.length()-1); 
        return userRepository.findByCredentials_UsernameEqualsAndCredentials_PasswordEquals(user, password) != null;
    }

	public boolean exists(String username) {
		return userRepository.findByCredentials_UsernameEquals(username) != null;
	}
	
	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User save(User user) {
		
		//Checks if a user is created
		if (exists(user.getCredentials().getUsername())) {
			//Gets the user if the user was created
			User tUser = checkUserCredentials(user.getCredentials());
			
			//If the user was deleted will reactive the user
			if (tUser != null && tUser.getIsActive().equals(false)) {
				tUser.setIsActive(true);
				return userRepository.save(tUser);
			}
			
			throw new UsernameExistsException();
		} 
//		
//		user.getProfile().setFirstName(user.getProfile().getFirstName());
//		user.getProfile().setLastName(user.getProfile().getLastName());
//		user.getProfile().setPhone(user.getProfile().getPhone());
		
		//If all else fails then it creates a new user
		return userRepository.save(user);
	}
	
//	public User save(User user) {
//		return userRepository.save(user);
//	}

	
//	public User updateAUser(UserCredOnlyDto user, String username, String firstName, String lastName, String phone) {
//		TweetUser tweetUser = checkUserCredentials(user.getCredentials());
//		
//		if (tweetUser != null && tweetUser.getIsActive().equals(true) 
//				&& tweetUser.getCredentials().getUsername().equals(username)) {
//			
//			if (firstName != null) {
//				tweetUser.getProfile().setFirstName(firstName);
//			}
//			
//			if (lastName != null) {
//				tweetUser.getProfile().setLastName(lastName);
//			}
//			
//			
//			if (phone != null) {
//				tweetUser.getProfile().setPhone(phone);
//			}
//			
//			return userRepository.save(tweetUser);
//		}
//		
//		throw new EntityNotFoundException();
//	}

	public User getUser(String username) {
		User user = userRepository.findByCredentials_UsernameEquals(username);
		if (user != null) {
			return user;
		}
		
		throw new EntityNotFoundException();

	}

	public User delete(String username, User creds) {
		User user = checkUserCredentials(creds.getCredentials());	
		if (user != null && username.equals(user.getCredentials().getUsername())) {
			user.setIsActive(false);
		} else {
			throw new EntityNotFoundException();
		}
		
		return user;
	}
	
	public User checkUserCredentials(Credentials user) {
		return userRepository
				.findByCredentials_UsernameEqualsAndCredentials_PasswordEquals(
						user.getUsername(), user.getPassword());
	}
	
}