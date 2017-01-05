package com.teardowall.models.helpModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.teardowall.common.Common;

public class WebConfig {
	private String webGroupId;
	private String webGroupName;
	private List<String> url;
	private List<String> name;
	private List<String> icon;
	private List<Integer> serial;
    
	public List<String> getIcon() {
		return icon;
	}
	public void setIcon(List<String> icon) {
		this.icon = icon;
	}
	public String getWebGroupName() {
		return webGroupName;
	}
	public List<String> getUrl() {
		return url;
	}
	public void setUrl(List<String> url) {
		this.url = url;
	}
	public List<String> getName() {
		return name;
	}
	public void setName(List<String> name) {
		this.name = name;
	}
	public List<Integer> getSerial() {
		return serial;
	}
	public void setSerial(List<Integer> serial) {
		this.serial = serial;
	}
	public void setWebGroupName(String webGroupName) {
		this.webGroupName = webGroupName;
	}
	public String getWebGroupId() {
		return webGroupId;
	}
	public void setWebGroupId(String webGroupId) {
		this.webGroupId = webGroupId;
	}
	
	public boolean validate(){
		if(url == null || name == null || serial == null || icon == null || url.size() != name.size() || url.size() != serial.size() || url.size() != icon.size()){
			return false;
		}
		List<Integer> removeList = new ArrayList<>();
		for(int i = 0; i < url.size(); i++){
			if(Common.stringIsEmpty(url.get(i)) || Common.stringIsEmpty(name.get(i))){
				removeList.add(i);
			}
		}
		for(int i = removeList.size() - 1; i >= 0; i-- ){
			int j = removeList.get(i);
			url.remove(j);
			name.remove(j);
			icon.remove(j);
			serial.remove(j);
		}
		List<String> newUrl = new ArrayList<>();
		List<String> newName = new ArrayList<>();
		List<String> newIcon = new ArrayList<>();
		List<Integer> oldSerial = new ArrayList<>();
		oldSerial.addAll(serial);
		Collections.sort(serial);
		for(int i = 0; i < serial.size(); i++){
			int index = oldSerial.indexOf(serial.get(i));
			newUrl.add(url.get(index));
			newName.add(name.get(index));
			newIcon.add(icon.get(index));
		}
		url.clear();
		url.addAll(newUrl);
		name.clear();
		name.addAll(newName);
		icon.clear();
		icon.addAll(newIcon);
		return true;
	}
}
