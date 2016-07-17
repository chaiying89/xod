package com.cdxod.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oxd.model.YangShengModel;
import com.oxd.service.YangShengService;
import com.oxd.util.Constants;
import com.oxd.vo.PageVo;

/**
 * 养生专区controller
 * @author chaiying
 *
 */
@Controller
public class YangshengController {
	
	protected static final Logger logger = Logger.getLogger(YangshengController.class);
	
	@Autowired
	private YangShengService service;
	
	@RequestMapping("/yangsheng/{mid}")
	public String index(Model model, @PathVariable("mid") Integer mid, 
			@RequestParam(defaultValue = "1", required = false) Integer pageNum) {
		model.addAttribute("mid", mid);
		PageVo page = new PageVo();
		try {
			page = service.findPageByParam(pageNum, 6, mid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("pages", page);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", 6);
		return "xod/yangsheng";
	}
	

	@RequestMapping("/yangsheng/detail")
	public String detail(Model model, Integer id) {
		model.addAttribute("pname", "养生专区");
		model.addAttribute("menu2level", Constants.MENU_YANGSHENG);
		try {
			YangShengModel news = service.findOne(id);
			model.addAttribute("mid", news.getType().getId());
			model.addAttribute("content", news.getContent());
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		return "xod/content";
	}
}
