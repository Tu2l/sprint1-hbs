package com.hbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbs.dto.RoomDetailsDTO;
import com.hbs.entities.RoomDetails;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.service.RoomDetailsService;

import antlr.collections.List;

@RestController
@RequestMapping("/roomdetails")
public class RoomDetailsController {

	@Autowired
	private RoomDetailsService roomDetailsService;

	@PostMapping
	public ResponseEntity<RoomDetails> add(@RequestBody RoomDetailsDTO roomDetailsDto) {
		
//		RoomDetails addedRoomDetails = roomDetailsService.addRoomDetails(roomDetails);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<RoomDetails> update(@RequestBody RoomDetails roomDetails)
			throws RoomDetailsNotFoundException {

		RoomDetails updatedRoomDetails = roomDetailsService.updateRoomDetails(roomDetails);
		return new ResponseEntity<>(updatedRoomDetails, HttpStatus.OK);

	}

	@DeleteMapping("/{roomDetailsId}")
	public ResponseEntity<RoomDetails> remove(@PathVariable int roomDetailsId)
			throws RoomDetailsNotFoundException {

		RoomDetails removeRoomDetails = roomDetailsService.removeRoomDetailsById(roomDetailsId);
		return new ResponseEntity<>(removeRoomDetails, HttpStatus.OK);

	}

	@GetMapping("/all")
	public ResponseEntity<List> findAll() {

		List roomDetailsList = (List) roomDetailsService.findAllRoomDetails();
		return new ResponseEntity<>(roomDetailsList, HttpStatus.OK);

	}

	@GetMapping("/{roomDetailsId}")
	public ResponseEntity<RoomDetails> findById(@PathVariable int roomDetailsId)
			throws RoomDetailsNotFoundException {

		RoomDetails roomDetails = roomDetailsService.findRoomDetailsById(roomDetailsId);
		return new ResponseEntity<>(roomDetails, HttpStatus.OK);

	}
}
