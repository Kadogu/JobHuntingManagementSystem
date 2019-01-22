package dto;

import java.io.Serializable;

public class Charge_Class implements Serializable{
	private int teacher_id;
	private String course_id;
	private int school_year;

	public Charge_Class(){}
	public Charge_Class(int teacher_id, String course_id, int school_year) {
		this.teacher_id = teacher_id;
		this.course_id = course_id;
		this.school_year = school_year;
	}

	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public int getSchool_year() {
		return school_year;
	}
	public void setSchool_year(int school_year) {
		this.school_year = school_year;
	}
}