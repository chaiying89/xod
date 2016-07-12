package com.cdxod.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 经营范围controller
 * @author chaiying
 *
 */
@Controller
@RequestMapping("/scope")
public class ScopeController {
	
	@RequestMapping(value = {"", "/index"})
	public String index(HttpSession session) {
		return "xod/scope";
	}
}
