package com.cdxod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oxd.service.TouziService;
import com.oxd.vo.TouziVo;


@Controller
@RequestMapping("/touzi")
public class TouZiController {
	
	@Autowired
	private TouziService service;
	
	@RequestMapping("/{mid}")
	public String index(Model model, @PathVariable("mid") Integer mid) {
		model.addAttribute("mid", mid);
		TouziVo vo = service.findOne(mid);
		model.addAttribute("content", vo == null ? "": vo.getContent());
		return "xod/touzi";
	}
	
	@RequestMapping("/stores/{mid}")
	public String store(Model model, @PathVariable("mid") Integer mid) {
		model.addAttribute("mid", mid);
		return "xod/touzi-stores";
	}
	
	@RequestMapping("/questions/{mid}")
	public String questions(Model model, @PathVariable("mid") Integer mid) {
		model.addAttribute("mid", mid);
		return "xod/touzi-questions";
	}
}
