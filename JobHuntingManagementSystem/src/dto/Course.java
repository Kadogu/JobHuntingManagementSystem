package dto;

import java.io.Serializable;

public class Course implements Serializable{
	private String course_id;
	private String course_name;
	private int year;
	private String department_id;
	private String belongs_id;

	public Course(){}
	public Course(String course_id, String course_name, int year, String department_id, String belongs_id) {
		this.course_id = course_id;
		this.course_name = course_name;
		this.year = year;
		this.department_id = department_id;
		this.belongs_id = belongs_id;
	}

	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getBelongs_id() {
		return belongs_id;
	}
	public void setBelongs_id(String belongs_id) {
		this.belongs_id = belongs_id;
	}
}