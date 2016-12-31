package com.teardowall.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/account")
public class AccountController extends BaseController {
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public void signin(){
		System.out.println("============signin===========");
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public void signup(){
		System.out.println("============signup===========");
	}
}
