package com.hbs.entities;

public enum UserRole {
	USER, ADMIN, UNDEFINED;

	public static UserRole set(String string) {
		string = string.toLowerCase();
		if (string.equals("user"))
			return USER;
		else if (string.equals("admin"))
			return ADMIN;

		return UserRole.UNDEFINED;
	}
}
