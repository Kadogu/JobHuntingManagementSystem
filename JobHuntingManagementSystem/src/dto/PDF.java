package dto;

import java.io.Serializable;
import java.time.LocalDate;

public class PDF implements Serializable{
	private String pdf_id;
	private String file_name;
	private int student_id;
	private LocalDate filing_date;
	private String sorf;
	private LocalDate sfdate;
	private String company_id;
	private String type_of_industry;
	private int occupations;
	private String application_form;
	private String content_of_test;
	private String advice_to_junior;
	private boolean cc_flg;
	private boolean tc_flg;
	private boolean edc_flg;

	public PDF(){}
	public PDF(String pdf_id, String file_name, int student_id, LocalDate filing_date, String sorf, LocalDate sfdate,
			String company_id, String type_of_industry, int occupations, String application_form,
			String content_of_test, String advice_to_junior, boolean cc_flg, boolean tc_flg, boolean edc_flg) {
		this.pdf_id = pdf_id;
		this.file_name = file_name;
		this.student_id = student_id;
		this.filing_date = filing_date;
		this.sorf = sorf;
		this.sfdate = sfdate;
		this.company_id = company_id;
		this.type_of_industry = type_of_industry;
		this.occupations = occupations;
		this.application_form = application_form;
		this.content_of_test = content_of_test;
		this.advice_to_junior = advice_to_junior;
		this.cc_flg = cc_flg;
		this.tc_flg = tc_flg;
		this.edc_flg = edc_flg;
	}

	public String getPdf_id() {
		return pdf_id;
	}
	public void setPdf_id(String pdf_id) {
		this.pdf_id = pdf_id;
	}

	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public LocalDate getFiling_date() {
		return filing_date;
	}
	public void setFiling_date(LocalDate filing_date) {
		this.filing_date = filing_date;
	}

	public String getSorf() {
		return sorf;
	}
	public void setSorf(String sorf) {
		this.sorf = sorf;
	}

	public LocalDate getSfdate() {
		return sfdate;
	}
	public void setSfdate(LocalDate sfdate) {
		this.sfdate = sfdate;
	}

	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getType_of_industry() {
		return type_of_industry;
	}
	public void setType_of_industry(String type_of_industry) {
		this.type_of_industry = type_of_industry;
	}

	public int getOccupations() {
		return occupations;
	}
	public void setOccupations(int occupations) {
		this.occupations = occupations;
	}

	public String getApplication_form() {
		return application_form;
	}
	public void setApplication_form(String application_form) {
		this.application_form = application_form;
	}

	public String getContent_of_test() {
		return content_of_test;
	}
	public void setContent_of_test(String content_of_test) {
		this.content_of_test = content_of_test;
	}

	public String getAdvice_to_junior() {
		return advice_to_junior;
	}
	public void setAdvice_to_junior(String advice_to_junior) {
		this.advice_to_junior = advice_to_junior;
	}

	public boolean isCc_flg() {
		return cc_flg;
	}
	public void setCc_flg(boolean cc_flg) {
		this.cc_flg = cc_flg;
	}

	public boolean isTc_flg() {
		return tc_flg;
	}
	public void setTc_flg(boolean tc_flg) {
		this.tc_flg = tc_flg;
	}

	public boolean isEdc_flg() {
		return edc_flg;
	}
	public void setEdc_flg(boolean edc_flg) {
		this.edc_flg = edc_flg;
	}
}