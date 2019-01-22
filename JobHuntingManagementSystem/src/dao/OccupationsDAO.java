package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dto.Occupations;

public class OccupationsDAO {
//	private static final String HOST = "localhost";
	private static final String HOST = "10.0.3.10";

	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://" + HOST + ":3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "occupations";

	/** 職種リストを取得するためのもの
	 *  @return occupationsList - 取得した職種リスト
	 */
	public static ArrayList<Occupations> getOccupationsList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Occupations> occupationsList = new ArrayList<Occupations>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select occupations_code, occupations_name from " + TABLE;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int occupations_code = rs.getInt("occupations_code");
				String occupations_name = rs.getString("occupations_name");
				occupationsList.add(new Occupations(occupations_code, occupations_name));
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
		return occupationsList;
	}

	/** 職種マップを取得するためのもの
	 *  @return occupationsMap - 取得した職種マップ
	 */
	public static HashMap<Integer, String> getOccupationsMap(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<Integer, String> occupationsMap = new HashMap<Integer, String>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select occupations_code, occupations_name from " + TABLE;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int occupations_code = rs.getInt("occupations_code");
				String occupations_name = rs.getString("occupations_name");
				occupationsMap.put(occupations_code, occupations_name);
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
		return occupationsMap;
	}

	/** 職種名を取得するためのもの
	 *  @param occupations_code - 取得したい職種名の職種ID
	 *  @return occupations_name - 取得した職種名
	 */
	public static String getOccupations_name(int occupations_code){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String occupations_name = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select occupations_name from " + TABLE + " where occupations_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, occupations_code);
			rs = pstmt.executeQuery();
			if(rs.next()){
				occupations_name = rs.getString("occupations_name");
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
		return occupations_name;
	}
}