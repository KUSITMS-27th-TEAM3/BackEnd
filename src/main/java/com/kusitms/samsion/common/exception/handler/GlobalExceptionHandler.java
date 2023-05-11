package com.kusitms.samsion.common.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kusitms.samsion.common.exception.BusinessException;
import com.kusitms.samsion.common.exception.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleJwtException(BusinessException e) {
		final ErrorResponse errorResponse = ErrorResponse.from(e);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
