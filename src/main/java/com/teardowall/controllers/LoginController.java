package com.teardowall.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String login(Model model) {
		System.out.println("GGGGGGGGGGGGGGGGG");
		
		return "account/signin";
	}
	
	private void loginOld(Model model){
		//System.out.println(webSiteService.getTest(1));
		//webSiteService.saveDefaultSites();
		List<WebGroup> groups = webGroupService.getDefaultGroups();
		List<List<WebSite>> sites = webGroupService.getSitesByGroups(groups);
		model.addAttribute("groups", groups);
		model.addAttribute("sites", sites);
		//WebGroup www = hash.keySet().toArray()[0];
		System.out.println(groups.size());
	}
}
