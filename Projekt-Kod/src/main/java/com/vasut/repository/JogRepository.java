package com.vasut.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vasut.domain.Jog;

public interface JogRepository extends CrudRepository<Jog, Long> {

	List<Jog> findAll();

	@Query(value = "select * from jog where nev= :nev", nativeQuery = true)
	Jog findByNev(@Param("nev") String nev);

}
