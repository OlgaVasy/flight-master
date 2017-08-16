package com.cooksys.mapper;

import org.mapstruct.Mapper;

import com.cooksys.dto.BookedFlightDto;
import com.cooksys.entity.BookedFlight;

@Mapper(componentModel = "spring", uses = {UserAccMapper.class, ProfileMapper.class})
public interface BookedFlightMapper {	
	
	BookedFlightDto bookedFlightDto(BookedFlight f);
	BookedFlight toBookedFlight(BookedFlightDto f);

}
