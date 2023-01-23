package com.hbs.entities;

public enum UserRole {
	USER, ADMIN, UNDEFINED;

	public static UserRole set(String string) {
		switch (string.toLowerCase()) {
		case "user":
			return UserRole.USER;
		case "admin":
			return UserRole.ADMIN;
		}

		return UserRole.UNDEFINED;
	}
}
