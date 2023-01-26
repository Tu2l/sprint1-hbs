package com.hbs.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbs.dto.RoomDetailsDTO;
import com.hbs.dto.RoomImage;
import com.hbs.exceptions.ActiveBookingFoundException;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.InvalidImageFormatException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.service.RoomDetailsService;

@RestController
@RequestMapping("/room")
public class RoomDetailsController {

	@Autowired
	private RoomDetailsService roomService;

	@PostMapping
	public ResponseEntity<RoomDetailsDTO> add(@Valid @RequestBody RoomDetailsDTO dto)
			throws HotelNotFoundException, RoomDetailsNotFoundException {

		return new ResponseEntity<>(roomService.add(dto), HttpStatus.CREATED);
	}

	@PostMapping(consumes = { "multipart/form-data" }, path = "/upload/{roomId}")
	public ResponseEntity<RoomDetailsDTO> uploadImage(@Valid @ModelAttribute RoomImage image)
			throws IOException, RoomDetailsNotFoundException, InvalidImageFormatException {

		return new ResponseEntity<>(roomService.uploadImage(image), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<RoomDetailsDTO> update(@Valid @RequestBody RoomDetailsDTO dto, @PathVariable int id)
			throws RoomDetailsNotFoundException, HotelNotFoundException {
		dto.setRoomId(id);
		return new ResponseEntity<>(roomService.update(dto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<RoomDetailsDTO> remove(@PathVariable int id)
			throws RoomDetailsNotFoundException, ActiveBookingFoundException, HotelNotFoundException {
		return new ResponseEntity<>(roomService.remove(id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<RoomDetailsDTO>> findAll() {
		return new ResponseEntity<>(roomService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RoomDetailsDTO> findById(@PathVariable int id) throws RoomDetailsNotFoundException {
		return new ResponseEntity<>(roomService.findById(id), HttpStatus.OK);
	}

	@GetMapping("/hotel/{id}")
	public ResponseEntity<List<RoomDetailsDTO>> findByHotelId(@PathVariable int id) {
		return new ResponseEntity<>((roomService.findByHotelId(id)), HttpStatus.OK);
	}

	@GetMapping("/{from}/{to}")
	public ResponseEntity<List<RoomDetailsDTO>> findAvailable(@PathVariable String from, @PathVariable String to) {
		return new ResponseEntity<>((roomService.findAllAvailableRoom(LocalDate.parse(from), LocalDate.parse(to))),
				HttpStatus.OK);
	}
}
