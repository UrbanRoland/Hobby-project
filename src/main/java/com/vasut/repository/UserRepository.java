package com.vasut.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.vasut.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
	
	User findByActivation(String code);
	
	@Query(value = "SELECT email FROM users", nativeQuery = true)
	 List<String> allEmail();
	
	
}
