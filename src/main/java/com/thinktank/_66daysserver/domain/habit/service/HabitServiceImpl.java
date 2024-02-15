package com.thinktank._66daysserver.domain.habit.service;

import org.springframework.stereotype.Service;

import java.util.List;

import com.thinktank._66daysserver.domain.habit.dto.HabitReq;
import com.thinktank._66daysserver.domain.habit.model.Habit;
import com.thinktank._66daysserver.domain.habit.repository.HabitRepository;
import com.thinktank._66daysserver.global.error.exception.HabitException;
import com.thinktank._66daysserver.global.error.type.HabitErrorType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HabitServiceImpl implements HabitService {

	private final HabitRepository habitRepository;

	@Override
	public Habit createHabit(HabitReq habitReq) {
		Habit habit = new Habit();
		habit.setHabitName(habitReq.getHabitName());
		habit.setStartDate(habitReq.getStartDate());
		habit.setEndDate(habitReq.getStartDate().plusDays(66));

		try {
			return habitRepository.save(habit);
		} catch (Exception e) {
			throw new HabitException(HabitErrorType.HABIT_CREATION_FAILED);
		}
	}

	@Override
	public Habit updateHabit(Long habitID, HabitReq habitReq) {
		Habit habit = habitRepository.findById(habitID)
			.orElseThrow(() -> new HabitException(HabitErrorType.HABIT_NOT_FOUND));

		habit.setHabitName(habitReq.getHabitName());
		habit.setStartDate(habitReq.getStartDate());

		try {
			return habitRepository.save(habit);
		} catch (Exception e) {
			throw new HabitException(HabitErrorType.HABIT_UPDATE_FAILED);
		}
	}

	@Override
	public Habit getHabitById(Long habitID) {
		return habitRepository.findById(habitID)
			.orElseThrow(() -> new HabitException(HabitErrorType.HABIT_NOT_FOUND));
	}

	@Override
	public List<Habit> getAllHabits() {
		return habitRepository.findAll();
	}

	@Override
	public void deleteHabit(Long habitID) {
		Habit habit = habitRepository.findById(habitID)
			.orElseThrow(() -> new HabitException(HabitErrorType.HABIT_NOT_FOUND));
		habitRepository.delete(habit);
	}
}
