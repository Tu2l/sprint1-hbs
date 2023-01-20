package com.hbs.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbs.entities.RoomDetails;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.repository.RoomDetailsRepository;

@Service
public class RoomDetailsServiceImpl implements RoomDetailsService {

	private static final String MESSAGE = "Room details not found for room id: ";
	@Autowired
	private RoomDetailsRepository roomDetailsRepository;

	@Override
	public RoomDetails add(RoomDetails roomDetails) {

		return roomDetailsRepository.save(roomDetails);
	}

	@Override
	@Transactional
	public RoomDetails update(RoomDetails roomDetails) throws RoomDetailsNotFoundException {
		Optional<RoomDetails> optionalRoomDetails = roomDetailsRepository.findById(roomDetails.getRoomId());

		return optionalRoomDetails.map(rd -> roomDetailsRepository.save(roomDetails))
				.orElseThrow(() -> new RoomDetailsNotFoundException(MESSAGE + roomDetails.getRoomId()));
	}

	@Override
	public RoomDetails removeById(int id) throws RoomDetailsNotFoundException {

		RoomDetails room = findById(id);
		roomDetailsRepository.deleteById(id);
		return room;
	}

	@Override
	public List<RoomDetails> findAll() {

		return roomDetailsRepository.findAll();
	}

	@Override
	public RoomDetails findById(int roomDetailsId) throws RoomDetailsNotFoundException {
		return roomDetailsRepository.findById(roomDetailsId).orElseThrow(() -> new RoomDetailsNotFoundException(MESSAGE + roomDetailsId));
	}

	@Override
	public List<RoomDetails> findByHotelId(int hotelId) {
		return roomDetailsRepository.findByHotelId(hotelId);
	}
}
