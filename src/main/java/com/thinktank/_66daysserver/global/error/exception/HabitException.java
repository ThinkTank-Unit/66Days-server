package com.thinktank._66daysserver.global.error.exception;

import com.thinktank._66daysserver.global.error.type.HabitErrorType;

import lombok.Getter;

@Getter
public class HabitException extends RuntimeException {

	private final HabitErrorType errorCode;

	public HabitException(HabitErrorType errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
