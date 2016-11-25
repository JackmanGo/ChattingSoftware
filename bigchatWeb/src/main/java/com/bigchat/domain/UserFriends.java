package com.bigchat.domain;

public class UserFriends {
	private String userFriendsId;
	private User userA;
	private User userB;

	public String getUserFriendsId() {
		return userFriendsId;
	}

	public void setUserFriendsId(String userFriendsId) {
		this.userFriendsId = userFriendsId;
	}

	public User getUserA() {
		return userA;
	}

	public void setUserA(User userA) {
		this.userA = userA;
	}

	public User getUserB() {
		return userB;
	}

	public void setUserB(User userB) {
		this.userB = userB;
	}

	@Override
	public String toString() {
		return "UserFriends{" +
				"userFriendsId='" + userFriendsId + '\'' +
				", userA=" + userA +
				", userB=" + userB +
				'}';
	}
}
