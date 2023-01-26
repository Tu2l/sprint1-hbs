package com.hbs.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequestMapping("/")
public class GlobalExceptionHandler {

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { HotelNotFoundException.class, 
								RoomDetailsNotFoundException.class,
								BookingDetailsNotFoundException.class, 
								PaymentsNotFoundException.class, 
								UserNotFoundException.class,
								AdminNotFoundException.class })
	public ErrorResponse handleNotFound(Exception ex, HttpServletRequest req) {
		return new ErrorResponse(ex.getMessage(), req.getRequestURI(), LocalDateTime.now().toString());
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { MethodArgumentNotValidException.class, 
								InvalidEmailFormatException.class,
								InvalidMobileNumberFormatException.class, 
								AdminAlreadyExistsException.class,
								UserAlreadyExistsException.class ,
								InvalidImageFormatException.class,
								RoomAlreadyBookedException.class,
								ActiveBookingFoundException.class})
	public ErrorResponse handleValidationError(Exception ex, HttpServletRequest req) {
		String msg = ex.getMessage();

		if (ex instanceof MethodArgumentNotValidException) {
			FieldError error = ((MethodArgumentNotValidException) ex).getFieldError();
			if (error != null)
				msg = error.getDefaultMessage();
		}

		return new ErrorResponse(msg, req.getRequestURI(), LocalDateTime.now().toString());
	}

	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = { InvalidCredentialsException.class })
	public ErrorResponse handleAuthenticationException(Exception ex, HttpServletRequest req) {
		return new ErrorResponse(ex.getMessage(), req.getRequestURI(), LocalDateTime.now().toString());
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = { Exception.class })
	public ErrorResponse handleError(Exception ex, HttpServletRequest req) {
		return new ErrorResponse(ex.getMessage(), req.getRequestURI(), LocalDateTime.now().toString());
	}
}
