package com.hbs.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class HotelDTO {

	@JsonProperty(access = Access.READ_ONLY)
	private int hotelId;

	@NotNull(message = "City must not be Empty")
	private String city;

	@NotNull(message = "Hotel Name must not be empty ")
	private String hotelName;

	@NotNull(message = "Address must not be empty ")
	private String address;

	@NotNull(message = "Description must not be empty ")
	private String description;

	@JsonProperty(access = Access.READ_ONLY)
	private double avgRatePerDay;

	@NotNull(message = "Email must not be empty")
	@Email
	private String email;

	@NotNull(message = "Phone1 must not be empty")
	@Size(min = 10)
	private String phone1;

	@NotNull(message = "Phone2 must not be empty")
	@Size(min = 10)
	private String phone2;

	@NotNull(message = "Website must not be empty")
	private String website;
}
