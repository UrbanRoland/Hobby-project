package com.vasut.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vasut.domain.Tickets;

public interface TicketsRepository extends CrudRepository<Tickets, Long> {

	List<Tickets> findAll();

	@Query(value = "SELECT * FROM tickets WHERE tickets.fromm = @from AND tickets.too = @too AND tickets.departure >= @time", nativeQuery = true)
	List<Tickets> getJegyek(@Param("from") String from, @Param("too") String too, @Param("time") String time);
}
