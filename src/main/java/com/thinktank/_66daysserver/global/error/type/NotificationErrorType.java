package com.thinktank._66daysserver.global.error.type;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 알림 에러 코드 = N001 ~ N099
 */

@Getter
@AllArgsConstructor
public enum NotificationErrorType {
	NOTIFICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "N001", "알림을 찾을 수 없습니다."),
	INVALID_NOTIFICATION_INFO(HttpStatus.BAD_REQUEST, "N002", "유효하지 않은 알림입니다."),
	NOTIFICATION_CREATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "N003", "알림 생성에 실패하였습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
