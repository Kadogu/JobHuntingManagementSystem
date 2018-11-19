package dto;

import java.io.Serializable;

public class Account implements Serializable{
	private String user_id;
	private String pw;
	private boolean account_lock_flg;

	public Account(){}
	public Account(String user_id, String pw, boolean account_lock_flg) {
		this.user_id = user_id;
		this.pw = pw;
		this.account_lock_flg = account_lock_flg;
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

	public boolean isAccount_lock_flg() {
		return account_lock_flg;
	}
	public void setAccount_lock_flg(boolean account_lock_flg) {
		this.account_lock_flg = account_lock_flg;
	}
}