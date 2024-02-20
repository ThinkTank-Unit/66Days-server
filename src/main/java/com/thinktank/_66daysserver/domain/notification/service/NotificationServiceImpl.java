package com.thinktank._66daysserver.domain.notification.service;

import com.thinktank._66daysserver.domain.habit.model.Habit;
import com.thinktank._66daysserver.domain.habit.repository.HabitRepository;
import com.thinktank._66daysserver.domain.notification.dto.NotificationReq;
import com.thinktank._66daysserver.domain.notification.dto.NotificationRes;
import com.thinktank._66daysserver.domain.notification.model.Notification;
import com.thinktank._66daysserver.domain.notification.model.NotificationStatus;
import com.thinktank._66daysserver.domain.notification.repository.NotificationRepository;
import com.thinktank._66daysserver.domain.users.model.Users;
import com.thinktank._66daysserver.domain.users.repository.UsersRepository;
import com.thinktank._66daysserver.global.error.exception.NotificationException;
import com.thinktank._66daysserver.global.error.type.NotificationErrorType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private HabitRepository habitRepository;

	private NotificationRes convertToResponseDto(Notification notification) {
		NotificationRes response = new NotificationRes();
		response.setNotificationId(notification.getNotificationID());
		if (notification.getUserId() != null) {
			response.setUserId(notification.getUserId().getUserID());
		}
		if (notification.getHabitId() != null) {
			response.setHabitId(notification.getHabitId().getHabitID());
		}
		response.setMessage(notification.getMessage());
		response.setScheduledTime(notification.getScheduledTime());
		response.setStatus(String.valueOf(notification.getStatus()));
		return response;
	}


	@Override
	public NotificationRes createNotification(NotificationReq requestDto) {
		// Users와 Habit 엔티티를 찾음
		Users user = usersRepository.findById(requestDto.getUserId().getUserID())
			.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + requestDto.getUserId()));
		Habit habit = habitRepository.findById(requestDto.getHabitId().getHabitID())
			.orElseThrow(() -> new EntityNotFoundException("Habit not found with id: " + requestDto.getHabitId()));

		Notification notification = new Notification();
		notification.setUserId(user); // Users 엔티티 설정
		notification.setHabitId(habit); // Habit 엔티티 설정
		notification.setMessage(requestDto.getMessage());
		notification.setScheduledTime(requestDto.getScheduledTime());
		notification.setStatus(NotificationStatus.SENT);

		Notification savedNotification = notificationRepository.save(notification);
		return convertToResponseDto(savedNotification);
	}

	@Override
	public List<NotificationRes> getNotifications(Long userId) {
		List<Notification> notifications = notificationRepository.findByUserId(userId);
		return notifications.stream().map(this::convertToResponseDto).collect(Collectors.toList());
	}

	@Override
	public void markNotificationAsRead(Long notificationId) {
		Notification notification = notificationRepository.findById(notificationId)
			.orElseThrow(() -> new NotificationException(NotificationErrorType.NOTIFICATION_NOT_FOUND));
		notification.setStatus(NotificationStatus.valueOf("READ"));
		notificationRepository.save(notification);
	}

}
