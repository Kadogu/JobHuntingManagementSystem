package dto;

import java.io.Serializable;

public class Account implements Serializable{
	private String user_id;
	private String pw;

	public Account(){}
	public Account(String user_id, String pw) {
		this.user_id = user_id;
		this.pw = pw;
	}

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
}