package com.vasut.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vasut.domain.News;

/**
 * @author Roland
 *         <p>
 * 
 *         Tartalmaz minden hirhez kapcsolodo muveletet. Fontos ez egy interface
 *         es ki kell terjesztenie a CrudRepository-t. Hasznalatkor a bele irt
 *         metodusokat felul kell definialni.
 *
 * 
 */
public interface NewsRepository extends CrudRepository<News, Long> {

	List<News> findAll();

	News findByTitle(String title);

	List<News> findFirst5ByOrderByDateDesc();

	// Ez keres a hírek címében paraméterben kapott szöveg alapján
	@Query(value = "SELECT * FROM news n WHERE n.title LIKE '%' || :keyword || '%'", nativeQuery = true)
	List<News> search(@Param("keyword") String keyword);
}
