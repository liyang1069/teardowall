package com.teardowall.models;

public class WebSite extends BaseModel {
	private String name;
	private String webUrl;
	private String iconPath;
	private int isDefault;
//	private String webGroupId;
	
	public WebSite(){
		
	}
	
	public String getName() {
		return name;
	}
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String url) {
		this.webUrl = url;
	}
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
}
