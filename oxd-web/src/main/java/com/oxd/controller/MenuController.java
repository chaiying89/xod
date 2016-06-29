package com.oxd.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.oxd.service.MenuService;

@Controller
public class MenuController {
	
	protected static final Logger logger = Logger.getLogger(MenuController.class);

	@Autowired
	private MenuService service;
	
}
