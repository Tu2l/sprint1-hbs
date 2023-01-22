package com.hbs.repository;

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

	@Query("SELECT room FROM RoomDetails room JOIN room.hotel hotel WHERE hotel.hotelId=:hotelId AND room.roomId=:roomIds")
	List<RoomDetails> findByRoomIdAndHotelId(@Param("hotelId") int hotelId, @Param("roomIds") List<Integer> roomIds);
	
	@Query("SELECT COUNT(room) FROM RoomDetails room JOIN room.hotel hotel WHERE hotel.hotelId=:hotelId AND room.roomId=:roomIds")
	Integer findByRoomIdAndHotelIdCount(@Param("hotelId") int hotelId, @Param("roomIds") List<Integer> roomIds);


	@Query("SELECT COUNT(room) FROM RoomDetails room JOIN room.hotel hotel WHERE hotel.hotelId=:hotelId AND room.roomNo=:roomNo")
	Integer findByRoomNoAndHotelIdCount(@Param("hotelId") int hotelId, @Param("roomNo")String roomNo);
}
