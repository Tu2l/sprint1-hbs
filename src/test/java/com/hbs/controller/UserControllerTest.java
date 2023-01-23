package com.hbs.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hbs.entities.User;
import com.hbs.service.UserService;

class UserControllerTest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//	@Autowired
//	private MockMvc mockmvc;
//	
//	@MockBean
//	private UserService userService;
//	
//	private ObjectMapper mapper = new ObjectMapper();
//	
//	@Test
//	public void testGetExample()throws Exception{
//		List<User> users = new ArrayList<>();
//		User user = new User();
//		user.setUserId(1);
//		user.setUserName("Abhi");
//		user.setEmail("Abhi@gmail.com");
//		user.setPassword("Abhi1234567");
//		user.setRole("Normal");
//		user.setMobile("9343850209");
//		user.setAddress("Adressadded");
//		users.add(user);
//		Mockito.when(userService.findAll()).thenReturn(users);
//		mockmvc.perform(get("/getMapping")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1))).andExpect(jsonPath("$[0].name", Matchers.equalTo("Abhi")));
//	}
//	

}
