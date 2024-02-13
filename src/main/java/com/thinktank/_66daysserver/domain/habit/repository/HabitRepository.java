package com.thinktank._66daysserver.domain.habit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thinktank._66daysserver.domain.habit.model.Habit;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {
}
