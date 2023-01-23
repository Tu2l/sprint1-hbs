package com.hbs.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

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
import com.hbs.exceptions.InvalidImageFormatException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.service.RoomDetailsService;
import com.hbs.util.FileUploadUtil;
import com.hbs.util.MapperUtil;
import com.hbs.util.ValidationUtil;

@RestController
@RequestMapping("/room")
public class RoomDetailsController {

	@Autowired
	private RoomDetailsService roomService;

	@PostMapping(consumes = { "multipart/form-data" })
	public ResponseEntity<RoomDetailsDTO> add(@Valid @ModelAttribute RoomDetailsDTO roomDetailsDto)
			throws IOException, HotelNotFoundException, RoomDetailsNotFoundException, InvalidImageFormatException {

		RoomDetails room = MapperUtil.mapToRoomDetails(roomDetailsDto);
		MultipartFile photo = roomDetailsDto.getPhotoUpload();

		String fileName = StringUtils.cleanPath(photo.getOriginalFilename());
		StringBuilder sb = new StringBuilder(fileName);
		String ext = sb.substring(sb.lastIndexOf("."));
		if (!ValidationUtil.validateImageExtension(ext))
			throw new InvalidImageFormatException("Invalid Image file");

		String uploadDir = roomDetailsDto.getHotelId() + "/" + room.getRoomNo();
		String path = FileUploadUtil.saveFile(uploadDir, fileName, roomDetailsDto.getPhotoUpload());
		roomDetailsDto.setImageUrl(path);
		return new ResponseEntity<>(roomService.add(roomDetailsDto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<RoomDetailsDTO> update(@Valid @RequestBody RoomDetailsDTO roomDetailsDto,
			@PathVariable int id) throws RoomDetailsNotFoundException, HotelNotFoundException {
		roomDetailsDto.setRoomId(id);
		return new ResponseEntity<>(roomService.update(roomDetailsDto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<RoomDetailsDTO> remove(@PathVariable int id) throws RoomDetailsNotFoundException {
		return new ResponseEntity<>(roomService.removeById(id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<RoomDetailsDTO>> findAll() {
		return new ResponseEntity<>(roomService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RoomDetailsDTO> findById(@PathVariable int id) throws RoomDetailsNotFoundException {
		return new ResponseEntity<>(roomService.findById(id), HttpStatus.OK);
	}

	@GetMapping("/byhotelid/{id}")
	public ResponseEntity<List<RoomDetailsDTO>> findByHotelId(@PathVariable int id) {
		return new ResponseEntity<>((roomService.findByHotelId(id)), HttpStatus.OK);
	}
}
