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
	
}
