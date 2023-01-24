package com.hbs.util;

import java.util.regex.Pattern;

public class ValidationUtil {

	ValidationUtil() {
	}

	public static boolean validatePattern(String regex, String data) {
		return Pattern.compile(regex).matcher(data).matches();
	}

	public static boolean validateEmail(String email) {
		String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

		return validatePattern(regexPattern, email);
	}

	public static boolean validatePhoneNumber(String phone) {
		String regexPattern = "\\d{10}";

		return validatePattern(regexPattern, phone);
	}
	
	public static boolean validateImageExtension(String ext) {
		String regex = "(jpe?g|png|gif|bmp)";
		
		return validatePattern(regex, ext);
		
	}
}
