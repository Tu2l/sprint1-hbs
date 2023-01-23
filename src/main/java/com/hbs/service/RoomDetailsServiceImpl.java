package com.hbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbs.dto.RoomDetailsDTO;
import com.hbs.entities.RoomDetails;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.repository.HotelRepository;
import com.hbs.repository.RoomDetailsRepository;
import com.hbs.util.MapperUtil;

@Service
public class RoomDetailsServiceImpl implements RoomDetailsService {
	private static final String ROOM_DETAILS_NOT_FOUND = "Room details not found for room id: ";
	private static final String HOTEL_NOT_FOUND = "Hotel not found for hotelid: ";

	@Autowired
	private RoomDetailsRepository roomRepository;
	@Autowired
	private HotelRepository hotelRepository;

	private void validateRoomDetails(RoomDetailsDTO room) throws HotelNotFoundException, RoomDetailsNotFoundException {
		// validate hotel and room
		if (!hotelRepository.existsById(room.getHotelId()))
			throw new HotelNotFoundException(HOTEL_NOT_FOUND + room.getHotelId());

		if (roomRepository.findByRoomNoAndHotelIdCount(room.getHotelId(), room.getRoomNo()) != 1)
			throw new RoomDetailsNotFoundException(ROOM_DETAILS_NOT_FOUND);
	}

	@Override
	public RoomDetailsDTO add(RoomDetailsDTO dto) throws HotelNotFoundException, RoomDetailsNotFoundException {
		validateRoomDetails(dto);

		return MapperUtil.mapToRoomDetailsDto(roomRepository.save(MapperUtil.mapToRoomDetails(dto)));
	}

	@Override
	public RoomDetailsDTO update(RoomDetailsDTO dto) throws RoomDetailsNotFoundException, HotelNotFoundException {
		findById(dto.getRoomId());
		validateRoomDetails(dto);
		return MapperUtil.mapToRoomDetailsDto(roomRepository.save(MapperUtil.mapToRoomDetails(dto)));
	}

	@Override
	public RoomDetailsDTO removeById(int id) throws RoomDetailsNotFoundException {
		RoomDetailsDTO room = findById(id);
		roomRepository.deleteById(id);
		return room;
	}

	@Override
	public List<RoomDetailsDTO> findAll() {
		return MapperUtil.mapToRoomDetailsDtoList(roomRepository.findAll());
	}

	@Override
	public RoomDetailsDTO findById(int roomDetailsId) throws RoomDetailsNotFoundException {
		RoomDetails roomDetails = roomRepository.findById(roomDetailsId)
				.orElseThrow(() -> new RoomDetailsNotFoundException(ROOM_DETAILS_NOT_FOUND + roomDetailsId));
		return MapperUtil.mapToRoomDetailsDto(roomDetails);
	}

	@Override
	public List<RoomDetailsDTO> findByHotelId(int hotelId) {
		return MapperUtil.mapToRoomDetailsDtoList(roomRepository.findByHotelId(hotelId));
	}
}
