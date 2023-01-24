package com.hbs.dto;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RoomImage {
	@NotNull(message = "Image must not be null")
	private MultipartFile image;
	@NotNull(message = "RoomId must not be null")
	private int roomId;
}
