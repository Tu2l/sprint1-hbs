package com.hbs.auth;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {
	@NotNull(message = "username/email must not be null")
	private String email;
	@NotNull(message = "password must not be null")
	private String password;
}
