package com.thinktank._66daysserver.domain.notification.model;

import java.time.LocalDateTime;

import com.thinktank._66daysserver.domain.habit.model.Habit;
import com.thinktank._66daysserver.domain.users.model.Users;
import com.thinktank._66daysserver.global.entity.BaseTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notification")
@Getter
@Setter
public class Notification extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long notificationID;

	@ManyToOne
	@JoinColumn(name = "userID", nullable = false)
	private Users users;

	@ManyToOne
	@JoinColumn(name = "habitID", nullable = false)
	private Habit habit;

	@Enumerated(EnumType.STRING)
	private NotificationStyle notificationType;

	private String message;

	private LocalDateTime scheduledTime;

	@Enumerated(EnumType.STRING)
	private NotificationStatus status;

}