package com.hbs.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class AdminDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private int adminId;

	@NotNull(message = "Admin name must not be null")
	private String adminName;
	
	@NotNull(message = "Email must not be null")
	@Email(message = "Invalid email")
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Size(min = 8, message = "Password must have atleast 8 characters")
	private String password;
}
