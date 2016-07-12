package com.oxd.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oxd.service.MenuService;
import com.oxd.vo.MenuVo;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	protected static final Logger logger = Logger.getLogger(MenuController.class);

	@Autowired
	private MenuService service;
	
	@RequestMapping(value = {"/", "/main"})
	public String index() {
		return "menuMgr";
	}
	
	@RequestMapping("/seletor")
	@ResponseBody
	public List<MenuVo> selectQuery(Model model, String name) {
		try {
			List<MenuVo> list = service.selectQuery(name);
			return list;
		} catch(Exception e) {
			logger.error("查询菜单出错", e);
			return null;
		}
	}
	
}
