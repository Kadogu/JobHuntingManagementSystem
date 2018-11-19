package dto;

import java.io.Serializable;

public class Company implements Serializable{
	private String company_id;
	private String company_name;
	private String postal_code;
	private String address;
	private String phone_number;

	public Company(){}
	public Company(String company_id, String company_name, String postal_code, String address, String phone_number) {
		this.company_id = company_id;
		this.company_name = company_name;
		this.postal_code = postal_code;
		this.address = address;
		this.phone_number = phone_number;
	}

	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
}