package com.thinktank._66daysserver.domain.habit.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitRes {

	private Long habitID;
	private String habitName;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalTime reminderTime;

	public HabitRes(Long habitID, String habitName, LocalDate startDate, LocalDate endDate) {
		this.habitID = habitID;
		this.habitName = habitName;
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
