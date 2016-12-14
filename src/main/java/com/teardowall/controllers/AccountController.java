package com.teardowall.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/account")
public class AccountController extends BaseController {
	
	@RequestMapping(value = "/sign_up", method = RequestMethod.GET)
	public String signUp() {
		
		return "";
	}
}
