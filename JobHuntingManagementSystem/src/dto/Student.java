package dto;

import java.io.Serializable;

public class Student implements Serializable{
	private int student_id;
	private String name;
	private String mail_address;
	private int school_year;
	private String course_id;
	private int job_hunting_state;
	private String user_id;

	public Student(){}
	public Student(int student_id, String name, String mail_address, int school_year, String course_id,
			int job_hunting_state, String user_id) {
		this.student_id = student_id;
		this.name = name;
		this.mail_address = mail_address;
		this.school_year = school_year;
		this.course_id = course_id;
		this.job_hunting_state = job_hunting_state;
		this.user_id = user_id;
	}

	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getMail_address() {
		return mail_address;
	}
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}

	public int getSchool_year() {
		return school_year;
	}
	public void setSchool_year(int school_year) {
		this.school_year = school_year;
	}

	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public int getJob_hunting_state() {
		return job_hunting_state;
	}
	public void setJob_hunting_state(int job_hunting_state) {
		this.job_hunting_state = job_hunting_state;
	}

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}