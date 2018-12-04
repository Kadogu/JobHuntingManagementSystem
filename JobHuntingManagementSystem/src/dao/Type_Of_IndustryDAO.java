package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dto.Type_Of_Industry;

public class Type_Of_IndustryDAO {
	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "type_of_industry";

	/** 業種リストを取得するためのもの
	 *  @return type_of_industryList - 取得した業種リスト
	 */
	public static ArrayList<Type_Of_Industry> getType_Of_IndustryList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Type_Of_Industry> type_of_industryList = new ArrayList<Type_Of_Industry>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select industry_code, industry_name from " + TABLE;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String industry_code = rs.getString("industry_code");
				String industry_name = rs.getString("industry_name");
				type_of_industryList.add(new Type_Of_Industry(industry_code, industry_name));
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
		return type_of_industryList;
	}

	/** 業種マップを取得するためのもの
	 *  @return type_of_industryMap - 取得した業種マップ
	 */
	public static HashMap<String, String> getType_Of_IndustryMap(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<String, String> type_of_industryMap = new HashMap<String, String>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select industry_code, industry_name from " + TABLE;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String industry_code = rs.getString("industry_code");
				String industry_name = rs.getString("industry_name");
				type_of_industryMap.put(industry_code, industry_name);
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
		return type_of_industryMap;
	}

	/** 業種名を取得するためのもの
	 *  @param industry_code - 取得したい業種名の業種ID
	 *  @return industry_name - 取得した業種名
	 */
	public static String getIndustry_name(String industry_code){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String industry_name = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select industry_name from " + TABLE + " where industry_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, industry_code);
			rs = pstmt.executeQuery();
			if(rs.next()){
				industry_name = rs.getString("industry_name");
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
		return industry_name;
	}
}