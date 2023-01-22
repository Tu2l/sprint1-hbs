package com.hbs.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hbs.dto.RoomDetailsDTO;
import com.hbs.entities.RoomDetails;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.service.RoomDetailsService;
import com.hbs.util.FileUploadUtil;
import com.hbs.util.MapperUtil;

@RestController
@RequestMapping("/room")
public class RoomDetailsController {
	
	@Autowired
	private RoomDetailsService roomService;

	@PostMapping(consumes = { "multipart/form-data" })
	public ResponseEntity<RoomDetailsDTO> add(@Valid @ModelAttribute RoomDetailsDTO roomDetailsDto)
			throws IOException, HotelNotFoundException, RoomDetailsNotFoundException {

		RoomDetails room = MapperUtil.mapToRoomDetails(roomDetailsDto);
		MultipartFile photo = roomDetailsDto.getPhotoUpload();

		String fileName = StringUtils.cleanPath(photo.getOriginalFilename());
		String uploadDir = roomDetailsDto.getHotelId() + "/" + room.getRoomNo();
		String path = FileUploadUtil.saveFile(uploadDir, fileName, roomDetailsDto.getPhotoUpload());
		room.setImageUrl(path);
		room = roomService.add(room);

//		LOGGER.info(room);
		return new ResponseEntity<>(MapperUtil.mapToRoomDetailsDto(room), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<RoomDetailsDTO> update(@Valid @RequestBody RoomDetailsDTO roomDetailsDto,
			@PathVariable int id)
			throws RoomDetailsNotFoundException, HotelNotFoundException {

		RoomDetails room = roomService.update(MapperUtil.mapToRoomDetails(roomDetailsDto));
		room.setRoomId(id);
		
		return new ResponseEntity<>(MapperUtil.mapToRoomDetailsDto(room), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<RoomDetailsDTO> remove(@PathVariable int id) throws RoomDetailsNotFoundException {
		RoomDetails room = roomService.removeById(id);
		return new ResponseEntity<>(MapperUtil.mapToRoomDetailsDto(room), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<RoomDetailsDTO>> findAll() {
		return new ResponseEntity<>(MapperUtil.mapToRoomDetailsDtoList(roomService.findAll()), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RoomDetailsDTO> findById(@PathVariable int id) throws RoomDetailsNotFoundException {
		return new ResponseEntity<>(MapperUtil.mapToRoomDetailsDto(roomService.findById(id)), HttpStatus.OK);
	}

	@GetMapping("/byhotelid/{id}")
	public ResponseEntity<List<RoomDetailsDTO>> findByHotelId(@PathVariable int id) {
		return new ResponseEntity<>(MapperUtil.mapToRoomDetailsDtoList(roomService.findByHotelId(id)),
				HttpStatus.OK);
	}
}
