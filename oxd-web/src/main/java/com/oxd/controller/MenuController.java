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
import com.oxd.vo.TreeVo;

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
	
	@RequestMapping("/allTree")
	@ResponseBody
	public List<TreeVo> getAllTree() {
		try {
			return service.getAllTree();
		} catch(Exception e) {
			logger.error("查询菜单树出错", e);
		}
		return null;
	}
	
	@RequestMapping("/findByPid")
	@ResponseBody
	public List<TreeVo> find(Model model, int id) {
		try {
			return service.findByParentId(id);
		} catch(Exception e) {
			logger.error("查询菜单树出错", e);
		}
		return null;
	}
	
	@RequestMapping("/seletor")
	@ResponseBody
	public List<MenuVo> selectQuery(Model model, String name) {
		try {
			List<MenuVo> list = service.selectQuery(name);
			return list;
		} catch(Exception e) {
			logger.error("查询菜单出错", e);
		}
		return null;
	}
	
}
