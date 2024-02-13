package com.thinktank._66daysserver.domain.habit.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.thinktank._66daysserver.domain.users.model.Users;
import com.thinktank._66daysserver.global.entity.BaseTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "habit")
@Getter
@Setter
@RequiredArgsConstructor
public class Habit extends BaseTime {

	// 습관 ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long habitID;

	// 사용자 ID
	@ManyToOne
	@JoinColumn(name = "userID", nullable = false)
	private Users user;

	// 습관 이름
	private String habitName;

	// 습관 시작일시
	private LocalDate startDate;

	// 습관 종료일시
	private LocalDate endDate;

	// 습관 알림 시간
	private LocalTime reminderTime;

	// 습관 종료일자 자동 계산
	@PrePersist
	public void prePersist() {
		if (startDate != null && endDate == null) {
			endDate = startDate.plusDays(66);
		}
	}

}
