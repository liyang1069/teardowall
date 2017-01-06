package com.teardowall.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

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
	public String login(HttpServletRequest request, Model model) {
		System.out.println("GGGGGGGGGGGGGGGGG");
		getSession(request);
		if(Common.stringIsEmpty(userId) == false){
			User user = accountService.findUserById(userId);
			if(user != null && user.getEmailActive() == 1){
				return "redirect:/web_group/index";
			}
		}
		
		return "account/signin";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doLogin(@RequestParam String username, @RequestParam String password,HttpServletRequest request, Model model) {
		System.out.println("DDDDDDDDDDDDDDDDD");
		if(Common.stringIsEmpty(username) || Common.stringIsEmpty(password)){
			model.addAttribute("msg", "请输入完整的用户名和密码!");
			return "account/signin";
		}
		User user = accountService.findUserByEmail(username);
		if(user.getPassword().equals(Common.encrypyPasswd(password + Common.passwdSuffix + user.getSalt())) && user.getEmailActive() == 1){
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getNickName());
			session.setAttribute("userId", user.getId());
			return "redirect:/web_group/index";
		}
		else{
			if(user.getEmailActive() != 1){
				model.addAttribute("msg", "请验证邮箱!");
			}
			else{
				model.addAttribute("msg", "请输入正确的用户名和密码!");
			}
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
