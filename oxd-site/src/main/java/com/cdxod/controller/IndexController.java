package com.cdxod.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@RequestMapping(value = {"", "/index"})
	public String index(HttpSession session) {
		return "xod/index";
	}
}
