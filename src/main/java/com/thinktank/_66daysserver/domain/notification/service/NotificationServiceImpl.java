package com.thinktank._66daysserver.domain.notification.service;

import com.thinktank._66daysserver.domain.notification.dto.NotificationReq;
import com.thinktank._66daysserver.domain.notification.dto.NotificationRes;
import com.thinktank._66daysserver.domain.notification.model.Notification;
import com.thinktank._66daysserver.domain.notification.model.NotificationStatus;
import com.thinktank._66daysserver.domain.notification.repository.NotificationRepository;
import com.thinktank._66daysserver.global.error.exception.NotificationException;
import com.thinktank._66daysserver.global.error.type.NotificationErrorType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	private NotificationRes convertToResponseDto(Notification notification) {
		NotificationRes response = new NotificationRes();
		response.setNotificationId(notification.getNotificationID());
		response.setUserId(notification.getUserId().getUserID());
		response.setHabitId(notification.getHabitId().getHabitID());
		response.setMessage(notification.getMessage());
		response.setScheduledTime(notification.getScheduledTime());
		response.setStatus(String.valueOf(notification.getStatus()));
		return response;
	}

	@Override
	public NotificationRes createNotification(NotificationReq requestDto) {
		Notification notification = new Notification();
		notification.setUserId(requestDto.getUserId());
		notification.setHabitId(requestDto.getHabitId());
		notification.setMessage(requestDto.getMessage());
		notification.setScheduledTime(requestDto.getScheduledTime());
		notification.setStatus(NotificationStatus.valueOf("SENT"));

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
