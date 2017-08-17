package com.cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.BookedFlight;

public interface BookedFlightRepository extends JpaRepository<BookedFlight, Long> {	
	
	List<BookedFlight> findByClientId(long id);

	
}
