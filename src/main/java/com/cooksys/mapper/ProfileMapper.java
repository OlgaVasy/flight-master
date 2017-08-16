package com.cooksys.mapper;

import org.mapstruct.Mapper;

import com.cooksys.dto.ProfileDto;
import com.cooksys.dto.ProfileEmailOnlyDto;
import com.cooksys.entity.Profile;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
	
	ProfileDto tProfileDto(Profile p);
	Profile toProfile(ProfileDto p);
	
	ProfileEmailOnlyDto tEmailOnlyDto(Profile p);
	Profile toProfile(ProfileEmailOnlyDto p);
	
}
