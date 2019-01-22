package dto;

import java.io.Serializable;

public class Occupations implements Serializable{
	private int occupations_code;
	private String occupations_name;

	public Occupations(){}
	public Occupations(int occupations_code, String occupations_name) {
		this.occupations_code = occupations_code;
		this.occupations_name = occupations_name;
	}

	public int getOccupations_code() {
		return occupations_code;
	}
	public void setOccupations_code(int occupations_code) {
		this.occupations_code = occupations_code;
	}

	public String getOccupations_name() {
		return occupations_name;
	}
	public void setOccupations_name(String occupations_name) {
		this.occupations_name = occupations_name;
	}
}