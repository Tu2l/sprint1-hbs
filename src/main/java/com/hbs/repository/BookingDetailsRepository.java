package com.hbs.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hbs.entities.BookingDetails;

@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Integer> {

	@Query("SELECT booking FROM BookingDetails booking JOIN booking.roomList room WHERE room.roomId=:roomId")
	List<BookingDetails> findByRoomId(@Param("roomId") int roomId);

	@Query("SELECT booking FROM BookingDetails booking JOIN booking.roomList room WHERE room.roomId=:roomId"
			+ " AND booking.bookedTo >=:date")
	List<BookingDetails> findByDateAndRoomId(@Param("date") LocalDate date, @Param("roomId") int roomId);
	
	@Query("SELECT COUNT(booking) FROM BookingDetails booking JOIN booking.roomList room WHERE room.roomId=:roomId"
			+ " AND booking.bookedTo >=:date")
	Integer findByDateAndRoomIdCount(@Param("roomId") int roomId,@Param("date") LocalDate date);

	@Query("SELECT booking FROM BookingDetails booking WHERE booking.bookedTo >=:date AND booking.bookingId=:bookingId")
	List<BookingDetails> findByDate(@Param("date") LocalDate date, @Param("bookingId") int bookingId);

	@Query("SELECT COUNT(booking) FROM BookingDetails booking WHERE booking.bookedTo >=:date AND booking.bookingId=:bookingId")
	Integer findCountByDate(@Param("date") LocalDate date, @Param("bookingId") int bookingId);

	@Query("SELECT booking FROM BookingDetails booking WHERE booking.bookedTo >=:date AND booking.user.userId=:userId")
	List<BookingDetails> findByDateAndUserId(@Param("date") LocalDate date, @Param("userId") int userId);

	@Query("SELECT COUNT(booking) FROM BookingDetails booking WHERE booking.bookedTo >=:date AND booking.user.userId=:userId")
	Integer findByDateAndUserIdCount(@Param("date") LocalDate date, @Param("userId") int userId);

	@Query("SELECT booking FROM BookingDetails booking WHERE booking.bookedTo >=:date AND booking.hotel.hotelId=:hotelId")
	List<BookingDetails> findByDateAndHotelId(@Param("date") LocalDate date, @Param("hotelId") int hotelId);

	@Query("SELECT COUNT(booking) FROM BookingDetails booking WHERE booking.bookedTo >=:date AND booking.hotel.hotelId=:hotelId")
	Integer findByDateAndHotelIdCount(@Param("date") LocalDate date, @Param("hotelId") int hotelId);

	@Query("SELECT booking FROM BookingDetails booking WHERE booking.user.userId=:userId")
	List<BookingDetails> findByUserId(@Param("userId") int userId);


}
