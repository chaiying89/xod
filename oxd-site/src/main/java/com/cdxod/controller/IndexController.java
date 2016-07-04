package com.cdxod.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@RequestMapping(value = {"", "/index"})
	public String index(Model model) {
		return "xod/index";
	}
}