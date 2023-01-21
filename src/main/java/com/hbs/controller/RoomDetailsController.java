package com.hbs.controller;

import java.io.IOException;
import java.sql.SQLException;
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
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.service.RoomDetailsService;
import com.hbs.util.FileUploadUtil;
import com.hbs.util.MapperUtil;

@RestController
@RequestMapping("/roomdetails")
public class RoomDetailsController {
	private static final Logger LOGGER ;
	static {
		LOGGER = LogManager.getLogger(RoomDetailsController.class);
	}
	
	@Autowired
	private RoomDetailsService roomService;

	@PostMapping(consumes = { "multipart/form-data" })
	public ResponseEntity<RoomDetailsDTO> add(@Valid @ModelAttribute RoomDetailsDTO roomDetailsDto) throws IOException {

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

	@PutMapping
	public ResponseEntity<RoomDetailsDTO> update(@Valid @RequestBody RoomDetailsDTO roomDetailsDto)
			throws RoomDetailsNotFoundException {

		RoomDetails room = roomService.update(MapperUtil.mapToRoomDetails(roomDetailsDto));
		return new ResponseEntity<>(MapperUtil.mapToRoomDetailsDto(room), HttpStatus.OK);
	}

	@DeleteMapping("/{roomid}")
	public ResponseEntity<RoomDetailsDTO> remove(@PathVariable int roomId) throws RoomDetailsNotFoundException {
		RoomDetails room = roomService.removeById(roomId);
		return new ResponseEntity<>(MapperUtil.mapToRoomDetailsDto(room), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<RoomDetailsDTO>> findAll() {
		return new ResponseEntity<>(MapperUtil.mapToRoomDetailsDtoList(roomService.findAll()), HttpStatus.OK);
	}

	@GetMapping("/{roomId}")
	public ResponseEntity<RoomDetailsDTO> findById(@PathVariable int roomId) throws RoomDetailsNotFoundException {
		return new ResponseEntity<>(MapperUtil.mapToRoomDetailsDto(roomService.findById(roomId)), HttpStatus.OK);
	}

	@GetMapping("/byhotelid/{hotelId}")
	public ResponseEntity<List<RoomDetailsDTO>> findByHotelId(@PathVariable int hotelId) {
		return new ResponseEntity<>(MapperUtil.mapToRoomDetailsDtoList(roomService.findByHotelId(hotelId)),
				HttpStatus.OK);
	}
}
