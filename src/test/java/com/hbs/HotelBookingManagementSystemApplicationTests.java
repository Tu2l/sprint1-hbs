package com.hbs;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hbs.controller.AdminController;
import com.hbs.controller.AuthController;
import com.hbs.controller.BookingDetailsController;
import com.hbs.controller.HotelController;
import com.hbs.controller.PaymentController;
import com.hbs.controller.TransactionController;
import com.hbs.controller.UserController;
import com.hbs.service.AdminService;
import com.hbs.service.BookingDetailsService;
import com.hbs.service.HotelService;
import com.hbs.service.JwtService;
import com.hbs.service.PaymentService;
import com.hbs.service.TransactionService;
import com.hbs.service.UserService;

@SpringBootTest
class HotelBookingManagementSystemApplicationTests {

	@Autowired
	private AdminController adminController;
	@Autowired
	private AuthController authController;
	@Autowired
	private BookingDetailsController bookingController;
	@Autowired
	private HotelController hotelController;
	@Autowired
	private PaymentController paymentController;
	@Autowired
	private TransactionController transactionController;
	@Autowired
	private UserController userController;
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private BookingDetailsService bookingService;
	@Autowired
	private HotelService hotelService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private UserService userService;
	
	@Test
	void contextLoads() {
		assertNotNull(adminController);
		assertNotNull(authController);
		assertNotNull(bookingController);
		assertNotNull(hotelController);
		assertNotNull(paymentController);
		assertNotNull(transactionController);
		assertNotNull(userController);
		
		assertNotNull(adminService);
		assertNotNull(jwtService);
		assertNotNull(bookingService);
		assertNotNull(hotelService);
		assertNotNull(paymentService);
		assertNotNull(transactionService);
		assertNotNull(userService);
	}

}
