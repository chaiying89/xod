package com.oxd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oxd.model.NewsModel;


@Repository
public interface NewsRepository extends JpaRepository<NewsModel, Integer> {

	@Query("select news from NewsModel news where news.title = ?1")
	public NewsModel findByName(String name);
}
