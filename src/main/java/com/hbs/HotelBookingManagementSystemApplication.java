package com.hbs;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HotelBookingManagementSystemApplication {

//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder ();
//	}
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HotelBookingManagementSystemApplication.class, args);
		System.out.println("Running");
		System.err.println("Running");		
	}

/*
	@Autowired
	IUserRepository userRepo;
	@Autowired
	IHotelRepository hotelRepo;
	@Autowired
	IPaymentRepository payRepo;
	@Autowired
	IRoomDetailsRepository roomRepo;
	@Autowired
	ITransactionRepository tranRepo;
	@Autowired
	IBookingDetailsRepository bookingRepo;


	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setUserName("name");
		user.setEmail("email");
		user.setPassword("password");
		user.setRole("role");
		user.setMobile("mobile");
		user.setAddress("address");
		
		Hotel hotel = new Hotel();
		hotel.setCity("city");
		hotel.setHotelName("hotelname");
		hotel.setAddress("address");
		hotel.setDescription("description");
		hotel.setAvgRatePerDay(123);
		hotel.setEmail("email");
		hotel.setPhone1("phone1");
		hotel.setPhone2("phone2");
		hotel.setWebsite("website");
		
		RoomDetails roomDetails = new RoomDetails();
		roomDetails.setHotelId(hotel);
		roomDetails.setRoomNo("roomno");
		roomDetails.setRoomType("roomtype");
		roomDetails.setRatePerDay(123);
		hotel.setRoomList(new ArrayList<>());
		hotel.getRoomList().add(roomDetails);
		
		Transactions transaction = new Transactions();
		transaction.setAmount(123);

		Payments payment = new Payments();
		payment.setTransaction(transaction);
		
		BookingDetails booking = new BookingDetails();
		booking.setUser(user);
		booking.setHotle(hotel);
		booking.setAmount(123);
		booking.setBookedFrom(LocalDate.now());
		booking.setBookedTo(LocalDate.now());
		booking.setNoOfAdults(1);
		booking.setNoOfChildren(0);
		
		payment.setBookingDetails(booking);
		booking.setRoomsList(new ArrayList<>());
		booking.getRoomsList().add(roomDetails);
		booking.setPaymentList(new ArrayList<>());
		booking.getPaymentList().add(payment);
		
		userRepo.save(user);
		hotelRepo.save(hotel);
		//tranRepo.save(transaction);
		payRepo.save(payment);
		
		//bookingRepo.save(booking);

	}
	*/

}
