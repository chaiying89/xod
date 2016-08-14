package com.cdxod.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oxd.service.TouziService;
import com.oxd.vo.TouziVo;

/**
 * 投资加盟
 * @author chaiying
 *
 */
@Controller
@RequestMapping("/touzi")
public class InvestController {
	
	protected static final Logger logger = Logger.getLogger(InvestController.class);
	
	@Autowired
	private TouziService service;
	
	@RequestMapping("/{mid}")
	public String index(Model model, @PathVariable("mid") Integer mid) {
		try {
			model.addAttribute("mid", mid);
			TouziVo vo = service.findOne(mid);
			model.addAttribute("content", vo == null ? "": vo.getContent());
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		return "xod/touzi";
	}
}
