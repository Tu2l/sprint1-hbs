package com.hbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hbs.entities.BookingDetails;

@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Integer> {

	@Query("SELECT booking FROM BookingDetails booking JOIN booking.roomList room WHERE room.roomId=:roomId")
	List<BookingDetails> findAllByRoomId(@Param("roomId") int roomId);

}
