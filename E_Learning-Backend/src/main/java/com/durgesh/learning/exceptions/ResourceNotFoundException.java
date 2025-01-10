package com.durgesh.learning.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		super("Course Not Found ");
	}
	
	public ResourceNotFoundException(String courseNotFound) {
		super(courseNotFound);
	}

}
