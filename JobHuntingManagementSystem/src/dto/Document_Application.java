package dto;

import java.io.Serializable;
import java.time.LocalDate;

public class Document_Application implements Serializable{
	private String document_application_id;
	private LocalDate application_date;
	private int student_id;
	private String company_id;
	private boolean bring_mailing;
	private LocalDate deadline;
	private int[] documents_flg;
	private int issue_fee;
	private boolean issue_flg;
	private int destination;

	public Document_Application(){}
	public Document_Application(String document_application_id, LocalDate application_date, int student_id,
			String company_id, boolean bring_mailing, LocalDate deadline, int[] documents_flg, int issue_fee,
			boolean issue_flg, int destination) {
		this.document_application_id = document_application_id;
		this.application_date = application_date;
		this.student_id = student_id;
		this.company_id = company_id;
		this.bring_mailing = bring_mailing;
		this.deadline = deadline;
		this.documents_flg = documents_flg;
		this.issue_fee = issue_fee;
		this.issue_flg = issue_flg;
		this.destination = destination;
	}

	public String getDocument_application_id() {
		return document_application_id;
	}
	public void setDocument_application_id(String document_application_id) {
		this.document_application_id = document_application_id;
	}

	public LocalDate getApplication_date() {
		return application_date;
	}
	public void setApplication_date(LocalDate application_date) {
		this.application_date = application_date;
	}

	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public boolean isBring_mailing() {
		return bring_mailing;
	}
	public void setBring_mailing(boolean bring_mailing) {
		this.bring_mailing = bring_mailing;
	}

	public LocalDate getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public int[] getDocuments_flg() {
		return documents_flg;
	}
	public void setDocuments_flg(int[] documents_flg) {
		this.documents_flg = documents_flg;
	}

	public int getIssue_fee() {
		return issue_fee;
	}
	public void setIssue_fee(int issue_fee) {
		this.issue_fee = issue_fee;
	}

	public boolean isIssue_flg() {
		return issue_flg;
	}
	public void setIssue_flg(boolean issue_flg) {
		this.issue_flg = issue_flg;
	}

	public int getDestination() {
		return destination;
	}
	public void setDestination(int destination) {
		this.destination = destination;
	}
}