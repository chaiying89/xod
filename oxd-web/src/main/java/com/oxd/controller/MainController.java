package com.oxd.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oxd.service.UserService;

@Controller
public class MainController {
	
	protected static final Logger logger = Logger.getLogger(MainController.class);

	@Autowired
	private UserService service;
	
	
	@RequestMapping("/editor")
	public String editor(Model model) {
		return "online_editor";
	}
	
	@RequestMapping(value = {"/", "/main"})
	public String success() {
		return "main";
	}
}
