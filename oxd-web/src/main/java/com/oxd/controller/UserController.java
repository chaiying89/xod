package com.oxd.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
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
	
	protected static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService service;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest req, Model model) {
		try {
			Subject subject = SecurityUtils.getSubject();
			if(subject.isAuthenticated()) {
				return "redirect:/main";
			}
			String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
	        String error = null;
	        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
	            error = "用户名或密码错误";
	            logger.error(error);
	        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
	            error = "用户名或密码错误";
	            logger.error(error);
	        } else if(exceptionClassName != null) {
	            error = "未知错误";
	            logger.error(error + ":" + exceptionClassName);
	        }
	        model.addAttribute("error", error);
		} catch(Exception e) {
			model.addAttribute("error", "未知错误");
			logger.error("未知错误", e);
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
			Subject subject = SecurityUtils.getSubject();
			if(!password.equals(repassword)) {
				model.addAttribute("message", "register fail! <a href='userRegister'>register</a>");
				return "fail";
			}
			SecureRandomNumberGenerator sec = new SecureRandomNumberGenerator();
			String salt = sec.nextBytes().toHex();
			String pwd = new Md5Hash(password, username + salt, 2).toBase64();
			User u = new User();
			u.setSalt(salt);
			u.setPassword(pwd);
			u.setUsername(username);
			service.register(u);
			model.addAttribute("message", "register success! <a href='userLogin'>login in</a>");
		} catch(Exception e) {
			model.addAttribute("message", "register fail! <a href='userRegister'>register</a>");
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping(value = {"/", "/main"})
	public String success() {
		return "main";
	}
	
	@RequestMapping("/index")
	public String registerNewPage(Model model) {
		return "index";
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
	
	public static void main(String args[]) {
		SecureRandomNumberGenerator sec = new SecureRandomNumberGenerator();
		String salt = sec.nextBytes().toHex();
		String password = new Md5Hash("123","zhangsan"+salt,2).toBase64();
		System.out.println("salt：" + salt + "\r\n" + "password：" + password);
	}
}
