package com.teardowall.models;

public class WebSite extends BaseModel {
	private String name;
	private String webUrl;
	private String iconId;
	private int isDefault;
	private String iconPath;
//	private String webGroupId;
	
	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

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
	public String getIconId() {
		return iconId;
	}
	public void setIconId(String iconId) {
		this.iconId = iconId;
	}
}
