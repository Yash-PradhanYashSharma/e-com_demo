package com.yash.ecom.server.exceptions;

public class CreateOrderException extends Exception {

	private static final long serialVersionUID = 1L;

	public CreateOrderException(String message, Throwable cause) {
		super(message, cause);
	}
}
