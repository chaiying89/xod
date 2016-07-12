package com.cdxod.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 联系我们controller
 * @author chaiying
 *
 */
@Controller
@RequestMapping("/contact")
public class ContactController {
	
	@RequestMapping(value = {"", "/index"})
	public String index(HttpSession session) {
		return "xod/cont";
	}
}
