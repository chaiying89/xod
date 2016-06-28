package com.oxd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oxd.dao.NewsRepository;
import com.oxd.model.NewsModel;

@Service
@Transactional(rollbackFor = Exception.class)
public class NewsService {
	
	@Autowired
	private NewsRepository repository;
	
	@Transactional(readOnly = true)
	public NewsModel getByName(String name) {
		return repository.findByName(name);
	}
	
	/**
	 * 添加或更新资讯信息
	 * @param news
	 */
	public void saveOrUpdate(NewsModel news) {
		repository.save(news);
	}

}
