package com.thinktank._66daysserver.domain.notification.dto;

import java.time.LocalDateTime;

import com.thinktank._66daysserver.domain.habit.model.Habit;
import com.thinktank._66daysserver.domain.users.model.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationReq {
	private Users userId;
	private Habit habitId;
	private String message;
	private LocalDateTime scheduledTime;
}
