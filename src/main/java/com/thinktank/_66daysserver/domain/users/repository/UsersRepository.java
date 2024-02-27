package com.thinktank._66daysserver.domain.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinktank._66daysserver.domain.users.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
