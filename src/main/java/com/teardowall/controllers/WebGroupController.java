package com.teardowall.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teardowall.common.Common;
import com.teardowall.models.WebGroup;
import com.teardowall.models.WebSite;
import com.teardowall.models.helpModel.WebConfig;
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
	public String defaultPage(HttpServletRequest request, Model model){
		System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
		getSession();
		System.out.println(userId);
		List<WebGroup> groups = null;
		if(Common.stringIsEmpty(userId)){
			groups = webGroupService.getDefaultGroups();
		}
		else{
			groups = webGroupService.getGroupsByUserId(userId);
		}
		List<List<WebSite>> sites = webGroupService.getSitesByGroups(groups);
		if(Common.stringIsEmpty(userName)){
			model.addAttribute("username", "登录");
		}
		else{
			model.addAttribute("username", userName);
		}
		model.addAttribute("groups", groups);
		model.addAttribute("sites", sites);
		return "index";
	}
	
	@RequestMapping(value="/{webGroupId}/web_config", method = RequestMethod.GET)
	public String configWebGroup(HttpServletRequest request, Model model, @PathVariable String webGroupId){
		if(Common.stringIsEmpty(webGroupId)){
			return "redirect:/web_group/default";
		}
		getSession();
		if(Common.stringIsEmpty(userId)){
			return "redirect:/login";
		}
		WebGroup group = webGroupService.getGroupById(webGroupId);
		List<WebSite> sites = webGroupService.getSitesByGroupIdAndFillUp(webGroupId);
		model.addAttribute("webGroupId", webGroupId);
		model.addAttribute("webGroupName", group.getName());
		model.addAttribute("username", userName);
		model.addAttribute("sites", sites);
		return "webGroups/configWeb";
	}
	
	@RequestMapping(value="/add_group", method = RequestMethod.GET)
	public String addOneGroup(HttpServletRequest request, Model model){
		getSession();
		if(Common.stringIsEmpty(userId)){
			return "redirect:/login";
		}
		WebGroup group = new WebGroup();
		List<WebSite> sites = webGroupService.getSitesByGroupIdAndFillUp("0");
		model.addAttribute("webGroupId", "0");
		model.addAttribute("webGroupName", group.getName());
		model.addAttribute("username", userName);
		model.addAttribute("sites", sites);
		return "webGroups/configWeb";
	}
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request,Model model){
		getSession();
		if(Common.stringIsEmpty(userId)){
			return "redirect:/login";
		}
		List<WebGroup> groups = webGroupService.getGroupsByUserId(userId);
		List<List<WebSite>> sites = webGroupService.getSitesByGroups(groups);
		model.addAttribute("username", userName);
		model.addAttribute("groups", groups);
		model.addAttribute("sites", sites);
		return "index";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(WebConfig webConfig,HttpServletRequest request,Model model){
		getSession();
		if(Common.stringIsEmpty(userId)){
			return "redirect:/login";
		}
		webGroupService.updateGroup(webConfig, userId);
		return "redirect:/web_group/index";
	}
}
