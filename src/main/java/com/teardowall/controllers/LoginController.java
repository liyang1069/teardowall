package com.teardowall.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		System.out.println("GGGGGGGGGGGGGGGGG");
		return "index";
	}
}
