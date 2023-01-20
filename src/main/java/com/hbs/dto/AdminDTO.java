package com.hbs.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AdminDTO {
	@NotNull(message = "Admin name must not be null")
	private String adminName;
	
	@NotNull(message = "Email must not be null")
	@Email(message = "Invalid email")
	private String email;
	
	@Size(min = 8, message = "Password must have atleast 8 characters")
	private String password;
}
