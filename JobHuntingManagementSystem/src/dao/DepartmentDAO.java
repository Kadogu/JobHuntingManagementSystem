package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DepartmentDAO {
	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "department";

	/** 学科IDから学籍番号作成用の数字を取得するためのもの
	 *  @param department_id - 取得したい数字の学科ID
	 *  @return number - 取得した数字
	 */
	public static int getNumber(String department_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int number = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select number from " + TABLE + " where department_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, department_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				number = rs.getInt("number");
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
		return number;
	}

	/** 学科IDから学科名を取得するためのもの
	 *  @param department_id - 取得したい学科名の学科ID
	 *  @return department_name - 取得した学科名
	 */
	public static String getDepartment_name(String department_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String department_name = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select department_name from " + TABLE + " where department_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, department_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				department_name = rs.getString("department_name");
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
		return department_name;
	}

	/** 学科マップを取得するためのもの
	 *  @return departmentMap - 取得した学科マップ
	 */
	public static HashMap<String, String> getDepartmentMap(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<String, String> departmentMap = new HashMap<String, String>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select department_id, department_name from " + TABLE;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				departmentMap.put(rs.getString("department_id"), rs.getString("department_name"));
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
		return departmentMap;
	}
}