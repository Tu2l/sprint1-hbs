package com.hbs.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hbs.dto.RoomDetailsDTO;
import com.hbs.exceptions.ActiveBookingFoundException;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.service.RoomDetailsService;

@ExtendWith(MockitoExtension.class)
class RoomDetailsControllerTest {

	@InjectMocks
	private RoomDetailsController controller;

	@Mock
	private RoomDetailsService roomService;

	@Test
	void testAdd() throws HotelNotFoundException, RoomDetailsNotFoundException {
		RoomDetailsDTO dto = new RoomDetailsDTO();
		when(roomService.add(any(RoomDetailsDTO.class))).thenReturn(dto);
		ResponseEntity<RoomDetailsDTO> response = controller.add(dto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	void testUpdate() throws RoomDetailsNotFoundException, HotelNotFoundException {
		RoomDetailsDTO dto = new RoomDetailsDTO();
		when(roomService.update(any(RoomDetailsDTO.class))).thenReturn(dto);
		ResponseEntity<RoomDetailsDTO> response = controller.update(dto, 1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testRemove() throws RoomDetailsNotFoundException, ActiveBookingFoundException, HotelNotFoundException {
		RoomDetailsDTO dto = new RoomDetailsDTO();
		when(roomService.remove(anyInt())).thenReturn(dto);
		ResponseEntity<RoomDetailsDTO> response = controller.remove(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testFindAll() {
		List<RoomDetailsDTO> list = new ArrayList<>();
		when(roomService.findAll()).thenReturn(list);
		ResponseEntity<List<RoomDetailsDTO>> response = controller.findAll();
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testFindById() throws RoomDetailsNotFoundException {
		RoomDetailsDTO dto = new RoomDetailsDTO();
		when(roomService.findById(anyInt())).thenReturn(dto);
		ResponseEntity<RoomDetailsDTO> response = controller.findById(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testFindByHotelId() {
		List<RoomDetailsDTO> list = new ArrayList<>();
		when(roomService.findByHotelId(anyInt())).thenReturn(list);
		ResponseEntity<List<RoomDetailsDTO>> response = controller.findByHotelId(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
