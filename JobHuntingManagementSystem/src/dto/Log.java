package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Log implements Serializable{
	private int log_id;
	private LocalDateTime log_datetime;
	private String login_id;
	private String login_pw;
	private boolean login_sorf;

	public Log(){}
	public Log(int log_id, LocalDateTime log_datetime, String login_id, String login_pw, boolean login_sorf) {
		this.log_id = log_id;
		this.log_datetime = log_datetime;
		this.login_id = login_id;
		this.login_pw = login_pw;
		this.login_sorf = login_sorf;
	}

	public int getLog_id() {
		return log_id;
	}
	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}

	public LocalDateTime getLog_datetime() {
		return log_datetime;
	}
	public void setLog_datetime(LocalDateTime log_datetime) {
		this.log_datetime = log_datetime;
	}

	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getLogin_pw() {
		return login_pw;
	}
	public void setLogin_pw(String login_pw) {
		this.login_pw = login_pw;
	}

	public boolean isLogin_sorf() {
		return login_sorf;
	}
	public void setLogin_sorf(boolean login_sorf) {
		this.login_sorf = login_sorf;
	}
}