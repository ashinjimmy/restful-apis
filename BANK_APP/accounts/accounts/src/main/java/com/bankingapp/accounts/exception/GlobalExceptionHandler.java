package com.bankingapp.accounts.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.bankingapp.accounts.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleCustomerException(CustomerAlreadyExistsException exception,
			WebRequest request) {

		/*
		 * private String apiPath; private HttpStatus errorCode; private String
		 * errorMessage; private LocalDateTime errorTime;
		 */
		ErrorResponseDto errorResponse = new ErrorResponseDto(request.getDescription(false), HttpStatus.BAD_REQUEST,
				exception.getMessage(), LocalDateTime.now());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
