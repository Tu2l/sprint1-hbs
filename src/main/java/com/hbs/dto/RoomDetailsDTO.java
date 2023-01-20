package com.hbs.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RoomDetailsDTO {
	@Min(value = 0, message = "Invalid id")
	private int hotelId;

	@NotNull(message = "room number must not be null")
	private String roomNo;

	@NotNull(message = "room type must not be null")
	private String roomType;

	@Min(value = 0, message = "Invalid rate per day")
	private double ratePerDay;

	private boolean isAvailable;
}
