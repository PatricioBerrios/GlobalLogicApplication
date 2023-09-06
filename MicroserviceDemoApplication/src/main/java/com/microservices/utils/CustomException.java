package com.microservices.utils;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 6178251342002907579L;
	private final int code;

	public CustomException(int code, String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
