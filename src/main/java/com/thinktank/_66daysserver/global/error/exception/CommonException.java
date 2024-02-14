package com.thinktank._66daysserver.global.error.exception;

import com.thinktank._66daysserver.global.error.type.CommonErrorType;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

	private final CommonErrorType errorCode;

	public CommonException(CommonErrorType errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}