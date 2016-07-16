package com.cdxod.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oxd.service.AboutService;
import com.oxd.service.NewsService;
import com.oxd.service.ScopeService;
import com.oxd.service.StoreService;
import com.oxd.service.YangShengService;
import com.oxd.util.Constants;

@Controller
@RequestMapping("/")
public class IndexController {
	
	protected static final Logger logger = Logger.getLogger(IndexController.class);
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private YangShengService yangShengService;
	
	@Autowired
	private AboutService aboutService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private ScopeService scopeService;
	
	@RequestMapping(value = {"", "/index"})
	public String index(Model model) {
		try {
			model.addAttribute("jianjie", aboutService.findOne(Constants.ABOUT_ME));
			model.addAttribute("stores", storeService.findPageByParam(1, 4).getRows());
			model.addAttribute("scope", scopeService.searchForSite());
			model.addAttribute("gsgg", newsService.findPageByParam(1, 6, Constants.INFO_GSGG).getRows());
			model.addAttribute("liliao", yangShengService.findPageByParam(1, 6, Constants.YANGSHENG_LILIAO).getRows());
		} catch(Exception e) {
			logger.error("首页查询业务数据出错：" + e.getMessage());
		}
		return "xod/index";
	}
}
