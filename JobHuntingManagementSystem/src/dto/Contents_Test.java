package dto;

import java.io.Serializable;
import java.time.LocalDate;

public class Contents_Test implements Serializable{
	private int contents_test_id;
	private String pdf_id;
	private int n;
	private LocalDate start_date;
	private int start_hour;
	private int start_minute;
	private int last_hour;
	private int last_minute;
	private String test_category;
	private String writing;
	private int viewer_no;
	private int view_time;
	private String view_content;
	private int groop_no;
	private int discussion_no;

	public Contents_Test(){}
	public Contents_Test(int contents_test_id, String pdf_id, int n, LocalDate start_date, int start_hour,
			int start_minute, int last_hour, int last_minute, String test_category, String writing, int viewer_no,
			int view_time, String view_content, int groop_no, int discussion_no) {
		this.contents_test_id = contents_test_id;
		this.pdf_id = pdf_id;
		this.n = n;
		this.start_date = start_date;
		this.start_hour = start_hour;
		this.start_minute = start_minute;
		this.last_hour = last_hour;
		this.last_minute = last_minute;
		this.test_category = test_category;
		this.writing = writing;
		this.viewer_no = viewer_no;
		this.view_time = view_time;
		this.view_content = view_content;
		this.groop_no = groop_no;
		this.discussion_no = discussion_no;
	}

	public int getContents_test_id() {
		return contents_test_id;
	}
	public void setContents_test_id(int contents_test_id) {
		this.contents_test_id = contents_test_id;
	}

	public String getPdf_id() {
		return pdf_id;
	}
	public void setPdf_id(String pdf_id) {
		this.pdf_id = pdf_id;
	}

	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}

	public LocalDate getStart_date() {
		return start_date;
	}
	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public int getStart_hour() {
		return start_hour;
	}
	public void setStart_hour(int start_hour) {
		this.start_hour = start_hour;
	}

	public int getStart_minute() {
		return start_minute;
	}
	public void setStart_minute(int start_minute) {
		this.start_minute = start_minute;
	}

	public int getLast_hour() {
		return last_hour;
	}
	public void setLast_hour(int last_hour) {
		this.last_hour = last_hour;
	}

	public int getLast_minute() {
		return last_minute;
	}
	public void setLast_minute(int last_minute) {
		this.last_minute = last_minute;
	}

	public String getTest_category() {
		return test_category;
	}
	public void setTest_category(String test_category) {
		this.test_category = test_category;
	}

	public String getWriting() {
		return writing;
	}
	public void setWriting(String writing) {
		this.writing = writing;
	}

	public int getViewer_no() {
		return viewer_no;
	}
	public void setViewer_no(int viewer_no) {
		this.viewer_no = viewer_no;
	}

	public int getView_time() {
		return view_time;
	}
	public void setView_time(int view_time) {
		this.view_time = view_time;
	}

	public String getView_content() {
		return view_content;
	}
	public void setView_content(String view_content) {
		this.view_content = view_content;
	}

	public int getGroop_no() {
		return groop_no;
	}
	public void setGroop_no(int groop_no) {
		this.groop_no = groop_no;
	}

	public int getDiscussion_no() {
		return discussion_no;
	}
	public void setDiscussion_no(int discussion_no) {
		this.discussion_no = discussion_no;
	}
}