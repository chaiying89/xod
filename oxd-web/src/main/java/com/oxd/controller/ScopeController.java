package com.oxd.controller;



import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oxd.model.ScopeModel;
import com.oxd.service.ScopeService;
import com.oxd.vo.MessageVo;
import com.oxd.vo.ScopeVo;

/**
 * 经营范围后台controller
 * @author chaiying
 *
 */
@Controller
@RequestMapping("/scope")
public class ScopeController {
	
	protected static final Logger logger = Logger.getLogger(ScopeController.class);

	@Autowired
	private ScopeService service;
	
	@RequestMapping(value = {"/", "/main"})
	public String main() {
		return "scopeMgr";
	}
	
	
	@RequestMapping("/search")
	@ResponseBody
	public Object search(Model model) {
		List<ScopeVo> list = new ArrayList<ScopeVo>();
		try {
			list = service.search();
		} catch(Exception e) {
			logger.error("[投资加盟]查询失败", e);
		}
		return list;
	}
	
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public Object saveOrUpdate(Model model, @RequestBody ScopeModel scopeModel) {
		try {
			String msg = "新增成功";
			if(scopeModel.getId() != 0) {
				msg = "修改成功";
			}
			service.saveOrUpdate(scopeModel);
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
}
