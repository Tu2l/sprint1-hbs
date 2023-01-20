package com.hbs.util;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.hbs.dto.BookingDetailsDTO;
import com.hbs.dto.RoomDetailsDTO;
import com.hbs.dto.UserDTO;
import com.hbs.entities.BookingDetails;
import com.hbs.entities.RoomDetails;
import com.hbs.entities.User;

public class MapperUtil {

	private static final ModelMapper MAPPER;
	static {
		MAPPER = new ModelMapper();
	}

	//user<->userdto
	public static User mapToUser(UserDTO userDto) {
		return MAPPER.map(userDto, User.class);
	}

	public static UserDTO mapToUserDto(User user) {
		return MAPPER.map(user, UserDTO.class);
	}

	public static List<UserDTO> mapToUserDtoList(List<User> userList) {
		return MAPPER.map(userList, new TypeToken<List<UserDTO>>() {
		}.getType());
	}

	//roomdetails<->roomdetailsdto
	public static RoomDetails mapToRoomDetails(RoomDetailsDTO dto) {
		return MAPPER.map(dto, RoomDetails.class);
	}

	public static RoomDetailsDTO mapToRoomDetailsDto(RoomDetails roomDetails) {
		return MAPPER.map(roomDetails, RoomDetailsDTO.class);
	}

	public static List<RoomDetailsDTO> mapToRoomDetailsDtoList(List<RoomDetails> roomDetailsList) {
		return MAPPER.map(roomDetailsList, new TypeToken<List<RoomDetailsDTO>>() {
		}.getType());
	}
	
	//bookingdetails<->bookingdetailsdto
	public static BookingDetails mapToBookingDetails(BookingDetailsDTO bookingDetailsDto) {
		return MAPPER.map(bookingDetailsDto, BookingDetails.class);
	}

	public static BookingDetailsDTO mapToBookingDetailsDto(BookingDetails bookingDetails) {
		return MAPPER.map(bookingDetails, BookingDetailsDTO.class);
	}

	public static List<BookingDetailsDTO> mapToBookingDetailsDtoList(List<BookingDetails> bookingDetailsList) {
		return MAPPER.map(bookingDetailsList, new TypeToken<List<BookingDetailsDTO>>() {
		}.getType());
	}
}
