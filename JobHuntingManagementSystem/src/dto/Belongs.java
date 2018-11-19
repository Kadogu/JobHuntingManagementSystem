package dto;

import java.io.Serializable;

public class Belongs implements Serializable{
	private String belongs_id;
	private String belongs_name;

	public Belongs(){}

	public Belongs(String belongs_id, String belongs_name) {
		this.belongs_id = belongs_id;
		this.belongs_name = belongs_name;
	}

	public String getBelongs_id() {
		return belongs_id;
	}
	public void setBelongs_id(String belongs_id) {
		this.belongs_id = belongs_id;
	}

	public String getBelongs_name() {
		return belongs_name;
	}
	public void setBelongs_name(String belongs_name) {
		this.belongs_name = belongs_name;
	}
}