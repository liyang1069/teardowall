package com.teardowall.models;

public class GroupsSites extends BaseModel {
	private String webGroupId;
	private String webSiteId;
	private int serialNum;
	
	public int getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}
	public String getWebGroupId() {
		return webGroupId;
	}
	public void setWebGroupId(String webGroupId) {
		this.webGroupId = webGroupId;
	}
	public String getWebSiteId() {
		return webSiteId;
	}
	public void setWebSiteId(String webSiteId) {
		this.webSiteId = webSiteId;
	}
}
