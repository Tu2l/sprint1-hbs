package com.hbs.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hbs.dto.RoomDetailsDTO;
import com.hbs.entities.RoomDetails;
import com.hbs.repository.BookingDetailsRepository;
import com.hbs.repository.HotelRepository;
import com.hbs.repository.RoomDetailsRepository;
import com.hbs.util.MapperUtil;

@ExtendWith(MockitoExtension.class)
class RoomDetailsServiceImplTest {

	@Mock
	private BookingDetailsRepository bookingRepository;
	@Mock
	private RoomDetailsRepository roomRepository;
	@Mock
	private HotelRepository hotelRepository;
	@InjectMocks
	private RoomDetailsServiceImpl roomService;

	private MockedStatic<MapperUtil> mockedMapperUtil;

	private RoomDetailsDTO dto;
	private RoomDetails room;
	private List<RoomDetailsDTO> dtos = new ArrayList<>();
	private List<RoomDetails> rooms = new ArrayList<>();

	@BeforeEach
	void setup() {
		dto = new RoomDetailsDTO();
		dto.setHotelId(1);
		dto.setImageUrl("url");
		dto.setRatePerDay(100);
		dto.setRoomId(13);
		dto.setRoomNo("a23");
		dto.setRoomType("delux");

		room = MapperUtil.mapToRoomDetails(dto);
		dtos.add(dto);

		rooms.add(room);
	}

	@AfterEach
	void close() {
		if (mockedMapperUtil != null)
			mockedMapperUtil.close();
	}

	@Disabled
	@Test
	void testAdd() {
		try {
			mockedMapperUtil = mockStatic(MapperUtil.class);
//			when(MapperUtil.mapToRoomDetails(dto)).thenReturn(room);
//			when(MapperUtil.mapToRoomDetailsDto(room)).thenReturn(dto);
//			when(roomRepository.findById(dto.getRoomId())).thenReturn(Optional.of(room));
			when(hotelRepository.existsById(dto.getHotelId())).thenReturn(true);
			when(roomService.add(dto)).thenReturn(dto)
					.thenAnswer(i -> hotelRepository.existsById(dto.getHotelId()));
			assertEquals(dto, roomService.add(dto));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

//	@Test
//	void testUpdate() {
//		try {
//			mockedMapperUtil = mockStatic(MapperUtil.class);
//			when(MapperUtil.mapToRoomDetailsDto(room)).thenReturn(dto);
////			when(hotelRepository.findById(dto.getHotelId()));
//			lenient().when(roomRepository.findById(dto.getRoomId())).thenReturn(Optional.of(room))
//					.then(i -> roomService.remove(dto.getRoomId()));
////			when(roomService.remove(dto.getRoomId())).thenReturn(dto);
//
//			assertEquals(dto, roomService.update(dto.getRoomId()));
//
//		} catch (Exception e) {
//			fail(e.getMessage());
//		}
//	}

	@Disabled
	@Test
	void testRemove() {
		try {
			mockedMapperUtil = mockStatic(MapperUtil.class);
			when(MapperUtil.mapToRoomDetailsDto(room)).thenReturn(dto);
//			when(hotelRepository.findById(dto.getHotelId()));
			lenient().when(roomRepository.findById(dto.getRoomId())).thenReturn(Optional.of(room))
					.then(i -> roomService.remove(dto.getRoomId()));
//			when(roomService.remove(dto.getRoomId())).thenReturn(dto);

			assertEquals(dto, roomService.remove(dto.getRoomId()));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testFindAll() {
		try {
			mockedMapperUtil = mockStatic(MapperUtil.class);
			when(MapperUtil.mapToRoomDetailsDtoList(new ArrayList<>())).thenReturn(dtos);
			when(roomService.findAll()).thenReturn(dtos).thenAnswer(i -> roomService.findAll());
			assertEquals(dtos, roomService.findAll());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testFindById() {
		try {
			mockedMapperUtil = mockStatic(MapperUtil.class);
			when(MapperUtil.mapToRoomDetailsDto(room)).thenReturn(dto);
			when(roomRepository.findById(dto.getRoomId())).thenReturn(Optional.of(room));
			when(roomService.findById(dto.getRoomId())).thenReturn(dto);

			assertEquals(dto, roomService.findById(dto.getRoomId()));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testFindByHotelId() {
		try {
			mockedMapperUtil = mockStatic(MapperUtil.class);
			when(MapperUtil.mapToRoomDetailsDto(room)).thenReturn(dto);
			when(roomRepository.findByHotelId(dto.getHotelId())).thenReturn(rooms);
			when(roomService.findByHotelId(dto.getHotelId())).thenReturn(dtos);

			assertEquals(dtos, roomService.findByHotelId(dto.getHotelId()));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testFindAllAvailableRoom() {
		try {
			mockedMapperUtil = mockStatic(MapperUtil.class);
			when(MapperUtil.mapToRoomDetailsDto(room)).thenReturn(dto);
			when(roomRepository.findAllAvailableRooms(LocalDate.now(), LocalDate.now())).thenReturn(rooms);
			when(roomService.findAllAvailableRoom(LocalDate.now(), LocalDate.now())).thenReturn(dtos);

			assertEquals(dtos, roomService.findAllAvailableRoom(LocalDate.now(), LocalDate.now()));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
