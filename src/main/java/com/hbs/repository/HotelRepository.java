package com.hbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hbs.entities.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	Hotel findByEmail(String email);

//	@Query("SELECT hotel FROM Hotel hotel JOIN hotel.roomList room WHERE room.roomId=:roomId")
//	Optional<Hotel> findByRoomId(@Param("roomId") int roomId);
}
