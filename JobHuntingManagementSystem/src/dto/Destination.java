package dto;

import java.io.Serializable;

public class Destination implements Serializable{
	private String document_application_id;
	private String postal_code;
	private String address;
	private String individual;

	public Destination(){}
	public Destination(String document_application_id, String postal_code, String address, String individual) {
		this.document_application_id = document_application_id;
		this.postal_code = postal_code;
		this.address = address;
		this.individual = individual;
	}

	public String getDocument_application_id() {
		return document_application_id;
	}
	public void setDocument_application_id(String document_application_id) {
		this.document_application_id = document_application_id;
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

	public String getIndividual() {
		return individual;
	}
	public void setIndividual(String individual) {
		this.individual = individual;
	}
}