package dto;

import java.io.Serializable;

public class Teacher implements Serializable{
	private int teacher_id;
	private String name;
	private String belongs_id;
	private String mail_address;
	private boolean admin_flg;
	private String image_filename;
	private String user_id;

	public Teacher(){}
	public Teacher(int teacher_id, String name, String belongs_id, String mail_address, boolean admin_flg,
			String image_filename, String user_id) {
		this.teacher_id = teacher_id;
		this.name = name;
		this.belongs_id = belongs_id;
		this.mail_address = mail_address;
		this.admin_flg = admin_flg;
		this.image_filename = image_filename;
		this.user_id = user_id;
	}

	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getBelongs_id() {
		return belongs_id;
	}
	public void setBelongs_id(String belongs_id) {
		this.belongs_id = belongs_id;
	}

	public String getMail_address() {
		return mail_address;
	}
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}

	public boolean isAdmin_flg() {
		return admin_flg;
	}
	public void setAdmin_flg(boolean admin_flg) {
		this.admin_flg = admin_flg;
	}

	public String getImage_filename() {
		return image_filename;
	}
	public void setImage_filename(String image_filename) {
		this.image_filename = image_filename;
	}

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}