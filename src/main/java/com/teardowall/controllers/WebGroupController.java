package com.teardowall.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectapi.teardowall.entity.WeatherBaidu;
import com.teardowall.common.Common;
import com.teardowall.models.WebGroup;
import com.teardowall.models.WebSite;
import com.teardowall.models.helpModel.WebConfig;
import com.teardowall.services.WebGroupService;
import com.teardowall.services.WebSiteService;
import com.teardowall.services.account.AccountService;

@Controller
@RequestMapping(value = "/web_group")
public class WebGroupController extends BaseController {
	
	@Resource
	private WebSiteService webSiteService;
	
	@Resource
	private WebGroupService webGroupService;
	
	@Resource
	private AccountService accountService;
	
	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String defaultPage(HttpServletRequest request, Model model){
		System.out.println(userId);
		List<WebGroup> groups = null;
		if(Common.stringIsEmpty(userId)){
			groups = webGroupService.getDefaultGroups();
		}
		else{
			groups = webGroupService.getGroupsByUserId(userId);
		}
		List<List<WebSite>> sites = webGroupService.getSitesByGroups(groups);
		model.addAttribute("groups", groups);
		model.addAttribute("sites", sites);
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value="/get_weather",method = RequestMethod.GET)
	public String getWeather(HttpServletRequest request){
		String weatherString = "";
		if(Common.stringIsEmpty(userId)){
			return weatherString;
		}
		String ip = null;
		ip = getIpAddress(request);
		if (ip == null || "0:0:0:0:0:0:0:1".equals(ip)){
			ip = "182.50.126.10";
		}
		weatherString = accountService.getWeatherByIp(ip, userId);
		return weatherString;
	}
	
	private String getIpAddress(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	  } 
	
	@RequestMapping(value="/{webGroupId}/web_config", method = RequestMethod.GET)
	public String configWebGroup(HttpServletRequest request, Model model, @PathVariable String webGroupId){
		if(Common.stringIsEmpty(webGroupId)){
			return "redirect:/web_group/default";
		}
		if(Common.stringIsEmpty(userId)){
			return "redirect:/login";
		}
		WebGroup group = webGroupService.getGroupById(webGroupId);
		List<WebSite> sites = webGroupService.getSitesByGroupIdAndFillUp(webGroupId);
		model.addAttribute("webGroupId", webGroupId);
		model.addAttribute("webGroupName", group.getName());
		model.addAttribute("sites", sites);
		return "webGroups/configWeb";
	}
	
	@RequestMapping(value="/add_group", method = RequestMethod.GET)
	public String addOneGroup(HttpServletRequest request, Model model){
		if(Common.stringIsEmpty(userId)){
			return "redirect:/login";
		}
		WebGroup group = new WebGroup();
		List<WebSite> sites = webGroupService.getSitesByGroupIdAndFillUp("0");
		model.addAttribute("webGroupId", "0");
		model.addAttribute("webGroupName", group.getName());
		model.addAttribute("sites", sites);
		return "webGroups/configWeb";
	}
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request,Model model){
		if(Common.stringIsEmpty(userId)){
			return "redirect:/login";
		}
		List<WebGroup> groups = webGroupService.getGroupsByUserId(userId);
		List<List<WebSite>> sites = webGroupService.getSitesByGroups(groups);
		model.addAttribute("groups", groups);
		model.addAttribute("sites", sites);
		return "index";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(WebConfig webConfig,HttpServletRequest request,Model model){
		if(Common.stringIsEmpty(userId)){
			return "redirect:/login";
		}
		webGroupService.updateGroup(webConfig, userId);
		return "redirect:/web_group/index";
	}
	
	@RequestMapping(value = "/{webGroupId}/delete_group", method = RequestMethod.GET)
	public String deleteGroup(Model model, @PathVariable String webGroupId){
		if(Common.stringIsEmpty(userId)){
			return "redirect:/login";
		}
		webGroupService.deleteGroup(webGroupId, userId);
		return "redirect:/web_group/index";
	}
}
