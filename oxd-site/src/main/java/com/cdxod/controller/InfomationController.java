package com.cdxod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oxd.service.NewsService;
import com.oxd.vo.PageVo;

/**
 * 资讯中心Controller
 * @author chaiying
 *
 */
@Controller
@RequestMapping("/info")
public class InfomationController {
	
	@Autowired
	private NewsService service;
	
	@RequestMapping("/{mid}")
	public String news(Model model, @PathVariable("mid") Integer mid, 
			@RequestParam(defaultValue = "1", required = false) Integer pageNum) {
		model.addAttribute("mid", mid);
		PageVo page = new PageVo();
		try {
			page = service.findPageByParam(pageNum, 4, mid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("pages", page);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", 4);
		return "xod/news";
	}
}
