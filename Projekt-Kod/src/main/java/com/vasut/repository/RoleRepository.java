package com.vasut.repository;

import org.springframework.data.repository.CrudRepository;

import com.vasut.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByRole(String role);

}
