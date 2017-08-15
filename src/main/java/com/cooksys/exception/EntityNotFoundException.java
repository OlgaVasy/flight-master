package com.cooksys.exception;

public class EntityNotFoundException extends RuntimeException {
	
	public EntityNotFoundException() {
		super("The entity could not be found.");
	}
}
