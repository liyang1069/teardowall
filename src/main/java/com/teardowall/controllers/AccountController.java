package com.teardowall.controllers;

import java.io.IOException;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.teardowall.common.Common;
import com.teardowall.models.User;
import com.teardowall.services.account.AccountService;

@Controller
@RequestMapping(value = "/account")
public class AccountController extends BaseController {
	
	@Resource
	private AccountService accountService;
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public void signin(){
		System.out.println("============signin===========");
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@RequestParam String fullname, @RequestParam(value="email_up") String emailUp,
			@RequestParam(value="password_up") String passwordUp, Model model) throws IOException, MessagingException{
		System.out.println("============signup===========");
		if(Common.verifyEmail(emailUp) == false){
			model.addAttribute("msg", "账户已存在，请使用其他邮箱!");
		}
		else if(accountService.findUserByEmail(emailUp) != null){
			model.addAttribute("msg", "账户已存在，请使用其他邮箱!");
		}
		else{
			accountService.addUser(fullname, emailUp, passwordUp);
			User user = accountService.findUserByEmail(emailUp);
			if(user == null || user.getId().equals("0")){
				model.addAttribute("msg", "账户申请失败，请联系管理员!");
			}
			else{
				model.addAttribute("msg", "账户申请成功，请登陆邮箱验证!");
				accountService.sendAuthenEmail(user);
			}
		}
		return "account/msg";
	}
}
