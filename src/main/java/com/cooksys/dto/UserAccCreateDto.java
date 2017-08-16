package com.cooksys.dto;

import com.cooksys.entity.Credentials;

public class UserAccCreateDto {

	private Credentials credentials;
	private ProfileEmailOnlyDto profile;

	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	public ProfileEmailOnlyDto getProfile() {
		return profile;
	}
	public void setProfile(ProfileEmailOnlyDto profile) {
		this.profile = profile;
	}



}
