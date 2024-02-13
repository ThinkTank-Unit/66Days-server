package com.thinktank._66daysserver.domain.habit.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "habitTracking")
@Getter
@Setter
public class HabitTracking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long trackingID;

	@ManyToOne
	@JoinColumn(name = "habitID", nullable = false)
	private Habit habit;

	private LocalDate date;

	private Boolean status;

}
