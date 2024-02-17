package com.thinktank._66daysserver.habit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import com.thinktank._66daysserver.domain.habit.dto.HabitReq;
import com.thinktank._66daysserver.domain.habit.model.Habit;
import com.thinktank._66daysserver.domain.habit.repository.HabitRepository;
import com.thinktank._66daysserver.domain.habit.service.HabitServiceImpl;
import com.thinktank._66daysserver.global.error.exception.HabitException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HabitServiceTest {

	@Mock
	private HabitRepository habitRepository;

	@InjectMocks
	private HabitServiceImpl habitService;

	private Habit habit;
	private HabitReq habitReq;

	@BeforeEach
	void setUp() {
		// 초기화
		habit = new Habit();
		habit.setHabitID(1L);
		habit.setHabitName("Test Habit");
		habit.setStartDate(LocalDate.now());
		habit.setEndDate(LocalDate.now().plusDays(66));
		habit.setReminderTime(LocalTime.of(8, 30));

		habitReq = new HabitReq();
		habitReq.setHabitName("Test Habit");
		habitReq.setStartDate(LocalDate.now());
		habitReq.setReminderTime(LocalTime.of(8, 30));
	}

	@Test
	void createHabit_Success() {
		when(habitRepository.save(any(Habit.class))).thenReturn(habit);

		Habit createdHabit = habitService.createHabit(habitReq);

		assertNotNull(createdHabit);
		assertEquals(habit.getHabitName(), createdHabit.getHabitName());
		assertEquals(habit.getReminderTime(), createdHabit.getReminderTime());
	}

	@Test
	void getHabitById_Success() {
		when(habitRepository.findById(anyLong())).thenReturn(Optional.of(habit));

		Habit foundHabit = habitService.getHabitById(1L);

		assertNotNull(foundHabit);
		assertEquals(habit.getHabitID(), foundHabit.getHabitID());
	}

	@Test
	void getHabitById_NotFound() {
		when(habitRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(HabitException.class, () -> habitService.getHabitById(1L));
	}

	@Test
	void updateHabit_Success() {
		when(habitRepository.findById(anyLong())).thenReturn(Optional.of(habit));
		when(habitRepository.save(any(Habit.class))).thenReturn(habit);

		Habit updatedHabit = habitService.updateHabit(1L, habitReq);

		assertNotNull(updatedHabit);
		assertEquals(habitReq.getHabitName(), updatedHabit.getHabitName());
		assertEquals(habitReq.getReminderTime(), updatedHabit.getReminderTime());
	}

	// 추가 테스트 케이스 구현...
}
