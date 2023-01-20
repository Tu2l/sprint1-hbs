package com.hbs.util;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.hbs.dto.UserDTO;
import com.hbs.entities.User;

public class Util {

	private static final ModelMapper MAPPER;
	static {
		MAPPER = new ModelMapper();
	}

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

}
