package com.cdxod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oxd.service.AboutService;
import com.oxd.vo.AboutVo;

@Controller
public class AboutController {
	
	@Autowired
	private AboutService service;
	
	@RequestMapping("/about/{mid}")
	public String index(Model model, @PathVariable("mid") Integer mid) {
		model.addAttribute("mid", mid);
		AboutVo vo = service.findOne(mid);
		model.addAttribute("content", vo == null ? "": vo.getContent());
		return "xod/about";
	}
}
