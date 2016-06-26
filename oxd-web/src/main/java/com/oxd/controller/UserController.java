package com.oxd.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.oxd.model.User;
import com.oxd.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest req, Model model) {
		try {
			Subject subject = SecurityUtils.getSubject();
			if(subject.isAuthenticated()) {
				return "redirect:/ajax_page";
			}
			String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
	        String error = null;
	        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
	            error = "用户名/密码错误";
	        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
	            error = "用户名/密码错误";
	        } else if(exceptionClassName != null) {
	            error = "未知错误" + exceptionClassName;
	        }
	        model.addAttribute("error", error);
			model.addAttribute("message", "login success!");
		} catch(Exception e) {
			model.addAttribute("message", "login fail! username or password is wrong, please try agin. <a href='userLogin'>login in</a>");
			return "fail";
		}
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}
	
	@RequestMapping("/userRegister")
	public String registerPage(Model model) {
		return "register";
	}
	
	@RequestMapping("/editor")
	public String editor(Model model) {
		return "online_editor";
	}
	
	@RequestMapping("/register")
	public String register(Model model, 
			@RequestParam(required = true)String username, 
			@RequestParam(required = true)String password, 
			@RequestParam(required = true)String repassword) {
		try {
			if(!password.equals(repassword)) {
				model.addAttribute("message", "register fail! <a href='userRegister'>register</a>");
				return "fail";
			}
			User u = new User();
			u.setPassword(password);
			u.setUsername(username);
			service.register(u);
			model.addAttribute("message", "register success! <a href='userLogin'>login in</a>");
		} catch(Exception e) {
			model.addAttribute("message", "register fail! <a href='userRegister'>register</a>");
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping("/success")
	public String success() {
		return "success";
	}
	
	@RequestMapping("/ajax_page")
	public String registerNewPage(Model model) {
		return "ajax_page";
	}
	
	@RequestMapping("/ajax")
	@ResponseBody
	public Object returnJson(Model model, HttpServletRequest request) {
		User u = new User();
		u.setId(1);
		u.setPassword("123");
		u.setUsername("chaiying");
		return checkJsonpCallback(request, u);
	}
	
	
	@SuppressWarnings("static-access")
	public String checkJsonpCallback(HttpServletRequest request, Object obj) {
		String callname = request.getParameter("callback");
		JSONObject json = new JSONObject();
		return callname != null ? callname + "(" + json.toJSONString(obj) + ")" : json.toJSONString(obj);
	}
}
