package com.oxd.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oxd.model.NewsModel;
import com.oxd.service.NewsService;
import com.oxd.vo.PageVo;

@Controller
@RequestMapping("/news-info")
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
	public PageVo fingPageByParam(Model model) {
		PageVo pageVo = new PageVo();
		List<NewsModel> list = new ArrayList<NewsModel>();
		NewsModel news = new NewsModel();
		news.setTitle("今天星期二");
		news.setId(1);
		news.setIntroduction("据介绍大家的时间");
		NewsModel news1 = new NewsModel();
		news1.setTitle("今天星期二fff");
		news1.setId(2);
		news1.setIntroduction("据介绍大家的时间sss");
		list.add(news);
		list.add(news1);
		pageVo.setRows(list);
		pageVo.setTotal(2);
		return pageVo;
	}
}
