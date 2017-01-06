package com.teardowall.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.teardowall.common.Common;
import com.teardowall.mapper.WebGroupMapper;
import com.teardowall.mapper.WebSiteMapper;
import com.teardowall.models.GroupsSites;
import com.teardowall.models.WebGroup;
import com.teardowall.models.WebSite;
import com.teardowall.models.helpModel.WebConfig;

@Component
@Transactional
public class WebGroupService extends BaseService {
	
	@Resource
	private WebGroupMapper webGroupMapper;
	
	@Resource
	private WebSiteMapper webSiteMapper;
	
	@Resource
	private WebSiteService webSiteService;
	
	public List<WebGroup> getGroupsByUserId(String userId){
		List<WebGroup> groups = webGroupMapper.getGroupsByUserId(userId);
//		if(groups == null || groups.size() == 0){
//			groups = getDefaultGroups();
//		}
		return groups;
	}
	
	public WebGroup getGroupById(String groupId){
		WebGroup group = webGroupMapper.getGroupById(groupId);
		return group;
	}
	
	public List<WebGroup> getDefaultGroups(){
		//WebGroup[] groups = webGroupMapper.getDefaultGroups();
		List<WebGroup> groups = webGroupMapper.getDefaultGroups();
		//List<Map<WebGroup, WebSite[]>> list = new ArrayList<Map<WebGroup, WebSite[]>>();
		//Map<WebGroup, WebSite[]> hash = new HashMap<>();
		return groups;
		//return hash;
	}
	
	public List<WebSite> getSitesByGroupId(String groupId){
		return webSiteMapper.getSitesByGroupId(groupId);
	}
	
	public List<WebSite> getSitesByGroupIdAndFillUp(String groupId){
		List<WebSite> ws = new ArrayList<>();
		if(Common.stringIsEmpty(groupId) == false){
			ws = getSitesByGroupId(groupId);
		}
		return fillUpSite(ws, 8);
	}
	
	private List<WebSite> fillUpSite(List<WebSite> sites, int count){
		int addNum = count - sites.size();
		for(int i = 0; i < addNum; i++){
			sites.add(getNullSite());
		}
		return sites;
	}
	
	public List<List<WebSite>> getSitesByGroups(List<WebGroup> groups, boolean isFillUp){
		List<List<WebSite>> sites = new ArrayList<>();
		for(Iterator<WebGroup> it = groups.iterator(); it.hasNext();){
			WebGroup group = it.next();
			List<WebSite> ws = getSitesByGroupId(group.getId());
			for(Iterator<WebSite> its = ws.iterator(); its.hasNext(); ){
				WebSite site = its.next();
				site.setIconPath(webSiteService.iconPathById(site.getIconId()));
			}
			if(isFillUp){
				int addNum = 0;
				if(ws.size() <= 4){
					addNum = 4;// - ws.size();
				}
				else if(ws.size() < 8){
					addNum = 8;// - ws.size();
				}
//				for(int i = 0; i < addNum; i++){
//					ws.add(getNullSite());
//				}
				ws = fillUpSite(ws, addNum);
			}
			sites.add(ws);
		}
		return sites;
	}
	
	public List<List<WebSite>> getSitesByGroups(List<WebGroup> groups){
		return getSitesByGroups(groups, true);
	}
	
	private WebSite getNullSite(){
		return webSiteService.newSite("", "", "", 0);
	}
	
	private WebGroup newGroup(String name, String userId, int isDefault){
		WebGroup group = new WebGroup();
		group.setName(name);
		group.setUserId(userId);
		group.setIsDefault(isDefault);
		Date date = new Date();
		group.setCreatedAt(date);
		group.setUpdatedAt(date);
		return group;
	}
	
	public void addDefaultGroup2NewUser(String userId){
		List<WebGroup> groups = getDefaultGroups();
		List<List<WebSite>> sites = getSitesByGroups(groups, false);
		for(Iterator<WebGroup> it = groups.iterator(); it.hasNext();){
			WebGroup group = it.next();
			webGroupMapper.insertGroup(newGroup(group.getName(), userId, 0));
		}
		List<WebGroup> thisGroups = getGroupsByUserId(userId);
		int groupIndex = 0;
		for(Iterator<WebGroup> it = thisGroups.iterator(); it.hasNext();){
			WebGroup group = it.next();
			int serialNum = 0;
			for(Iterator<WebSite> itSite = sites.get(groupIndex).iterator(); itSite.hasNext();){
				WebSite site = itSite.next();
				serialNum++;
				WebSite thisSite = webSiteService.newSite(site.getName(), site.getIconId(), site.getWebUrl(), 0);
				webSiteMapper.insertWeb(thisSite);
				webSiteMapper.insertGroupSiteRelation(webSiteService.newGroupSite(group.getId(), thisSite.getId(), serialNum));
			}
			groupIndex++;
		}
	}
	
	public void updateGroup(WebConfig webConfig, String userId){
		if(Common.stringIsEmpty(webConfig.getWebGroupId())){
			return;
		}
		WebGroup group = getGroupById(webConfig.getWebGroupId());
		if(Common.stringIsEmpty(webConfig.getWebGroupName())){
			return;
		}
		if(group != null && group.getName().equals(webConfig.getWebGroupName()) == false){
			group.setName(webConfig.getWebGroupName());
			group.setUpdatedAt(new Date());
			webGroupMapper.updateGroup(group);
		}
		if(group == null || Common.stringIsEmpty(webConfig.getWebGroupId())){
			group = newGroup(webConfig.getWebGroupName(), userId, 0);
			webGroupMapper.insertGroup(group);
		}
		if(webConfig.validate()){
			webSiteMapper.deleteSitesByGroupId(group.getId());
			webSiteMapper.deleteGroupSiteRelationByGroupId(group.getId());
			for(int i = 0; i < webConfig.getUrl().size(); i++){
				//返回值，值得深究
				WebSite site = webSiteService.newSite(webConfig.getName().get(i), webConfig.getIcon().get(i), webConfig.getUrl().get(i), 0);
				webSiteMapper.insertWeb(site);
				webSiteMapper.insertGroupSiteRelation(webSiteService.newGroupSite(group.getId(), site.getId(), i+1));
			}
		}
		
	}
}
