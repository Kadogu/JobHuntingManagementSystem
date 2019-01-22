package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Charge_Class;

public class Charge_ClassDAO {
//	private static final String HOST = "localhost";
	private static final String HOST = "10.0.3.10";

	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://" + HOST + ":3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "charge_class";

	/** 担当クラスデータを格納するために使用
	 *  @param charge_class - 格納する担当クラスデータ
	 *  @return row - insertした行数
	 */
	public static int addCharge_Class(Charge_Class charge_class){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "insert into " + TABLE + " (teacher_id, course_id, school_year) value(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, charge_class.getTeacher_id());
			pstmt.setString(2, charge_class.getCourse_id());
			pstmt.setInt(3, charge_class.getSchool_year());
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

	/** ある教師が担当しているクラスのリストを取得するために使用
	 *  @param teacher_id - 該当教師の教師ID
	 *  @return charge_classList - 取得した担当クラスのリスト
	 */
	public static ArrayList<Charge_Class> getCharge_ClassList(int teacher_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Charge_Class> charge_classList = new ArrayList<Charge_Class>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select course_id, school_year from " + TABLE + " where teacher_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, teacher_id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String course_id = rs.getString("course_id");
				int school_year = rs.getInt("school_year");
				charge_classList.add(new Charge_Class(teacher_id, course_id, school_year));
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
		return charge_classList;
	}

	/** 担当クラスを削除するためのもの
	 *  @param teacher_id - 削除する担当クラスに関連する教師の教師ID
	 *  @return row - deleteの行数
	 */
	public static int dropCharge_Class(int teacher_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "delete from " + TABLE + " where teacher_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, teacher_id);
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
}