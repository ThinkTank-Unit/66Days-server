package com.thinktank._66daysserver.notification;

import com.thinktank._66daysserver.domain.habit.model.Habit;
import com.thinktank._66daysserver.domain.habit.repository.HabitRepository;
import com.thinktank._66daysserver.domain.notification.dto.NotificationReq;
import com.thinktank._66daysserver.domain.notification.dto.NotificationRes;
import com.thinktank._66daysserver.domain.notification.model.Notification;
import com.thinktank._66daysserver.domain.notification.model.NotificationStatus;
import com.thinktank._66daysserver.domain.notification.repository.NotificationRepository;
import com.thinktank._66daysserver.domain.notification.service.NotificationServiceImpl;
import com.thinktank._66daysserver.domain.users.model.Users;
import com.thinktank._66daysserver.domain.users.repository.UsersRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

	@Mock
	private NotificationRepository notificationRepository;

	@Mock
	private UsersRepository usersRepository;

	@Mock
	private HabitRepository habitRepository;

	@InjectMocks
	private NotificationServiceImpl notificationService;

	private Users mockUser;
	private Habit mockHabit;
	private Notification notification;

	@BeforeEach
	void setUp() {
		mockUser = new Users();
		mockUser.setUserID(1L);

		mockHabit = new Habit();
		mockHabit.setHabitID(1L);

		notification = new Notification();
		notification.setNotificationID(1L);
		notification.setUserId(mockUser);
		notification.setHabitId(mockHabit);
		notification.setMessage("Test Notification");
		notification.setScheduledTime(LocalDateTime.now());
		notification.setStatus(NotificationStatus.SENT);
	}

	@Test
	void createNotification_ShouldReturnNotificationRes() {
		when(usersRepository.findById(anyLong())).thenReturn(Optional.of(mockUser));
		when(habitRepository.findById(anyLong())).thenReturn(Optional.of(mockHabit));
		when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

		NotificationReq request = new NotificationReq();
		request.setUserId(mockUser);
		request.setHabitId(mockHabit);
		request.setMessage("Test Notification");
		request.setScheduledTime(LocalDateTime.now());

		NotificationRes result = notificationService.createNotification(request);

		assertThat(result).isNotNull();
		assertThat(result.getMessage()).isEqualTo(notification.getMessage());
		verify(notificationRepository).save(any(Notification.class));
	}

	@Test
	void getNotifications_ShouldReturnListOfNotifications() {
		when(notificationRepository.findByUserId(anyLong())).thenReturn(Arrays.asList(notification));

		List<NotificationRes> results = notificationService.getNotifications(mockUser.getUserID());

		assertThat(results).hasSize(1);
		assertThat(results.get(0).getMessage()).isEqualTo("Test Notification");
	}

	@Test
	void markNotificationAsRead_ShouldChangeStatus() {
		when(notificationRepository.findById(anyLong())).thenReturn(Optional.of(notification));

		notificationService.markNotificationAsRead(notification.getNotificationID());

		assertThat(notification.getStatus()).isEqualTo(NotificationStatus.READ);
		verify(notificationRepository).save(notification);
	}
}
