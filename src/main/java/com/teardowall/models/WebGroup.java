package com.teardowall.models;

public class WebGroup extends BaseModel {
	private String name;
	private String userId;
	private int isDefault;
	
	public WebGroup(){
		
	}
	
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
