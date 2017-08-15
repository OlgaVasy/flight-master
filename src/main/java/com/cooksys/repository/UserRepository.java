package com.cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {

	User findById(long id);	
	User findByUsernameAndPassword(String username, String password);
	User findByUsername(String username);
	
}

