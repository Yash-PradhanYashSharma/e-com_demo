package com.yash.ecom.server.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yash.ecom.server.exceptions.CreateOrderException;

@RestControllerAdvice
public class CreateOrderExceptionsHandler {

	@ExceptionHandler(CreateOrderException.class)
	public ResponseEntity<Object> errorOccured(CreateOrderException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some Error Occurred");
	}

}
