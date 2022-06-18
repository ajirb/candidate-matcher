package com.candidate.finder.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.candidate.finder.dto.ExceptionResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = { ValidationException.class })
	protected ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
		ex.printStackTrace();
		ExceptionResponse bodyOfResponse = new ExceptionResponse(ex.getMessage());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}
	
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		ex.printStackTrace();
		ExceptionResponse bodyOfResponse = new ExceptionResponse(ex.getMessage());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}
}
