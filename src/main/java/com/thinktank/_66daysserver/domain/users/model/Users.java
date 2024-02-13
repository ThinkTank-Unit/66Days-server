package com.thinktank._66daysserver.domain.users.model;

import com.thinktank._66daysserver.global.entity.BaseTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class Users extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userID;

	private String name;

	private String email;

	private String password;

}
