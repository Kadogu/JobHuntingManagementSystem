package dto;

import java.io.Serializable;

public class Type_Of_Industry implements Serializable{
	private String industry_code;
	private String industry_name;

	public Type_Of_Industry(){}
	public Type_Of_Industry(String industry_code, String industry_name) {
		this.industry_code = industry_code;
		this.industry_name = industry_name;
	}

	public String getIndustry_code() {
		return industry_code;
	}
	public void setIndustry_code(String industry_code) {
		this.industry_code = industry_code;
	}

	public String getIndustry_name() {
		return industry_name;
	}
	public void setIndustry_name(String industry_name) {
		this.industry_name = industry_name;
	}
}