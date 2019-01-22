package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import bin.DateConversion;
import dto.Contents_Test;

public class Contents_TestDAO {
//	private static final String HOST = "localhost";
	private static final String HOST = "10.0.3.10";

	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://" + HOST + ":3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "contents_test";

	/** 新規作成時に試験の内容データを格納するためのもの
	 *  @param contents_test - 格納する試験の内容データ
	 *  @return row - insertした結果の行数
	 */
	public static int addContents_Test(Contents_Test contents_test){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "insert into " + TABLE + "(pdf_id, n, start_date, start_hour, "
					+ "start_minute, last_hour, last_minute, test_category, writing, viewer_no, "
					+ "view_time, view_content, groop_no, discussion_no) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, contents_test.getPdf_id());
			pstmt.setInt(2, contents_test.getN());
			pstmt.setDate(3, DateConversion.dateConversion(contents_test.getStart_date()));
			pstmt.setInt(4, contents_test.getStart_hour());
			pstmt.setInt(5, contents_test.getStart_minute());
			pstmt.setInt(6, contents_test.getLast_hour());
			pstmt.setInt(7, contents_test.getLast_minute());
			pstmt.setString(8, contents_test.getTest_category());
			pstmt.setString(9, contents_test.getWriting());
			pstmt.setInt(10, contents_test.getViewer_no());
			pstmt.setInt(11, contents_test.getView_time());
			pstmt.setString(12, contents_test.getView_content());
			pstmt.setInt(13, contents_test.getGroop_no());
			pstmt.setInt(14, contents_test.getDiscussion_no());
			row = pstmt.executeUpdate();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException e){

			}

			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){

			}
		}
		return row;
	}

	/** 試験の内容IDを取得するためのもの
	 *  @param pdf_id - 関連する報告書の報告書ID
	 *  @param n - 何次試験か分かるためのもの
	 *  @return contents_test_id - 取得した試験の内容ID
	 */
	public static int getContents_Test_Id(String pdf_id, int n){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		int contents_test_id = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select contents_test_id from " + TABLE + " where pdf_id = ? and n = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdf_id);
			pstmt.setInt(2, n);
			rs = pstmt.executeQuery();
			if(rs.next()){
				contents_test_id = rs.getInt("contents_test_id");
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
			}catch(SQLException e){

			}

			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException e){

			}

			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){

			}
		}
		return contents_test_id;
	}

	/** 続きから作成時に試験の内容データを格納するためのもの
	 *  @param contents_test - 格納する試験の内容データ
	 *  @return row - updateした結果の行数
	 */
	public static int updateContents_Test(Contents_Test contents_test){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "update " + TABLE + " set start_date = ?, start_hour = ?, start_minute = ?, last_hour = ?, "
					+ "last_minute = ?, test_category = ?, writing = ?, viewer_no = ?, view_time = ?, view_content = ?, "
					+ "groop_no = ?, discussion_no = ? where contents_test_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, DateConversion.dateConversion(contents_test.getStart_date()));
			pstmt.setInt(2, contents_test.getStart_hour());
			pstmt.setInt(3, contents_test.getStart_minute());
			pstmt.setInt(4, contents_test.getLast_hour());
			pstmt.setInt(5, contents_test.getLast_minute());
			pstmt.setString(6, contents_test.getTest_category());
			pstmt.setString(7, contents_test.getWriting());
			pstmt.setInt(8, contents_test.getViewer_no());
			pstmt.setInt(9, contents_test.getView_time());
			pstmt.setString(10, contents_test.getView_content());
			pstmt.setInt(11, contents_test.getGroop_no());
			pstmt.setInt(12, contents_test.getDiscussion_no());
			pstmt.setInt(13, contents_test.getContents_test_id());
			row = pstmt.executeUpdate();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException e){

			}

			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){

			}
		}
		return row;
	}

	/** 試験の内容データのリストを取得するために使用
	 *  @param pdf_id - 取得したい試験の内容データリストと紐づいている報告書ID
	 *  @return contents_testList - 取得した試験の内容データのリスト
	 */
	public static ArrayList<Contents_Test> getContents_TestList(String pdf_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		ArrayList<Contents_Test> contents_testList = new ArrayList<Contents_Test>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select n, start_date, start_hour, start_minute, last_hour, last_minute, "
					+ "test_category, writing, viewer_no, view_time, view_content, groop_no, "
					+ "discussion_no from " + TABLE + " where pdf_id = ? order by n asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdf_id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int n = rs.getInt("n");
				LocalDate start_date = DateConversion.localDateConversion(rs.getDate("start_date"));
				int start_hour = rs.getInt("start_hour");
				int start_minute = rs.getInt("start_minute");
				int last_hour = rs.getInt("last_hour");
				int last_minute = rs.getInt("last_minute");
				String test_category = rs.getString("test_category");
				String writing = rs.getString("writing");
				int viewer_no = rs.getInt("viewer_no");
				int view_time = rs.getInt("view_time");
				String view_content = rs.getString("view_content");
				int groop_no = rs.getInt("groop_no");
				int discussion_no = rs.getInt("discussion_no");
				contents_testList.add(new Contents_Test(0, pdf_id, n, start_date, start_hour,
						start_minute, last_hour, last_minute, test_category, writing, viewer_no,
						view_time, view_content, groop_no, discussion_no));
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
			}catch(SQLException e){

			}

			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException e){

			}

			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){

			}
		}
		return contents_testList;
	}
}