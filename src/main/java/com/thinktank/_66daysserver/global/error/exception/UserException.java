package com.thinktank._66daysserver.global.error.exception;

import com.thinktank._66daysserver.global.error.type.UserErrorType;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

	private final UserErrorType errorCode;

	public UserException(UserErrorType errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
