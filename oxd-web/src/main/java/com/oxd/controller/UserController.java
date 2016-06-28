package com.oxd.controller;



import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oxd.model.UserModel;
import com.oxd.service.UserService;
import com.oxd.vo.MessageVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	protected static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/pwd_update", method = RequestMethod.POST)
	@ResponseBody
	public Object pwdUpdate(Model model, 
			@RequestParam(required = true)String oldPwd,
			@RequestParam(required = true)String newPwd, 
			@RequestParam(required = true)String reNewPwd) {
		try {
			if(!newPwd.equals(reNewPwd)) {
				return MessageVo.fullErrorMessage("两次密码不同，请确认后再修改");
			}
			UserModel user = (UserModel) SecurityUtils.getSubject().getPrincipal();
			String pwd = new Md5Hash(oldPwd, user.getCredentialsSalt(), 2).toBase64();
			if(user == null || !pwd.equals(user.getPassword())) {
				return MessageVo.fullErrorMessage("原始密码错误");
			}
			
			pwd = new Md5Hash(newPwd, user.getCredentialsSalt(), 2).toBase64();
			user.setPassword(pwd);
			service.update(user);
			SecurityUtils.getSubject().logout();
			return MessageVo.fullSuccessMessage("修改成功");
		} catch(Exception e) {
			return MessageVo.fullErrorMessage("密码修改失败");
		}
	}
}
