package com.teardowall.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.teardowall.common.Common;
import com.teardowall.mapper.WebSiteMapper;
import com.teardowall.models.GroupsSites;
import com.teardowall.models.Icon;
import com.teardowall.models.WebSite;

@Component
@Transactional
public class WebSiteService extends BaseService {
	
	@Resource
	private WebSiteMapper webSiteMapper;
	
	public int getTest(int id){
		return webSiteMapper.getTest(id);
	}
	
	public void saveIcon(){
	  String path = Common.icon_path_absolute;
	  File file=new File(path);
	  File[] tempList = file.listFiles();
	  //List<Icon> list = new ArrayList<Icon>();
	  System.out.println("该目录下对象个数："+tempList.length);
	  for (int i = 0; i < tempList.length; i++) {
	   if (tempList[i].isFile()) {
	     System.out.println("文件："+tempList[i]);
	     //list.add(file2Icon(tempList[i]));
	     webSiteMapper.insertIcon(file2Icon(tempList[i]));
	   }
	   if (tempList[i].isDirectory()) {
	    System.out.println("文件夹："+tempList[i]);
	   }
	  }
	  //webSiteMapper.insertIconBatch(list);
	}
	
	private Icon file2Icon(File file){
		Icon icon = new Icon();
		String name = file.getName();
		icon.setName(name);
		icon.setPath(Common.icon_path_relative + name);
		icon.setSize(file.length()/1024);
		String[] sp = name.split("\\.");
		icon.setKeyword(sp[0]);
		return icon;
	}
	
	public void saveDefaultSites(){
		String[] sites = Common.sites.split(";");
		for(int i = 0; i < sites.length; i++){
			String[] oneSite = sites[i].split(",");
			webSiteMapper.insertWeb(string2Site(oneSite));
		}
	}
	private WebSite string2Site(String[] oneSite){
		WebSite site = new WebSite();
		site.setName(oneSite[0]);
		site.setWebUrl(oneSite[1]);
		//site.setIconPath(Common.icon_path_relative + oneSite[2] + ".png");
		site.setIsDefault(1);
		Date date = new Date();
		site.setCreatedAt(date);
		site.setUpdatedAt(date);
		return site;
	}
	
	public String iconPathById(String iconId){
		if(iconId == null || iconId.isEmpty()){
			return Common.defaultIconPath;
		}
		String iconPath = webSiteMapper.iconPathById(iconId);
		if(iconPath == null || iconPath.isEmpty()){
			iconPath = Common.defaultIconPath;
		}
		return iconPath;
	}
	
	public WebSite newSite(String name, String iconId, String webUrl, int isDefault){
		WebSite site = new WebSite();
		site.setName(name);
		if(Common.stringIsEmpty(iconId)){
			iconId = Common.defaultIconId;
		}
		site.setIconId(iconId);
		site.setWebUrl(webUrl);
		site.setIsDefault(isDefault);
		Date date = new Date();
		site.setCreatedAt(date);
		site.setUpdatedAt(date);
		return site;
	}
	
	public GroupsSites newGroupSite(String groupId, String siteId, int serialNum){
		GroupsSites gs = new GroupsSites();
		gs.setWebGroupId(groupId);
		gs.setWebSiteId(siteId);
		gs.setSerialNum(serialNum);
		Date date = new Date();
		gs.setCreatedAt(date);
		gs.setUpdatedAt(date);
		return gs;
	}
}
