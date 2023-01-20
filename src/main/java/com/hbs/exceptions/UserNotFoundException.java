package com.hbs.exceptions;

public class UserNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2669691225657466142L;

	public UserNotFoundException(String msg) {
		super(msg);
	}
}
