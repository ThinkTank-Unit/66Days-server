package com.thinktank._66daysserver.domain.notification.service;

import com.thinktank._66daysserver.domain.notification.dto.NotificationReq;
import com.thinktank._66daysserver.domain.notification.dto.NotificationRes;

import java.util.List;

public interface NotificationService {

	NotificationRes createNotification(NotificationReq requestDto);

	List<NotificationRes> getNotifications(Long userId);

	void markNotificationAsRead(Long notificationId);
}
