package com.thinktank._66daysserver.global.error.exception;

import com.thinktank._66daysserver.global.error.type.NotificationErrorType;

import lombok.Getter;

@Getter
public class NotificationException extends RuntimeException {

	private final NotificationErrorType errorCode;

	public NotificationException(NotificationErrorType errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
