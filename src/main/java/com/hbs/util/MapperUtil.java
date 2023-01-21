package com.hbs.util;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.hbs.dto.AdminDTO;
import com.hbs.dto.BookingDetailsDTO;
import com.hbs.dto.HotelDTO;
import com.hbs.dto.PaymentsDTO;
import com.hbs.dto.RoomDetailsDTO;
import com.hbs.dto.TransactionsDTO;
import com.hbs.dto.UserDTO;
import com.hbs.entities.Admin;
import com.hbs.entities.BookingDetails;
import com.hbs.entities.Hotel;
import com.hbs.entities.Payments;
import com.hbs.entities.RoomDetails;
import com.hbs.entities.Transactions;
import com.hbs.entities.User;

public class MapperUtil {

	private static final ModelMapper MAPPER;
	static {
		MAPPER = new ModelMapper();
	}

	private MapperUtil() {}

	// user<->userdto
	public static User mapToUser(UserDTO userDto) {
		return MAPPER.map(userDto, User.class);
	}

	public static UserDTO mapToUserDto(User user) {
		return MAPPER.map(user, UserDTO.class);
	}

	public static List<UserDTO> mapToUserDtoList(List<User> userList) {
		return MAPPER.map(userList, new TypeToken<List<UserDTO>>() {
		}.getType());
	}

	// roomdetails<->roomdetailsdto
	public static RoomDetails mapToRoomDetails(RoomDetailsDTO dto) {
		return MAPPER.map(dto, RoomDetails.class);
	}

	public static RoomDetailsDTO mapToRoomDetailsDto(RoomDetails roomDetails) {
		return MAPPER.map(roomDetails, RoomDetailsDTO.class);
	}

	public static List<RoomDetailsDTO> mapToRoomDetailsDtoList(List<RoomDetails> roomDetailsList) {
		return MAPPER.map(roomDetailsList, new TypeToken<List<RoomDetailsDTO>>() {
		}.getType());
	}

	// bookingdetails<->bookingdetailsdto
	public static BookingDetails mapToBookingDetails(BookingDetailsDTO bookingDetailsDto) {
		return MAPPER.map(bookingDetailsDto, BookingDetails.class);
	}

	public static BookingDetailsDTO mapToBookingDetailsDto(BookingDetails bookingDetails) {
		return MAPPER.map(bookingDetails, BookingDetailsDTO.class);
	}

	public static List<BookingDetailsDTO> mapToBookingDetailsDtoList(List<BookingDetails> bookingDetailsList) {
		return MAPPER.map(bookingDetailsList, new TypeToken<List<BookingDetailsDTO>>() {
		}.getType());
	}

	// payments<->paymentsdto
	public static Payments mapToPayment(PaymentsDTO paymentDto) {
		return MAPPER.map(paymentDto, Payments.class);
	}

	public static PaymentsDTO mapToPaymentDto(Payments payment) {
		return MAPPER.map(payment, PaymentsDTO.class);
	}

	// transactions<->transactionsdto
	public static Transactions mapToTransaction(TransactionsDTO transactionDto) {
		return MAPPER.map(transactionDto, Transactions.class);
	}

	public static TransactionsDTO mapToTransactionDto(Transactions transaction) {
		return MAPPER.map(transaction, TransactionsDTO.class);
	}

	public static Hotel mapToHotel(HotelDTO hotelDto) {
		return MAPPER.map(hotelDto, Hotel.class);
	}

	public static HotelDTO mapToHotelDto(Hotel hotel) {
		return MAPPER.map(hotel, HotelDTO.class);
	}

	public static List<HotelDTO> mapToHotelList(List<Hotel> hotel) {
		return MAPPER.map(hotel, new TypeToken<List<HotelDTO>>() {
		}.getType());
	}
	
	//admin 
	public static Admin mapToAdmin(AdminDTO dto) {
		return MAPPER.map(dto, Admin.class);
	}

	public static AdminDTO mapToAdminDto(Admin admin) {
		return MAPPER.map(admin, AdminDTO.class);
	}
}
