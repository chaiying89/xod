package com.oxd.controller;



import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oxd.exception.OxdException;
import com.oxd.model.ActivityModel;
import com.oxd.service.ActivityService;
import com.oxd.vo.ActivityVo;
import com.oxd.vo.MessageVo;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	protected static final Logger logger = Logger.getLogger(ActivityController.class);

	@Autowired
	private ActivityService service;
	
	@RequestMapping(value = {"/", "/main"})
	public String main() {
		return "activityMgr";
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Object fingPageByParam(Model model) {
		List<ActivityVo> list = new ArrayList<>();
		try {
			list = service.search();
		} catch(Exception e) {
			logger.error("查询失败", e);
		}
		return list;
	}
	
	@RequestMapping("/findOne")
	@ResponseBody
	public ActivityModel findOne(Model model, int id) {
		try {
			return service.findOneModel(id);
		} catch(Exception e) {
			logger.error("根据id查询失败", e);
		}
		return null;
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = {RequestMethod.POST})
	@ResponseBody
	public Object saveOrUpdate(Model model, @RequestBody ActivityModel news) {
		try {
			String msg = "新增成功";
			if(news.getId() != 0) {
				msg = "修改成功";
			}
			service.saveOrUpdate(news);
			return MessageVo.fullSuccessMessage(msg);
		} catch(Exception e) {
			logger.error("新增或修改失败", e);
			return MessageVo.fullErrorMessage("新增或修改失败");
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
			return MessageVo.fullErrorMessage("删除失败");
		}
	}
	
	@RequestMapping("/top")
	@ResponseBody
	public Object top(Model model, int id) {
		try {
			service.top(id);
			return MessageVo.fullSuccessMessage("置顶成功");
		} catch(OxdException e) {
			logger.error("置顶失败：" + e.getMessage(), e);
			return MessageVo.fullErrorMessage("置顶失败：" + e.getMessage());
		} catch(Exception e) {
			logger.error("置顶失败", e);
			return MessageVo.fullErrorMessage("置顶失败");
		}
	}
	
	@RequestMapping("/cancelTop")
	@ResponseBody
	public Object cancelTop(Model model, int id) {
		try {
			service.cancelTop(id);
			return MessageVo.fullSuccessMessage("取消置顶成功");
		} catch(Exception e) {
			logger.error("置顶失败", e);
			return MessageVo.fullErrorMessage("取消置顶失败");
		}
	}
	
}
