package com.thinktank._66daysserver.domain.notification.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRes {
	private Long notificationId;
	private Long userId;
	private Long habitId;
	private String message;
	private LocalDateTime scheduledTime;
	private String status;
}
