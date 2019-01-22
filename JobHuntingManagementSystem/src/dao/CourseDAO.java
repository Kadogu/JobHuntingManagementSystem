package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dto.Course;

public class CourseDAO {
//	private static final String HOST = "localhost";
	private static final String HOST = "10.0.3.10";

	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://" + HOST + ":3306/gradwork?useSSL=false";
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

	/** コース一覧のリストを取得するためのもの
	 *  @return courseList - 取得したコース一覧のリスト
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

	/** コースIDから該当コースの卒業年次を取得するためのもの
	 *  @param course_id - 該当するコースのコースID
	 *  @return year //取得した該当コースの卒業年次
	 */
	public static int getYear(String course_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int year = 2;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select year from " + TABLE + " where course_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, course_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				year = rs.getInt("year");
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
		return year;
	}

	/** コースIDから学科IDを取得するためのもの
	 *  @param course_id - 取得したい学科IDのコースID
	 *  @return department_id //取得した学科ID
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

	/** コースIDからコース名を取得するためのもの
	 *  @param course_id - 取得したいコース名のコースID
	 *  @return course_name - 取得したコース名
	 */
	public static String getCourse_name(String course_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String course_name = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select course_name from " + TABLE + " where course_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, course_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				course_name = rs.getString("course_name");
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
		return course_name;
	}

	/** コースマップを取得するためのもの
	 *  @return courseMap - 取得したコースマップ
	 */
	public static HashMap<String, String> getCourseMap(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<String, String> courseMap = new HashMap<String, String>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select course_id, course_name from " + TABLE;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				courseMap.put(rs.getString("course_id"), rs.getString("course_name"));
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
		return courseMap;
	}
}