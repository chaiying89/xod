package com.cdxod.cache;

import java.util.List;
import java.util.Map;

import com.oxd.vo.MenuVo;

public class CacheHandler {
	
	/**
	 * 获取网站TOP菜单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MenuVo> getMenus() {
		return (List<MenuVo>) CacheMapFactory.getCacheMap().get("menus");
	}
	
	/**
	 * 获取二级菜单根据父级ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MenuVo> get2lMenus(Integer id) {
		Map<Integer, List<MenuVo>> map = (Map<Integer, List<MenuVo>>) CacheMapFactory.getCacheMap().get("2lmenus");
		return map.get(id.intValue());
	}
	
	public String[] split(String args, String sp) {
		return args.split(sp);
	}
	
	
	public String firstPTag(String args) {
		if(args.contains("</p>")) {
			return args.substring(0, args.indexOf("</p>") + 4);
		}
		return args;
	}
	
	public String textOverflow(String args) {
		if(args.length() > 20) {
			return args.substring(0, 20) + "...";
		}
		return args;
	}
}
