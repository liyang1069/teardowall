package com.teardowall.models;

public class User extends BaseModel {
	private String name;
	private String email;
	private int emailActive;
	private String nickName;
	private String phoneNumber;
	private String password;
	private int salt;
	
	public int getSalt() {
		return salt;
	}
	public void setSalt(int salt) {
		this.salt = salt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEmailActive() {
		return emailActive;
	}
	public void setEmailActive(int emailActive) {
		this.emailActive = emailActive;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
