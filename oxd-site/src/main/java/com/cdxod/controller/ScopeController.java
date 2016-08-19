package com.cdxod.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oxd.service.ScopeService;
import com.oxd.vo.ScopeVo;

/**
 * 经营范围controller
 * @author chaiying
 *
 */
@Controller
@RequestMapping("/scope")
public class ScopeController {
	
	protected static final Logger logger = Logger.getLogger(ScopeController.class);
	
	@Autowired
	private ScopeService scopeService;
	
	@RequestMapping(value = {"", "/index"})
	public String index(Model model) {
		try {
		model.addAttribute("scope", scopeService.searchForSite());
		} catch(Exception e) {
			logger.error("查询业务数据出错：" + e.getMessage());
		}
		return "xod/scope";
	}
	
	@RequestMapping("/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("pname", "经营范围");
		try {
			ScopeVo news = scopeService.findOne(id);
			model.addAttribute("content", news.getContent());
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		return "xod/content";
	}
}
