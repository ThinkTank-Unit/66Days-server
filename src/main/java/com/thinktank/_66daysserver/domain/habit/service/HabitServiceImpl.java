package com.thinktank._66daysserver.domain.habit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.thinktank._66daysserver.domain.habit.dto.HabitReq;
import com.thinktank._66daysserver.domain.habit.model.Habit;
import com.thinktank._66daysserver.domain.habit.repository.HabitRepository;

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
		return habitRepository.save(habit);
	}

	@Override
	public Habit updateHabit(Long habitID, HabitReq habitReq) {
		Habit habit = habitRepository.findById(habitID)
			.orElseThrow(() -> new RuntimeException("Habit not found with id " + habitID));

		habit.setHabitName(habitReq.getHabitName());
		habit.setStartDate(habitReq.getStartDate());
		// Note: EndDate is calculated automatically based on the startDate.
		return habitRepository.save(habit);
	}

	@Override
	public Habit getHabitById(Long habitID) {
		return habitRepository.findById(habitID)
			.orElseThrow(() -> new RuntimeException("Habit not found with id " + habitID));
	}

	@Override
	public List<Habit> getAllHabits() {
		return habitRepository.findAll();
	}

	@Override
	public void deleteHabit(Long habitID) {
		Habit habit = habitRepository.findById(habitID)
			.orElseThrow(() -> new RuntimeException("Habit not found with id " + habitID));
		habitRepository.delete(habit);
	}
}
