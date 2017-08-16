package com.cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByCredentials_UsernameEquals(String username);
	User findByCredentials_UsernameEqualsAndCredentials_PasswordEquals(String username, String password);
	
	
}

