package com.teardowall.controllers;

import java.io.IOException;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.teardowall.common.Common;
import com.teardowall.models.User;
import com.teardowall.services.WebGroupService;
import com.teardowall.services.account.AccountService;

@Controller
@RequestMapping(value = "/account")
public class AccountController extends BaseController {
	
	@Resource
	private AccountService accountService;
	
	@Resource
	private WebGroupService webGroupService;
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public void signin(){
		System.out.println("============signin===========");
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@RequestParam String fullname, @RequestParam(value="email_up") String emailUp,
			@RequestParam(value="password_up") String passwordUp, Model model) throws IOException, MessagingException{
		System.out.println("============signup===========");
		String msg = "";
		if(Common.stringIsEmpty(emailUp) || Common.stringIsEmpty(fullname) || Common.stringIsEmpty(passwordUp)){
			msg = "填写信息格式不正确,请重新填写!";
		}
		else if(passwordUp.length() < 6 || passwordUp.length() > 16){
			msg = "密码请控制在6—16位!";
		}
		else if(Common.verifyEmail(emailUp) == false){
			msg = "账户已存在，请使用其他邮箱!";
		}
		else if(accountService.findUserByEmail(emailUp) != null){
			msg = "账户已存在，请使用其他邮箱!";
		}
		else{
			accountService.addUser(fullname, emailUp, passwordUp);
			User user = accountService.findUserByEmail(emailUp);
			if(user == null || user.getId().equals("0")){
				msg = "账户申请失败，请联系管理员!";
			}
			else{
				msg = "账户申请成功，请登陆邮箱验证!";
				accountService.sendAuthenEmail(user);
				webGroupService.addDefaultGroup2NewUser(user.getId());
				model.addAttribute("msg", msg);
				return "account/msg";
			}
		}
		model.addAttribute("msg", msg);
		return "account/msg";
//		model.addAttribute("msgSignUp", msg);
//		model.addAttribute("view", "signup");
//		return "account/signin";
	}
	
	@RequestMapping(value="/auth_email", method = RequestMethod.GET)
	public String authEamil(@RequestParam(value="auth_string") String authString, @RequestParam(value="auth_id") String email, HttpServletRequest request, Model model){
		User user = accountService.findUserByEmail(email);
		int result = accountService.authEamil(user, authString);
		String msg = "";
		if(result == 0){
			msg = "已经验证,无需重复!";
		}
		else if(result == 1){
			msg = "验证成功,点此登录!";
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getNickName());
			session.setAttribute("userId", user.getId());
			model.addAttribute("href", "/web_group/index");
		}
		else{
			msg = "验证失败!";
		}
		model.addAttribute("msg", msg);
		return "account/msg";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(Common.stringIsEmpty((String)session.getAttribute("userId")) == false){
			session.removeAttribute("username");
			session.removeAttribute("userId");
		}
		return "redirect:/login";
	}
}
