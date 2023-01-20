package com.hbs.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDTO {
	private int userId;
	@NotNull(message = "UserName must not be null")
	@Size(min = 4, message = "Username must have atleast 4 characters")
	private String userName;
	
	@NotNull(message = "Email must not be null")
	@Email(message = "Invalid email")
	private String email;
	
	@NotNull(message = "Password must not be null")
	@Size(min = 8, message = "Password must have atleast 8 characters")
	private String password;
	
	private String role;
	
	@NotNull(message = "mobile must not be null")
	@Size(min=10,max=10, message = "Invalid mobile number")
	private String mobile;
	
	@NotNull(message = "Address must not be null")
	@Size(min=5, message = "Address must have atleast 5 characters")
	private String address;
}
