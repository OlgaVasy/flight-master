package com.cooksys.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.cooksys.dto.UserAccCreateDto;
import com.cooksys.dto.UserAccCredOnlyDto;
import com.cooksys.dto.UserAccDisplayNameDto;
import com.cooksys.dto.UserAccDto;
import com.cooksys.entity.UserAcc;


@Mapper(componentModel = "spring", uses = {ProfileMapper.class})
public interface UserAccMapper {

    @Mappings({
        @Mapping(source = "credentials.username", target = "username")
    })
    UserAccDto tUserDto(UserAcc t);
    UserAcc toTweetUser(UserAccDto t);
	
    UserAcc toUserAcc(UserAccCreateDto t);
    UserAccCreateDto tCreateDto(UserAcc t);
	
    UserAccCredOnlyDto userAccCredOnlyDto(UserAcc t);
    UserAcc toUserAcc(UserAccCredOnlyDto t);
	
    @Mappings({
        @Mapping(source = "credentials.username", target = "username")
    })
    UserAccDisplayNameDto userAccDisplayNameDto(UserAcc t);
    UserAcc toUserAcc(UserAccDisplayNameDto t);
}
