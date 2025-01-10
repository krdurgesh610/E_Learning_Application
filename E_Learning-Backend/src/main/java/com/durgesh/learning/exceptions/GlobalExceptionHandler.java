package com.durgesh.learning.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.durgesh.learning.dtos.CustomMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomMessage> handleResourceNotFound(ResourceNotFoundException ex) {
		CustomMessage customMessage = new CustomMessage();
		customMessage.setMessage(ex.getMessage());
		customMessage.setSuccess(false);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customMessage);

	}

}
