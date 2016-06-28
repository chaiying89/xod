package com.oxd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oxd.dao.MenuRepository;
import com.oxd.model.MenuModel;

@Service
@Transactional(rollbackFor = Exception.class)
public class MenuService {
	
	@Autowired
	private MenuRepository repository;
	
	@Transactional(readOnly = true)
	public MenuModel getByName(String name) {
		return repository.findByName(name);
	}
	
	/**
	 * 添加或更新资讯信息
	 * @param news
	 */
	public void saveOrUpdate(MenuModel news) {
		repository.save(news);
	}

}
