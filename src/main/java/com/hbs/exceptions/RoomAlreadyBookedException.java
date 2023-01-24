package com.hbs.exceptions;

public class RoomAlreadyBookedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8855290852832447693L;

	public RoomAlreadyBookedException(String msg) {
		super(msg);
	}
}
