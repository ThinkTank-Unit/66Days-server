package com.thinktank._66daysserver.domain.habit.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HabitReq {

	private String habitName;
	private LocalDate startDate;
	private LocalDate reminderTime;
	// endDate는 startDate로부터 66일 후로 자동 계산되므로 포함하지 않음

}
