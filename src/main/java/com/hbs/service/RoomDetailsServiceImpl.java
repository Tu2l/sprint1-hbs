package com.hbs.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbs.entities.RoomDetails;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.repository.IRoomDetailsRepository;

@Service
public class RoomDetailsServiceImpl implements RoomDetailsService {

	@Autowired
	private IRoomDetailsRepository roomDetailsRepository;
	// private static final String MESSAGE = "Room details not found for room id: ";

	@Override
	public RoomDetails addRoomDetails(RoomDetails roomDetails) {

		return roomDetailsRepository.save(roomDetails);
	}

	@Override
	@Transactional
	public RoomDetails updateRoomDetails(RoomDetails roomDetails) throws RoomDetailsNotFoundException {
		Optional<RoomDetails> optionalRoomDetails = roomDetailsRepository.findById(roomDetails.getRoomId());

		return optionalRoomDetails.map(rd -> roomDetailsRepository.save(roomDetails))
				.orElseThrow(() -> new RoomDetailsNotFoundException());
	}

	@Override
	public RoomDetails removeRoomDetailsById(int id) throws RoomDetailsNotFoundException {

		RoomDetails room = findRoomDetailsById(id);
		roomDetailsRepository.deleteById(id);
		return room;
	}

	@Override
	public List<RoomDetails> findAllRoomDetails() {

		return roomDetailsRepository.findAll();
	}

	@Override
	public RoomDetails findRoomDetailsById(int roomDetailsId) throws RoomDetailsNotFoundException {
		return roomDetailsRepository.findById(roomDetailsId).orElseThrow(() -> new RoomDetailsNotFoundException());
	}
}
