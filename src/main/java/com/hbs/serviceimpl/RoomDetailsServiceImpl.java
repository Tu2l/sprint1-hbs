package com.hbs.serviceimpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbs.dto.RoomDetailsDTO;
import com.hbs.dto.RoomImage;
import com.hbs.entities.BookingDetails;
import com.hbs.entities.Hotel;
import com.hbs.entities.RoomDetails;
import com.hbs.exceptions.ActiveBookingFoundException;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.InvalidImageFormatException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.repository.BookingDetailsRepository;
import com.hbs.repository.HotelRepository;
import com.hbs.repository.RoomDetailsRepository;
import com.hbs.service.RoomDetailsService;
import com.hbs.util.FileUploadUtil;
import com.hbs.util.MapperUtil;

@Service
public class RoomDetailsServiceImpl implements RoomDetailsService {
	private static final String ROOM_DETAILS_FOUND = "Room details already exists for room no: ";
	private static final String ROOM_DETAILS_NOT_FOUND = "Room details not found for roomid: ";
	private static final String HOTEL_NOT_FOUND = "Hotel not found for hotelId: ";
	private static final String ACTIVE_BOOKING_FOUND_MESSAGE = "Active booking found";

	@Autowired
	private RoomDetailsRepository roomRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private BookingDetailsRepository bookingRepository;

	private void validateRoomDetails(RoomDetailsDTO room) throws HotelNotFoundException, RoomDetailsNotFoundException {
		// validate hotel and room
		if (!hotelRepository.existsById(room.getHotelId()))
			throw new HotelNotFoundException(HOTEL_NOT_FOUND + room.getHotelId());

		if (roomRepository.findByRoomNoAndHotelIdCount(room.getHotelId(), room.getRoomNo()) != 0)
			throw new RoomDetailsNotFoundException(ROOM_DETAILS_FOUND + room.getRoomNo());
	}

	private void updateHotelAvgPrice(int hotelId) throws HotelNotFoundException {
		// updating average price
		Hotel hotel = hotelRepository.findById(hotelId)
				.orElseThrow(() -> new HotelNotFoundException(HOTEL_NOT_FOUND + hotelId));
		hotel.setAvgRatePerDay(roomRepository.calculateAvgAmountByHotelId(hotelId));
		hotelRepository.save(hotel);
	}

	@Override
	public RoomDetailsDTO add(RoomDetailsDTO dto) throws HotelNotFoundException, RoomDetailsNotFoundException {
		validateRoomDetails(dto);

		RoomDetails room = MapperUtil.mapToRoomDetails(dto);
		room = roomRepository.save(room);

		updateHotelAvgPrice(dto.getHotelId());

		return MapperUtil.mapToRoomDetailsDto(room);
	}

	@Override
	public RoomDetailsDTO update(RoomDetailsDTO dto) throws RoomDetailsNotFoundException, HotelNotFoundException {
		validateRoomDetails(dto);

		RoomDetails room = roomRepository.save(MapperUtil.mapToRoomDetails(dto));
		updateHotelAvgPrice(dto.getHotelId());

		return MapperUtil.mapToRoomDetailsDto(room);
	}

	@Override
	public RoomDetailsDTO remove(int id)
			throws RoomDetailsNotFoundException, ActiveBookingFoundException, HotelNotFoundException {
		RoomDetailsDTO dto = findById(id);

		if (bookingRepository.findByDateAndRoomIdCount(id, LocalDate.now()) > 0)
			throw new ActiveBookingFoundException(ACTIVE_BOOKING_FOUND_MESSAGE);

		// remove all the references
		// removing references from booking_rooms
		List<BookingDetails> bookings = bookingRepository.findByRoomId(id);
		for (BookingDetails booking : bookings)
			booking.getRoomList().removeIf(room -> room.getRoomId() == id);

		bookingRepository.saveAll(bookings);

		roomRepository.deleteById(id);

		updateHotelAvgPrice(dto.getHotelId());

		FileUploadUtil.deleteImage(dto.getImageUrl());

		return dto;
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

	@Override
	public List<RoomDetailsDTO> findAllAvailableRoom(LocalDate from, LocalDate to) {
		return MapperUtil.mapToRoomDetailsDtoList(roomRepository.findAllAvailableRooms(from, to));
	}

	@Override
	public RoomDetailsDTO uploadImage(RoomImage image)
			throws RoomDetailsNotFoundException, InvalidImageFormatException, IOException {
		RoomDetails room = roomRepository.findById(image.getRoomId())
				.orElseThrow(() -> new RoomDetailsNotFoundException(ROOM_DETAILS_NOT_FOUND + image.getRoomId()));

		String oldImagePath = room.getImageUrl();

		String uploadDir = "HID" + room.getHotel().getHotelId() + "/RNO" + room.getRoomNo();
		String path = FileUploadUtil.prepareImage(image.getImage(), uploadDir);
		room.setImageUrl(path);

		if (oldImagePath != null && !oldImagePath.equals(path))
			FileUploadUtil.deleteImage(oldImagePath);

		room = roomRepository.save(room);

		return MapperUtil.mapToRoomDetailsDto(room);
	}
}
