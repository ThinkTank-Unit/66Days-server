package com.thinktank._66daysserver.domain.notification.controller;

import com.thinktank._66daysserver.domain.notification.dto.NotificationReq;
import com.thinktank._66daysserver.domain.notification.dto.NotificationRes;
import com.thinktank._66daysserver.domain.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@PostMapping
	public ResponseEntity<NotificationRes> createNotification(@RequestBody NotificationReq requestDto) {
		NotificationRes responseDto = notificationService.createNotification(requestDto);
		return ResponseEntity.ok(responseDto);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<NotificationRes>> getUserNotifications(@PathVariable Long userId) {
		List<NotificationRes> notifications = notificationService.getNotifications(userId);
		return ResponseEntity.ok(notifications);
	}

	@PostMapping("/read/{notificationId}")
	public ResponseEntity<Void> markNotificationAsRead(@PathVariable Long notificationId) {
		notificationService.markNotificationAsRead(notificationId);
		return ResponseEntity.ok().build();
	}
}
