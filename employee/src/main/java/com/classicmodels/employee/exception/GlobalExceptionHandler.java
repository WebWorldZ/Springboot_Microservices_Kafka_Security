package com.classicmodels.employee.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorObject> handleException(ResourceNotFoundException e, HttpServletRequest req) {
		ErrorObject error = new ErrorObject(
				req.getRequestURI(),
				e.getMessage(),
				HttpStatus.NOT_FOUND.value(),
				LocalDate.now(),
				null
				);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
}
