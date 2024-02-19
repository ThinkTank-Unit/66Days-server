package com.thinktank._66daysserver.domain.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinktank._66daysserver.domain.notification.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	List<Notification> findByUserId(Long userId);
}
