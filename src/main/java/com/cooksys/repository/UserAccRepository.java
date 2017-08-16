package com.cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.UserAcc;


public interface UserAccRepository extends JpaRepository<UserAcc, Long> {

	UserAcc findByCredentials_UsernameEquals(String username);
	UserAcc findByCredentials_UsernameEqualsAndCredentials_PasswordEquals(String username, String password);
	
	
}

