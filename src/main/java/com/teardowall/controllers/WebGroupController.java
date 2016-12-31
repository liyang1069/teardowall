package com.teardowall.controllers;

import java.util.List;

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
@RequestMapping(value = "/web_group")
public class WebGroupController extends BaseController {
	
	@Resource
	private WebSiteService webSiteService;
	
	@Resource
	private WebGroupService webGroupService;
	
	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String defaultPage(Model model){
		System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
		List<WebGroup> groups = webGroupService.getDefaultGroups();
		List<List<WebSite>> sites = webGroupService.getSitesByGroups(groups);
		model.addAttribute("groups", groups);
		model.addAttribute("sites", sites);
		return "index";
	}
}
