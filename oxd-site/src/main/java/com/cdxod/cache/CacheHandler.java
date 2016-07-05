package com.cdxod.cache;

import java.util.List;

import com.oxd.vo.MenuVo;

public class CacheHandler {
	
	@SuppressWarnings("unchecked")
	public List<MenuVo> getMenus() {
		return (List<MenuVo>) CacheMapFactory.getCacheMap().get("menus");
	}
}
