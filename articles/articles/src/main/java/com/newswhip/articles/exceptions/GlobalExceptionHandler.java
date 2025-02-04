package com.newswhip.articles.exceptions;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.newswhip.articles.dto.ErrorResponseDto;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ArticleAlreadyExistException.class)
	public ResponseEntity<ErrorResponseDto> handleArticleAlreadyExistException(ArticleAlreadyExistException exception,
			WebRequest webRequest) {

		ErrorResponseDto errorDto = new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_REQUEST,
				exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest webRequest) {

		ErrorResponseDto errorDto = new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_REQUEST,
				exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception, WebRequest webRequest) {

		ErrorResponseDto errorDto = new ErrorResponseDto(webRequest.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(ConstraintViolationException exception,
			WebRequest webRequest) {
		String message = exception.getConstraintViolations().stream().map(violation -> violation.getMessage()) 
				.collect(Collectors.joining(", "));

		ErrorResponseDto errorDto = new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_REQUEST,
				message, LocalDateTime.now());

		return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
	}

}
