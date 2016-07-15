package com.oxd.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oxd.model.MenuModel;
import com.oxd.service.MenuService;
import com.oxd.vo.MenuVo;
import com.oxd.vo.MessageVo;
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
	
	
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public Object saveOrUpdate(Model model, @RequestBody MenuModel menuModel) {
		try {
			String msg = "新增成功";
			if(menuModel.getId() != 0) {
				msg = "修改成功";
			}
			service.saveOrUpdate(menuModel);
			return MessageVo.fullSuccessMessage(msg);
		} catch(Exception e) {
			logger.error("新增或修改失败", e);
			return MessageVo.fullErrorMessage("新增或修改失败");
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Model model, int id) {
		try {
			service.delete(id);
			return MessageVo.fullSuccessMessage("删除成功");
		} catch(Exception e) {
			logger.error("根据id删除失败", e);
			return MessageVo.fullErrorMessage("删除失败");
		}
	}
}
