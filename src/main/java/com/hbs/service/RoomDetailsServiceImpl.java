package com.hbs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbs.entities.RoomDetails;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.repository.HotelRepository;
import com.hbs.repository.RoomDetailsRepository;

@Service
public class RoomDetailsServiceImpl implements RoomDetailsService {
	private static final String ROOM_DETAILS_NOT_FOUND = "Room details not found for room id: ";
	private static final String HOTEL_NOT_FOUND = "Hotel not found for hotelid: ";

	@Autowired
	private RoomDetailsRepository roomRepository;
	@Autowired
	private HotelRepository hotelRepository;

	private void validateRoomDetails(RoomDetails room) throws HotelNotFoundException, RoomDetailsNotFoundException {
		// validate hotel and room
		if (!hotelRepository.existsById(room.getHotel().getHotelId()))
			throw new HotelNotFoundException(HOTEL_NOT_FOUND + room.getHotel().getHotelId());

		if (roomRepository.findByRoomNoAndHotelIdCount(room.getHotel().getHotelId(), room.getRoomNo()) != 1)
			throw new RoomDetailsNotFoundException(ROOM_DETAILS_NOT_FOUND);
	}

	@Override
	public RoomDetails add(RoomDetails roomDetails) throws HotelNotFoundException, RoomDetailsNotFoundException {
		validateRoomDetails(roomDetails);
		
		return roomRepository.save(roomDetails);
	}

	@Override
	@Transactional
	public RoomDetails update(RoomDetails roomDetails) throws RoomDetailsNotFoundException, HotelNotFoundException {
		findById(roomDetails.getRoomId());
		validateRoomDetails(roomDetails);
		return roomRepository.save(roomDetails);
	}

	@Override
	public RoomDetails removeById(int id) throws RoomDetailsNotFoundException {
		RoomDetails room = findById(id);
		roomRepository.deleteById(id);
		return room;
	}

	@Override
	public List<RoomDetails> findAll() {
		return roomRepository.findAll();
	}

	@Override
	public RoomDetails findById(int roomDetailsId) throws RoomDetailsNotFoundException {
		return roomRepository.findById(roomDetailsId)
				.orElseThrow(() -> new RoomDetailsNotFoundException(ROOM_DETAILS_NOT_FOUND + roomDetailsId));
	}

	@Override
	public List<RoomDetails> findByHotelId(int hotelId) {
		return roomRepository.findByHotelId(hotelId);
	}
}
