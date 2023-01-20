package com.hbs.service;

import java.util.List;


import com.hbs.entities.RoomDetails;
import com.hbs.exceptions.RoomDetailsNotFoundException;

public interface RoomDetailsService {
	
	public RoomDetails addRoomDetails(RoomDetails roomDetails);
	
	public RoomDetails updateRoomDetails(RoomDetails roomDetails)throws RoomDetailsNotFoundException;
	
	public RoomDetails removeRoomDetailsById(int roomDetailsId)throws RoomDetailsNotFoundException;
	
	public List<RoomDetails> findAllRoomDetails();
	
	public RoomDetails findRoomDetailsById(int roomDetailsId)throws RoomDetailsNotFoundException;

}
