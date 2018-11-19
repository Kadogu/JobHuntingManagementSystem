package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Course;

public class CourseDAO {
	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "course";

	/** コースIDから学籍番号作成用の数字を取得するためのもの
	 *  @param course_id - 取得したい数字のコースID
	 *  @return number - 取得した数字
	 */
	public static int getNumber(String course_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int number = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select number from " + TABLE + " where course_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, course_id);
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

	/** 学科IDからコースIDのリストを取得するために使用
	 *  @param department_id - 取得したいコースIDに関係する学科ID
	 *  @return list - 取得したコースIDのリスト
	 */
	public static ArrayList<String> getCourse_idList(String department_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select course_id from " + TABLE + " where department_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, department_id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(rs.getString("course_id"));
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
		return list;
	}

	/** コース一覧のリストを取得するためのもの
	 *  戻り値 ArrayList<Course> //取得したコース一覧のリスト
	 */
	public static ArrayList<Course> getCourseList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Course> courseList = new ArrayList<Course>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select course_id, course_name, year, department_id, belongs_id from " + TABLE + " order by sort asc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String course_id = rs.getString("course_id");
				String course_name = rs.getString("course_name");
				int year = rs.getInt("year");
				String department_id = rs.getString("department_id");
				String belongs_id = rs.getString("belongs_id");
				courseList.add(new Course(course_id, course_name, year, department_id, belongs_id));
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
		return courseList;
	}

	/** コースIDから学科IDを取得するためのもの
	 *  String course_id //取得したい学科IDのコースID
	 *  戻り値 String department_id //取得した学科ID
	 */
	public static String getDepartment_Id(String course_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String department_id = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select department_id from " + TABLE + " where course_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, course_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				department_id = rs.getString("department_id");
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
		return department_id;
	}
}