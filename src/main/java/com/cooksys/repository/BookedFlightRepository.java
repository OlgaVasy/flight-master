package com.cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.BookedFlight;
import com.cooksys.entity.UserAcc;

public interface BookedFlightRepository extends JpaRepository<BookedFlight, Long> {	
	
	List<BookedFlight> findByClient(UserAcc id);

	
}
