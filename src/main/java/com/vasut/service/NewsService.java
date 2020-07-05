package com.vasut.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vasut.domain.News;
import com.vasut.repository.NewsRepository;

@Service
public class NewsService {

	private NewsRepository newsRepo;

	@Autowired
	public void setNewsRepo(NewsRepository newsRepo) {
		this.newsRepo = newsRepo;
	}


	public void init(String title, String content, Date date) {
		News news = new News(title, content, date);
		newsRepo.save(news);
	}

	public List<News> getNews() {
		return newsRepo.findAll();
	}

	// Visszaad egy hirt, azt a hirt aminek a cimet parameterul megadtuk
	public News getSpecificNews(String title) {
		return newsRepo.findByTitle(title);
	}

	// Visszad 5 hirt,datum szerint rendezve
	public List<News> getFivesNews() {
		return newsRepo.findFirst5ByOrderByDateDesc();
	}

	// Visszaadja azokat a híreket amiknek címébe szerepel az adott keyword
	public List<News> search(String keyword) {
		return newsRepo.search(keyword);
	}

}
