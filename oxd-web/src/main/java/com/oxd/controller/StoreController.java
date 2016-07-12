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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oxd.exception.OxdException;
import com.oxd.model.StoreInfoModel;
import com.oxd.model.StoreManagerModel;
import com.oxd.service.StoreService;
import com.oxd.vo.MessageVo;
import com.oxd.vo.PageVo;
import com.oxd.vo.TouziVo;

@Controller
@RequestMapping("/store")
public class StoreController {
	
	protected static final Logger logger = Logger.getLogger(StoreController.class);

	@Autowired
	private StoreService service;
	
	@RequestMapping(value = {"/", "/main"})
	public String main() {
		return "storesMgr";
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public PageVo fingPageByParam(Model model,
			@RequestParam(defaultValue = "", required = false) String title, 
			@RequestParam(defaultValue = "0", required = false) int typeId, 
			int page, int rows) {
		PageVo pageVo = new PageVo();
		try {
			pageVo = service.findPageByParam(page, rows);
		} catch(Exception e) {
			logger.error("分页查询失败", e);
		}
		return pageVo;
	}
	
	@RequestMapping("/findOne")
	@ResponseBody
	public StoreInfoModel findOne(Model model, int id) {
		try {
			return service.findOne(id);
		} catch(Exception e) {
			logger.error("根据id查询失败", e);
		}
		return null;
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = {RequestMethod.POST})
	@ResponseBody
	public Object saveOrUpdate(Model model, @RequestBody StoreInfoModel news) {
		try {
			String msg = "新增成功";
			if(news.getId() != 0) {
				msg = "修改成功";
			}
			service.saveOrUpdate(news);
			return MessageVo.fullSuccessMessage(msg);
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
	
	
	@RequestMapping("/base/search")
	@ResponseBody
	public Object baseSearch(Model model) {
		List<TouziVo> list = new ArrayList<TouziVo>();
		try {
			list = service.search();
		} catch(Exception e) {
			logger.error("[投资加盟]查询失败", e);
		}
		return list;
	}
	
	@RequestMapping("/base/saveOrUpdate")
	@ResponseBody
	public Object baseSaveOrUpdate(Model model, @RequestBody StoreManagerModel storeManagerModel) {
		try {
			String msg = "新增成功";
			if(storeManagerModel.getId() != 0) {
				msg = "修改成功";
			}
			service.saveOrUpdateManager(storeManagerModel);
			return MessageVo.fullSuccessMessage(msg);
		} catch(Exception e) {
			logger.error("更新或修改失败", e);
			return MessageVo.fullErrorMessage("更新或修改失败");
		}
	}
	
	@RequestMapping("/base/delete")
	@ResponseBody
	public Object baseDelete(Model model, int id) {
		try {
			service.delete(id);
			return MessageVo.fullSuccessMessage("删除成功");
		} catch(Exception e) {
			logger.error("根据id查询失败", e);
			return MessageVo.fullErrorMessage("删除失败");
		}
	}
	
}
