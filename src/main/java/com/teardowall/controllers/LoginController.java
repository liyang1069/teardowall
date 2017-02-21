package com.teardowall.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectapi.teardowall.CreeperService;
import com.teardowall.common.Common;
import com.teardowall.models.User;
import com.teardowall.services.WebGroupService;
import com.teardowall.services.WebSiteService;
import com.teardowall.services.account.AccountService;
import com.teardowall.services.rpc.CreeperRpcService;

@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {
	
	@Resource
	private WebSiteService webSiteService;
	
	@Resource
	private WebGroupService webGroupService;
	
	@Resource
	private AccountService accountService;
	
	@Resource
	private CreeperRpcService creeperRpcService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) {
		System.out.println("GGGGGGGGGGGGGGGGG");
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
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		Subject currentUser = SecurityUtils.getSubject();
		try {
		    currentUser.login(token);
		} catch (IncorrectCredentialsException ice) {
			model.addAttribute("msg", "请输入正确的用户名和密码!");
			return "account/signin";
		} catch (LockedAccountException lae) {
			model.addAttribute("msg", "请验证邮箱!");
			return "account/signin";
		}
		catch (AuthenticationException ae) {
			return "account/signin";
		}
		return "redirect:/web_group/index";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public void signup(){
		System.out.println("PPPPPPPPPPPPPPPPPPPPPP");
	}
	
	@ResponseBody
	@RequestMapping(value="/dubbo", method = RequestMethod.GET)
	public String dubbo(){
		String string = creeperRpcService.dubbo();
		System.out.println(string);
		return string;
	}
	
//	private void loginOld(Model model){
//		//System.out.println(webSiteService.getTest(1));
//		//webSiteService.saveDefaultSites();
//		List<WebGroup> groups = webGroupService.getDefaultGroups();
//		List<List<WebSite>> sites = webGroupService.getSitesByGroups(groups);
//		model.addAttribute("groups", groups);
//		model.addAttribute("sites", sites);
//		//WebGroup www = hash.keySet().toArray()[0];
//		System.out.println(groups.size());
//	}
}
