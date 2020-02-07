package com.vasut.repository;

import org.springframework.data.repository.CrudRepository;

import com.vasut.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
}
