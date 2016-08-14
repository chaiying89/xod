package com.cdxod.controller;


import org.apache.log4j.Logger;
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
	
	protected static final Logger logger = Logger.getLogger(ZhaopinController.class);

	@Autowired
	private ZhaoPinService service;
	
	@RequestMapping("/zhaopin/{mid}")
	public String index(Model model, @PathVariable("mid") Integer mid) {
		try {
			model.addAttribute("mid", mid);
			ZhaoPinVo vo = service.findOne(mid);
			model.addAttribute("content", vo == null ? "": vo.getContent());
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		return "xod/zhaopin";
	}
}
