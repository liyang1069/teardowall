package com.teardowall.controllers;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teardowall.models.WebGroup;
import com.teardowall.models.WebSite;
import com.teardowall.services.WebGroupService;
import com.teardowall.services.WebSiteService;

@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {
	
	@Resource
	private WebSiteService webSiteService;
	
	@Resource
	private WebGroupService webGroupService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		System.out.println("GGGGGGGGGGGGGGGGG");
		//System.out.println(webSiteService.getTest(1));
		//webSiteService.saveDefaultSites();
		Map<WebGroup, WebSite[]> hash = webGroupService.getDefaultGroups();
		System.out.println(hash.size());
		return "index";
	}
}
