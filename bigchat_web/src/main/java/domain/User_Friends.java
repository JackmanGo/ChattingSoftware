package domain;

public class User_Friends {
	private String user_friends_id;
	private User user_a;
	private User user_b;


	public String getUser_friends_id() {
		return user_friends_id;
	}

	public void setUser_friends_id(String user_friends_id) {
		this.user_friends_id = user_friends_id;
	}

	public User getUser_a() {
		return user_a;
	}

	public void setUser_a(User user_a) {
		this.user_a = user_a;
	}

	public User getUser_b() {
		return user_b;
	}

	public void setUser_b(User user_b) {
		this.user_b = user_b;
	}

}
