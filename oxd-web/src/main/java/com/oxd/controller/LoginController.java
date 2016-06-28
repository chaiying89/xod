package com.oxd.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台登录Controller
 * @author CHAIYING
 *
 */
@Controller
public class LoginController {
	
	protected static final Logger logger = Logger.getLogger(LoginController.class);
	
	/**
	 * 登录验证实现
	 * @param req
	 * @param model
	 * @return
	 */
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
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}
}
