package com.cdxod.listener;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdxod.cache.CacheMapFactory;
import com.oxd.service.MenuService;

/**
 * 初始化菜单数据
 * @author chaiying
 *
 */
public class InitMenuDataHandler implements InitializingBean {
	
	@Autowired
	private MenuService service;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		CacheMapFactory.getCacheMap().put("menus", service.siteMenuQuery());
		CacheMapFactory.getCacheMap().put("2lmenus", service.site2lMenuQuery());
	}

}
