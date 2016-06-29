package com.oxd.controller;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oxd.model.NewsModel;
import com.oxd.service.NewsService;
import com.oxd.vo.MessageVo;
import com.oxd.vo.PageVo;

@Controller
@RequestMapping("/newsInfo")
public class NewsController {
	
	protected static final Logger logger = Logger.getLogger(NewsController.class);

	@Autowired
	private NewsService service;
	
	@RequestMapping(value = {"/", "/main"})
	public String main() {
		return "newsInfoMgr";
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public PageVo fingPageByParam(Model model,
			@RequestParam(defaultValue = "", required = false) String title, 
			@RequestParam(defaultValue = "0", required = false) int typeId, 
			int page, int rows) {
		PageVo pageVo = new PageVo();
		try {
			pageVo = service.findPageByParam(title, typeId, page, rows);
		} catch(Exception e) {
			logger.error("分页查询失败", e);
		}
		return pageVo;
	}
	
	@RequestMapping("/findOne")
	@ResponseBody
	public NewsModel findOne(Model model, int id) {
		try {
			return service.findOne(id);
		} catch(Exception e) {
			logger.error("根据id查询失败", e);
		}
		return null;
	}
	
	@RequestMapping("/saveOrupdate")
	@ResponseBody
	public Object saveOrUpdate(Model model, NewsModel news) {
		try {
			service.saveOrUpdate(news);
			return MessageVo.fullSuccessMessage("新增成功");
		} catch(Exception e) {
			logger.error("更新或修改失败", e);
			return MessageVo.fullErrorMessage("更新或修改失败");
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Model model, int id) {
		try {
			service.delete(id);
			return MessageVo.fullSuccessMessage("删除成功");
		} catch(Exception e) {
			logger.error("根据id查询失败", e);
			return MessageVo.fullErrorMessage("根据id删除失败");
		}
	}
	
}
