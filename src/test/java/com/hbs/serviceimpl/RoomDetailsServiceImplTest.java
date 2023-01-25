package com.hbs.serviceimpl;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.hbs.repository.HotelRepository;
import com.hbs.repository.RoomDetailsRepository;

class RoomDetailsServiceImplTest {

	@Mock
	private RoomDetailsRepository roomRepository;

	@Mock
	private HotelRepository hotelRepository;

	@InjectMocks
	private RoomDetailsServiceImpl roomDetailsService;

	@BeforeEach
	void setup() {
//		MockitoAnnotations.openMocks(this);
	}
	
//	@Test
//	void testAddRoom() throws HotelNotFoundException, RoomDetailsNotFoundException {
//		mockStatic(MapperUtil.class);
//	    RoomDetailsDTO room = new RoomDetailsDTO();
//	    room.setHotelId(1);
//	    room.setRoomNo("101");
//	    room.setRatePerDay(100);
//	    RoomDetails roomEntity = MapperUtil.mapToRoomDetails(room);
//	    when(hotelRepository.existsById(1)).thenReturn(true);
//	    when(roomRepository.findByRoomNoAndHotelIdCount(1, "101")).thenReturn(0);
//	    when(roomRepository.save(roomEntity)).thenReturn(roomEntity);
//	    when(roomRepository.calculateAvgAmountByHotelId(1)).thenReturn(100.0);
//	    RoomDetailsDTO addedRoom = roomDetailsService.add(room);
//	    assertNotNull(addedRoom);
//	    assertEquals(addedRoom.getRoomNo(), room.getRoomNo());
//	    assertEquals(addedRoom.getHotelId(), room.getHotelId());
//	    assertEquals(addedRoom.getRatePerDay(), room.getRatePerDay());
//	    verify(hotelRepository, times(1)).existsById(1);
//	    verify(roomRepository, times(1)).findByRoomNoAndHotelIdCount(1, "101");
//	    verify(roomRepository, times(1)).save(roomEntity);
//	    verify(roomRepository, times(1)).calculateAvgAmountByHotelId(1);
//	}
}
