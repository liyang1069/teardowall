package com.teardowall.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teardowall.services.account.ShiroDbRealm.ShiroUser;


@Controller
@RequestMapping(value = "/base")
public class BaseController {

	protected String userName;
	protected String userId;

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "xxxxxxxxxxx";
	}
	
	@ModelAttribute
	protected void volidateUser(HttpServletRequest request, Model model){
		if ((SecurityUtils.getSubject().isAuthenticated() == false && SecurityUtils.getSubject().isRemembered() == false) || request.getRequestURI().indexOf("/login") > 0 || request.getRequestURI().indexOf("/logout") > 0){
			if (request.getRequestURI().indexOf("/login") < 0 && request.getRequestURI().indexOf("/logout") < 0){
				model.addAttribute("username", "登录");
			}
			return;
		}
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		userId = user.id;
		userName = user.name;
		model.addAttribute("username", userName);
	}
	
}
