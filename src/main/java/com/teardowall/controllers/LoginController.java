package com.teardowall.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.teardowall.common.Common;
import com.teardowall.models.User;
import com.teardowall.models.WebGroup;
import com.teardowall.models.WebSite;
import com.teardowall.services.WebGroupService;
import com.teardowall.services.WebSiteService;
import com.teardowall.services.account.AccountService;

@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {
	
	@Resource
	private WebSiteService webSiteService;
	
	@Resource
	private WebGroupService webGroupService;
	
	@Resource
	private AccountService accountService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model) {
		System.out.println("GGGGGGGGGGGGGGGGG");
		
		return "account/signin";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doLogin(@RequestParam String username, @RequestParam String password, Model model) {
		System.out.println("DDDDDDDDDDDDDDDDD");
		User user = accountService.findUserByEmail(username);
		if(user.getPassword().equals(Common.encrypyPasswd(password)) && user.getEmailActive() == 1){
			List<WebGroup> groups = webGroupService.getGroupsByUserId(user.getId());
			List<List<WebSite>> sites = webGroupService.getSitesByGroups(groups);
			model.addAttribute("groups", groups);
			model.addAttribute("sites", sites);
			return "index";
		}
		else{
			return "account/signin";
		}
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public void signup(){
		System.out.println("PPPPPPPPPPPPPPPPPPPPPP");
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
