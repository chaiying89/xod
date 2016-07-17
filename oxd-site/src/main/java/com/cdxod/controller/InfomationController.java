package com.cdxod.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oxd.model.NewsModel;
import com.oxd.service.NewsService;
import com.oxd.util.Constants;
import com.oxd.vo.PageVo;

/**
 * 资讯中心Controller
 * @author chaiying
 *
 */
@Controller
@RequestMapping("/info")
public class InfomationController {
	
	protected static final Logger logger = Logger.getLogger(InfomationController.class);
	
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
			logger.error(e.getMessage());
		}
		model.addAttribute("pages", page);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", 4);
		return "xod/news";
	}
	
	
	
	@RequestMapping("/detail")
	public String detail(Model model, Integer id) {
		model.addAttribute("pname", "资讯中心");
		model.addAttribute("menu2level", Constants.MENU_INFO);
		try {
			NewsModel news = service.findOne(id);
			model.addAttribute("mid", news.getType().getId());
			model.addAttribute("content", news.getContent());
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		return "xod/content";
	}
}
