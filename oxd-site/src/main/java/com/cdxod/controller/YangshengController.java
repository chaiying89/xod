package com.cdxod.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 养生专区controller
 * @author chaiying
 *
 */
@Controller
@RequestMapping("/yangsheng")
public class YangshengController {
	
	@RequestMapping(value = {"", "/index"})
	public String index(HttpSession session) {
		return "xod/yangsheng";
	}
}
