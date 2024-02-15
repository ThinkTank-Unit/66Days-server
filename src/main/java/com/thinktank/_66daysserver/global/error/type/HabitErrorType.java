package com.thinktank._66daysserver.global.error.type;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 습관 에러 코드 = H001 ~ H099
 */

@Getter
@AllArgsConstructor
public enum HabitErrorType {

	HABIT_NOT_FOUND(HttpStatus.NOT_FOUND, "H001", "습관을 찾을 수 없습니다."),
	INVALID_HABIT_INFO(HttpStatus.BAD_REQUEST, "H002", "유효하지 않은 습관 정보입니다."),
	HABIT_CREATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "H003", "습관 생성에 실패하였습니다."),
	HABIT_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "H004", "습관 수정에 실패하였습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;

}

