package com.bigchat.domain;

public class User {
	private String userId;
	private String userName;
	private String userPassword;
	private String signature;
	private String userIcon;//存放的是图片路径

	public User(){

	}

	public User(String userId,String userPassword){
		this.userId=userId;
		this.userPassword=userPassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				", userPassword='" + userPassword + '\'' +
				", signature='" + signature + '\'' +
				", userIcon='" + userIcon + '\'' +
				'}';
	}
}
