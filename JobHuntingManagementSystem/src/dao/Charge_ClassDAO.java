package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Charge_ClassDAO {
	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "charge_class";

	/** 担当クラスデータを格納するために使用
	 *  int teacher_id //担当するクラスの教員ID
	 *  String course_id //担当するクラスのコースID
	 *  int school_year //担当するクラスの学年
	 *  戻り値 int row //insertした行数
	 */
	public static int addCharge_Class(int teacher_id, String course_id, int school_year){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "insert into " + TABLE + " (teacher_id, course_id, school_year) value(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, teacher_id);
			pstmt.setString(2, course_id);
			pstmt.setInt(3, school_year);
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

	/** 教師IDを取得するために使用
	 *  @param course_id - 取得したい教師が担当しているクラスのコースID
	 *  @param school_year - 取得したい教師が担当しているクラスの学年
	 *  @return teacher_id - 取得した教師ID
	 */
	public static int searchTeacher_id(String course_id, int school_year){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int teacher_id = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select teacher_id from " + TABLE + " where course_id = ? and school_year = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, course_id);
			pstmt.setInt(2, school_year);
			rs = pstmt.executeQuery();
			if(rs.next()){
				teacher_id = rs.getInt("teacher_id");
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
		return teacher_id;
	}
}