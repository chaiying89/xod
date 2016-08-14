package com.cdxod.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oxd.service.AboutService;
import com.oxd.vo.AboutVo;

@Controller
public class AboutController {
	
	protected static final Logger logger = Logger.getLogger(AboutController.class);
	
	@Autowired
	private AboutService service;
	
	@RequestMapping("/about/{mid}")
	public String index(Model model, @PathVariable("mid") Integer mid) {
		try {
			model.addAttribute("mid", mid);
			AboutVo vo = service.findOne(mid);
			model.addAttribute("content", vo == null ? "": vo.getContent());
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		return "xod/about";
	}
}
