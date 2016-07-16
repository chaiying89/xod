package com.cdxod.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oxd.service.ZhaoPinService;
import com.oxd.vo.ZhaoPinVo;

/**
 * 招聘专栏controller
 * @author chaiying
 *
 */
@Controller
public class ZhaopinController {
	

	@Autowired
	private ZhaoPinService service;
	
	@RequestMapping("/zhaopin/{mid}")
	public String index(Model model, @PathVariable("mid") Integer mid) {
		model.addAttribute("mid", mid);
		ZhaoPinVo vo = service.findOne(mid);
		model.addAttribute("content", vo == null ? "": vo.getContent());
		return "xod/zhaopin";
	}
}
