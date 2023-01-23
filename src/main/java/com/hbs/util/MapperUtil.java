package com.hbs.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.hbs.auth.JwtResponse;
import com.hbs.dto.BookingDetailsDTO;
import com.hbs.dto.HotelDTO;
import com.hbs.dto.PaymentsDTO;
import com.hbs.dto.RoomDetailsDTO;
import com.hbs.dto.TransactionsDTO;
import com.hbs.dto.UserDTO;
import com.hbs.entities.BookingDetails;
import com.hbs.entities.Hotel;
import com.hbs.entities.JwtToken;
import com.hbs.entities.Payments;
import com.hbs.entities.RoomDetails;
import com.hbs.entities.Transactions;
import com.hbs.entities.User;

public class MapperUtil {

	private static final ModelMapper MAPPER;
	static {
		MAPPER = new ModelMapper();
	}

	private MapperUtil() {
	
	}

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
	public static BookingDetails mapToBookingDetails(BookingDetailsDTO dto) {
		BookingDetails booking = MAPPER.map(dto, BookingDetails.class);
	
		List<RoomDetails> roomList = dto.getRoomIds().stream().map((id) -> {
			RoomDetails room = new RoomDetails();
			room.setRoomId(id);
			return room;
		}).collect(Collectors.toList());

		booking.setRoomList(roomList);

		List<Payments> payments = dto.getPayments().stream().map((amount) -> {
			Payments payment = new Payments();
			payment.setAmount(amount);
			return payment;
		}).collect(Collectors.toList());

		booking.setPaymentList(payments);

		return booking;
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

	public static JwtResponse mapToJwtResponse(JwtToken jwt) {
		return MAPPER.map(jwt, JwtResponse.class);
	}
}
