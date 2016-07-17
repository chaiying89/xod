package com.cdxod.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oxd.model.StoreInfoModel;
import com.oxd.service.StoreService;
import com.oxd.util.Constants;
import com.oxd.vo.PageVo;
import com.oxd.vo.TouziVo;

/**
 * 投资加盟
 * @author chaiying
 *
 */
@Controller
@RequestMapping("/stores")
public class StoreController {
	
	protected static final Logger logger = Logger.getLogger(StoreController.class);
	
	@Autowired
	private StoreService service;
	
	@RequestMapping("/orgstruct")
	public String orgStruct(Model model) {
		model.addAttribute("url", "/stores/orgstruct");
		TouziVo vo = service.findOneManager(Constants.STORE_ORG);
		model.addAttribute("content", vo == null ? "": vo.getContent());
		return "xod/stores";
	}
	
	@RequestMapping("/manager")
	public String mamger(Model model) {
		model.addAttribute("url", "/stores/manager");
		TouziVo vo = service.findOneManager(Constants.STORE_MGR);
		model.addAttribute("content", vo == null ? "": vo.getContent());
		return "xod/stores";
	}
	
	@RequestMapping("/show")
	public String store(Model model, @RequestParam(defaultValue = "1", required = false) Integer pageNum) {
		model.addAttribute("url", "/stores/show");
		PageVo page = new PageVo();
		try {
			page = service.findPageByParam(pageNum, 6);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("pages", page);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", 6);
		return "xod/stores_show";
	}
	
	@RequestMapping("/detail")
	public String detail(Model model, Integer id) {
		model.addAttribute("pname", "投资加盟");
		model.addAttribute("menu2level", Constants.MENU_TOUZI);
		try {
			StoreInfoModel news = service.findOne(id);
			model.addAttribute("mid", Constants.TOUZI_STORE);
			model.addAttribute("content", news.getContent());
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		return "xod/content";
	}
	
}
