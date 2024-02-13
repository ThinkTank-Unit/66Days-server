package com.thinktank._66daysserver.domain.habit.model;

import java.time.LocalDate;

import com.thinktank._66daysserver.domain.users.model.Users;
import com.thinktank._66daysserver.global.entity.BaseTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "habit")
@Getter
@Setter
public class Habit extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long habitID;

	@ManyToOne
	@JoinColumn(name = "userID", nullable = false)
	private Users user;

	private String habitName;

	private String description;

	private LocalDate startDate;

	private LocalDate endDate;

}
