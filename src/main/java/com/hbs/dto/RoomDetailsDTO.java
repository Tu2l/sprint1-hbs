package com.hbs.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class RoomDetailsDTO {
	private int hotelId;

	@JsonProperty(access = Access.READ_ONLY)
	private int roomId;

	@NotNull(message = "room number must not be null")
	private String roomNo;

	@NotNull(message = "room type must not be null")
	private String roomType;

	@Min(value = 0, message = "Invalid rate per day")
	private double ratePerDay;

	@JsonProperty(access = Access.READ_ONLY)
	private String imageUrl;

}
