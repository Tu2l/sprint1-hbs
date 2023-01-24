package com.hbs.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hbs.entities.RoomDetails;

@Repository
public interface RoomDetailsRepository extends JpaRepository<RoomDetails, Integer> {

	@Query("SELECT room FROM RoomDetails room JOIN room.hotel hotel WHERE hotel.hotelId=:hotelId")
	List<RoomDetails> findByHotelId(@Param("hotelId") int hotelId);

	@Query("SELECT room FROM RoomDetails room JOIN room.hotel hotel WHERE hotel.hotelId=:hotelId AND room.roomId in :roomIds")
	List<RoomDetails> findByRoomIdAndHotelId(@Param("hotelId") int hotelId, @Param("roomIds") List<Integer> roomIds);

	@Query("SELECT COUNT(room) FROM RoomDetails room JOIN room.hotel hotel WHERE hotel.hotelId=:hotelId AND room.roomId in :roomIds")
	Integer findByRoomIdAndHotelIdCount(@Param("hotelId") int hotelId, @Param("roomIds") List<Integer> roomIds);

	@Query("SELECT COUNT(room) FROM RoomDetails room JOIN room.hotel hotel WHERE hotel.hotelId=:hotelId AND room.roomNo in :roomNo")
	Integer findByRoomNoAndHotelIdCount(@Param("hotelId") int hotelId, @Param("roomNo") String roomNo);

	@Query("SELECT room FROM RoomDetails room WHERE room.roomId NOT IN(SELECT r.roomId FROM BookingDetails b JOIN b.roomList r "
			+ "WHERE b.bookedFrom between :from and :to OR b.bookedTo between :from and :to)")
	List<RoomDetails> findAllAvailableRooms(@Param("from") LocalDate from, @Param("to") LocalDate to);

	@Query("SELECT COUNT(r.roomId) FROM BookingDetails b JOIN b.roomList r "
			+ "WHERE (b.bookedFrom between :from and :to OR b.bookedTo between :from and :to)"
			+ "AND r.roomId in :roomIds")
	Integer findBookedRoomsCountByDateAndRoomId(@Param("from") LocalDate from, @Param("to") LocalDate to,
			@Param("roomIds") List<Integer> roomIds);
	
	@Query("SELECT SUM(room.ratePerDay) FROM RoomDetails room WHERE room.roomId in :roomIds")
	Double calculateTotalAmount(@Param("roomIds")List<Integer> roomIds);
	
	@Query("SELECT AVG(room.ratePerDay) FROM RoomDetails room JOIN room.hotel hotel WHERE hotel.hotelId=:hotelId")
	Double calculateAvgAmountByHotelId(@Param("hotelId") int hotelId);
}
