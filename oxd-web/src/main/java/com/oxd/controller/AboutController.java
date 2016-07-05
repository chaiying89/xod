package com.oxd.controller;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oxd.service.NewsService;

@Controller
@RequestMapping("/about")
public class AboutController {
	
	protected static final Logger logger = Logger.getLogger(AboutController.class);

	@Autowired
	private NewsService service;
	
	@RequestMapping(value = {"/", "/main"})
	public String main() {
		return "aboutUsMgr";
	}
}
