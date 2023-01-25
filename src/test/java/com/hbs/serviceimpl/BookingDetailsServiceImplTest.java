package com.hbs.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hbs.dto.BookingDetailsDTO;
import com.hbs.exceptions.BookingDetailsNotFoundException;
import com.hbs.exceptions.RoomAlreadyBookedException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.repository.BookingDetailsRepository;
import com.hbs.repository.HotelRepository;
import com.hbs.repository.RoomDetailsRepository;
import com.hbs.repository.UserRepository;
@ExtendWith(MockitoExtension.class)
class BookingDetailsServiceImplTest {

    @Mock
    private BookingDetailsRepository bookingDetailsRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private HotelRepository hotelRepository;
    @Mock
    private RoomDetailsRepository roomRepository;

    @InjectMocks
    private BookingDetailsServiceImpl bookingDetailsService;

    private BookingDetailsDTO dto1,dto2;
//    private BookingDetails booking;   

    @BeforeEach
    void setUp() {
        dto1 = new BookingDetailsDTO();
        dto1.setBookingId(1);
        dto1.setUserId(1);
        dto1.setHotelId(1);
        dto1.setRoomIds(Arrays.asList(1, 2, 3));
        dto1.setBookedFrom(LocalDate.now());
        dto1.setBookedTo(LocalDate.now().plusDays(2));
        dto1.setAmount(1000);
        dto1.setPayments(Arrays.asList(100.00,200.00,300.00));
        dto2 = new BookingDetailsDTO();
        dto2.setBookingId(2);
        dto2.setUserId(2);
        dto2.setHotelId(2);
        dto2.setRoomIds(Arrays.asList(1, 2, 3));
        dto2.setBookedFrom(LocalDate.now());
        dto2.setBookedTo(LocalDate.now().plusDays(2));
        dto2.setAmount(1000);
        dto2.setPayments(Arrays.asList(100.00,200.00,300.00));
//        booking = MapperUtil.mapToBookingDetails(dto1);
    }

    @Test
    void testAddBookingDetailsUserNotFoundException() {
        when(userRepository.existsById(dto1.getUserId())).thenReturn(false);
        assertThrows(UserNotFoundException.class, () -> bookingDetailsService.add(dto1));
    }

    @Test
    void testAddBookingDetailsRoomDetailsNotFoundException() {
        when(userRepository.existsById(dto1.getUserId())).thenReturn(true);
        when(hotelRepository.existsById(dto1.getHotelId())).thenReturn(true);
        when(roomRepository.findByRoomIdAndHotelIdCount(dto1.getHotelId(), dto1.getRoomIds()))
                .thenReturn(dto1.getRoomIds().size() - 1);
        assertThrows(RoomDetailsNotFoundException.class, () -> bookingDetailsService.add(dto1));
    }

    @Test
    void testAddBookingDetailsRoomAlreadyBookedException() {
        when(userRepository.existsById(dto1.getUserId())).thenReturn(true);
        when(hotelRepository.existsById(dto1.getHotelId())).thenReturn(true);
        when(roomRepository.findByRoomIdAndHotelIdCount(dto1.getHotelId(), dto1.getRoomIds()))
                .thenReturn(dto1.getRoomIds().size());
        when(roomRepository.findBookedRoomsCountByDateAndRoomId(dto1.getBookedFromDate(), dto1.getBookedToDate(),
                dto1.getRoomIds())).thenReturn(1);
        assertThrows(RoomAlreadyBookedException.class, () -> bookingDetailsService.add(dto1));
    }

    @Test
    void testGetBookingDetailsBookingDetailsNotFoundException() {
        when(bookingDetailsRepository.findById(dto1.getBookingId())).thenReturn(Optional.empty());
        assertThrows(BookingDetailsNotFoundException.class, () -> bookingDetailsService.findById(dto1.getBookingId()));
    }
   
}
