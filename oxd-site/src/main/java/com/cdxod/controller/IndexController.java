package com.cdxod.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oxd.service.AboutService;
import com.oxd.service.ActivityService;
import com.oxd.service.NewsService;
import com.oxd.service.StoreService;
import com.oxd.service.TouziService;
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
	private TouziService touziService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(value = {"", "/index"})
	public String index(Model model) {
		try {
			model.addAttribute("stores", storeService.findPageByParam(1, 20).getRows());
			model.addAttribute("activity", activityService.searchByPage());
			model.addAttribute("touzi", touziService.searchForSite());
			model.addAttribute("about", aboutService.searchForSite());
			model.addAttribute("gsgg", newsService.findPageByParam(1, 3, Constants.INFO_GSNEW).getRows());
			model.addAttribute("liliao", yangShengService.findPageByParam(1, 3, Constants.YANGSHENG_LILIAO).getRows());
		} catch(Exception e) {
			logger.error("首页查询业务数据出错：" + e.getMessage());
		}
		return "xod/index";
	}
}
