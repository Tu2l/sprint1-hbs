package com.hbs.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hbs.auth.JwtRequest;
import com.hbs.auth.JwtResponse;
import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.exceptions.InvalidCredentialsException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.service.AdminService;
import com.hbs.service.UserService;



@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    

    @Mock
    private AdminService adminService;

    @Mock
    private UserService userService;
    
    @InjectMocks
    private AuthController authController;;

    private JwtRequest jwtRequest;
    private JwtResponse jwtResponse;

    @BeforeEach
    public void setUp() {
        jwtRequest = new JwtRequest("check1@email.com", "12345678");
        jwtResponse = new JwtResponse("token", "check1@email.com");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void adminSignIn_validCredentials_returnsJwtResponse() throws AdminNotFoundException, InvalidCredentialsException {
        when(adminService.signIn(jwtRequest)).thenReturn(jwtResponse);

        ResponseEntity<JwtResponse> response = authController.adminSignIn(jwtRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jwtResponse, response.getBody());
    }

    @Test
    void userSignIn_validCredentials_returnsJwtResponse() throws InvalidCredentialsException, UserNotFoundException {
        when(userService.signIn(jwtRequest)).thenReturn(jwtResponse);
        ResponseEntity<JwtResponse> response = authController.userSignIn(jwtRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
