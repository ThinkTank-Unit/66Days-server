package com.thinktank._66daysserver.domain.habit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import com.thinktank._66daysserver.domain.habit.dto.HabitReq;
import com.thinktank._66daysserver.domain.habit.dto.HabitRes;
import com.thinktank._66daysserver.domain.habit.model.Habit;
import com.thinktank._66daysserver.domain.habit.service.HabitService;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

	private final HabitService habitService;

	@Autowired
	public HabitController(HabitService habitService) {
		this.habitService = habitService;
	}

	@PostMapping
	public ResponseEntity<HabitRes> createHabit(@RequestBody HabitReq habitReq) {
		Habit habit = habitService.createHabit(habitReq);
		HabitRes habitRes = new HabitRes(habit.getHabitID(), habit.getHabitName(), habit.getStartDate(), habit.getEndDate());
		return ResponseEntity.ok(habitRes);
	}

	@GetMapping("/{habitID}")
	public ResponseEntity<HabitRes> getHabitById(@PathVariable Long habitID) {
		Habit habit = habitService.getHabitById(habitID);
		HabitRes habitRes = new HabitRes(habit.getHabitID(), habit.getHabitName(), habit.getStartDate(), habit.getEndDate());
		return ResponseEntity.ok(habitRes);
	}

	@GetMapping
	public List<HabitRes> getAllHabits() {
		return habitService.getAllHabits().stream()
			.map(habit -> new HabitRes(habit.getHabitID(), habit.getHabitName(), habit.getStartDate(), habit.getEndDate()))
			.collect(Collectors.toList());
	}

	@PutMapping("/{habitID}")
	public ResponseEntity<HabitRes> updateHabit(@PathVariable Long habitID, @RequestBody HabitReq habitReq) {
		Habit updatedHabit = habitService.updateHabit(habitID, habitReq);
		HabitRes habitRes = new HabitRes(updatedHabit.getHabitID(), updatedHabit.getHabitName(), updatedHabit.getStartDate(), updatedHabit.getEndDate());
		return ResponseEntity.ok(habitRes);
	}

	@DeleteMapping("/{habitID}")
	public ResponseEntity<?> deleteHabit(@PathVariable Long habitID) {
		habitService.deleteHabit(habitID);
		return ResponseEntity.ok().build();
	}
}
