package com.hbs.exceptions;

public class HotelNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8056346897169839947L;

	public HotelNotFoundException(String message) {
		super(message);
	}
}
