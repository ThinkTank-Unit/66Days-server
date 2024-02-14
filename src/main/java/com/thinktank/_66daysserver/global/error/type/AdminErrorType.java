package com.thinktank._66daysserver.global.error.type;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 관리자 에러 코드 = A001 ~ A099
 */

@Getter
@AllArgsConstructor
public enum AdminErrorType {

	USER_LOOKUP_FAILED(HttpStatus.BAD_REQUEST, "A001", "회원 조회에 실패하였습니다."),
	HABIT_LOOKUP_FAILED(HttpStatus.BAD_REQUEST, "A002", "습관 조회에 실패하였습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;

}