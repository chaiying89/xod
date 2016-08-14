package com.cdxod.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oxd.service.ActivityService;
import com.oxd.vo.ActivityVo;

@Controller
public class ActivityController {
	
	protected static final Logger logger = Logger.getLogger(ActivityController.class);
	
	@Autowired
	private ActivityService service;
	
	@RequestMapping("/activity/detail")
	@ResponseBody
	public Object index(Model model, Integer id) {
		try {
			ActivityVo vo = service.findOne(id);
			return vo.getContent();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}
