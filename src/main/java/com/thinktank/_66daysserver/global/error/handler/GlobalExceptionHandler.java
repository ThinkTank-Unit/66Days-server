package com.thinktank._66daysserver.global.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.thinktank._66daysserver.global.error.exception.CommonException;
import com.thinktank._66daysserver.global.error.exception.HabitException;
import com.thinktank._66daysserver.global.error.exception.UserException;
import com.thinktank._66daysserver.global.error.model.ErrorResponse;
import com.thinktank._66daysserver.global.error.type.CommonErrorType;
import com.thinktank._66daysserver.global.error.type.HabitErrorType;
import com.thinktank._66daysserver.global.error.type.UserErrorType;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CommonException.class)
	public ResponseEntity<ErrorResponse> handleCommonException(CommonException ex) {
		CommonErrorType errorType = ex.getErrorCode();

		ErrorResponse errorResponse = ErrorResponse.builder()
			.status(errorType.getStatus().value())
			.code(errorType.getCode())
			.message(errorType.getMessage())
			.build();

		return new ResponseEntity<>(errorResponse, errorType.getStatus());
	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorResponse> handleUserException(UserException ex) {
		UserErrorType errorType = ex.getErrorCode();

		ErrorResponse errorResponse = ErrorResponse.builder()
			.status(errorType.getStatus().value())
			.code(errorType.getCode())
			.message(errorType.getMessage())
			.build();

		return new ResponseEntity<>(errorResponse, errorType.getStatus());
	}

	@ExceptionHandler(HabitException.class)
	public ResponseEntity<ErrorResponse> handleHabitException(HabitException ex) {
		HabitErrorType errorType = ex.getErrorCode();

		ErrorResponse errorResponse = ErrorResponse.builder()
			.status(errorType.getStatus().value())
			.code(errorType.getCode())
			.message(errorType.getMessage())
			.build();

		return new ResponseEntity<>(errorResponse, errorType.getStatus());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
		CommonErrorType errorType = CommonErrorType.INVALID_ARGUMENT;

		ErrorResponse errorResponse = ErrorResponse.builder()
			.status(errorType.getStatus().value())
			.code(errorType.getCode())
			.message(ex.getMessage()) // IllegalArgumentException의 메시지를 사용
			.build();

		return new ResponseEntity<>(errorResponse, errorType.getStatus());
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException ex) {
		ErrorResponse errorResponse = ErrorResponse.builder()
			.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.code("E004")
			.message("Null pointer exception occurred")
			.build();

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
		ErrorResponse errorResponse = ErrorResponse.builder()
			.status(HttpStatus.NOT_FOUND.value())
			.code("E005")
			.message("The requested entity was not found")
			.build();

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
