package com.cdxod.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 招聘专栏controller
 * @author chaiying
 *
 */
@Controller
@RequestMapping("/zhaopin")
public class ZhaopinController {
	
	@RequestMapping(value = {"", "/index"})
	public String index(HttpSession session) {
		return "xod/zhaopin";
	}
}
