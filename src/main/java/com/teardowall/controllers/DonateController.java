package com.teardowall.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projectapi.teardowall.entity.DonateInfo;
import com.teardowall.services.rpc.CreeperRpcService;

@Controller
@RequestMapping(value = "/donate")
public class DonateController extends BaseController {
	
	@Resource
	private CreeperRpcService creeperRpcService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model){
		List<DonateInfo> donateInfos = creeperRpcService.getAllDonateInfos();
		model.addAttribute("donateInfos", donateInfos);
		return "donates/index";
	}
	
}
