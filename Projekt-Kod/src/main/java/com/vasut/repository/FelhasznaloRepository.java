package com.vasut.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vasut.domain.Felhasznalo;

public interface FelhasznaloRepository extends CrudRepository<Felhasznalo, Long> {

	List<Felhasznalo> findAll();

	// Email alapján meg tudunk keresni egy felhasznalot mert az email egyedi
	Felhasznalo findByEmail(String email);

}
