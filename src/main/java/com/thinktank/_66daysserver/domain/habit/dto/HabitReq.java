package com.thinktank._66daysserver.domain.habit.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HabitReq {

	private String habitName;
	private LocalDate startDate;
	private LocalTime reminderTime;

}
