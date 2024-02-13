package com.thinktank._66daysserver.domain.habit.service;

import java.util.List;

import com.thinktank._66daysserver.domain.habit.dto.HabitReq;
import com.thinktank._66daysserver.domain.habit.model.Habit;

public interface HabitService {

	// 습관 생성
	Habit createHabit(HabitReq habitReq);

	// 습관 수정
	Habit updateHabit(Long habitID, HabitReq habitReq);

	// 습관 상세조회
	Habit getHabitById(Long habitID);

	// 모든 습관 조회
	List<Habit> getAllHabits();

	// 습관 삭제
	void deleteHabit(Long habitID);
}