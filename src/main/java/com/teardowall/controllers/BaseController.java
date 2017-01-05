package com.teardowall.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/base")
public class BaseController {

	protected String userName;
	protected String userId;
	
	protected void getSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		userId = (String)session.getAttribute("userId");
		userName = (String)session.getAttribute("username");
	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "xxxxxxxxxxx";
	}
	
}
