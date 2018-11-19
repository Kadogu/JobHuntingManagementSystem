package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Student;

public class StudentDAO {
	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "student";

	/** 学籍番号を追加するために使用
	 *  @param student_id - 追加する学籍番号
	 *  @param course_id - 追加した学籍番号がどのコースの学生のものか分かるためのもの
	 *  @param school_year - 追加した学籍番号がどの学年の学生のものか分かるためのもの
	 *  @return row - insertの行数
	 */
	public static int addStudent_id(int student_id, String course_id, int school_year){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "insert into " + TABLE + " (student_id, course_id, school_year) value(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_id);
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

	/** 学籍番号確認に使用するリストを取得するためのもの
	 *  @param course_idList - 絞込するためのコースIDのリスト
	 *  @param school_year - 絞込するための学年
	 *  @return student_idList - 取得した学籍番号のリスト
	 */
	public static ArrayList<Integer> getStudent_idList(ArrayList<String> course_idList, int school_year){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Integer> student_idList = new ArrayList<Integer>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select student_id from " + TABLE + " where";
			boolean flg = false;

			if(!course_idList.isEmpty()){	//コースIDのリストが空じゃない場合
				for(int i = 0; i < course_idList.size() - 1; i++){
					sql += " course_id = ? or";
				}
				sql += " course_id = ?";

				if(school_year != 0){	//学年が指定されていた場合
					sql += " and";
				}

				flg = true;
			}

			if(school_year != 0){	//学年が指定されていた場合
				sql += " school_year = ?";
				flg = true;
			}

			if(!flg){	//何も指定されていない場合
				sql += " 0 = 0";
			}

			sql += " order by student_id asc";

			pstmt = con.prepareStatement(sql);

			int idx = 1;

			if(!course_idList.isEmpty()){	//コースIDのリストが空じゃない場合
				for(int i = 0 ; i < course_idList.size(); i++){
					pstmt.setString(idx++, course_idList.get(i));
				}
			}

			if(school_year != 0){	//学年が指定されていた場合
				pstmt.setInt(idx, school_year);
			}

			rs = pstmt.executeQuery();
			while(rs.next()){
				student_idList.add(rs.getInt("student_id"));
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
		return student_idList;
	}

	/** 学生データ確認用のために使用
	 *  @param student_id - 確認したい学籍番号
	 *  @return data - 要素1:学籍番号が不正かどうか<br>
	 *  要素2:学生データがアカウントと連携なっているか確認用
	 */
	public static String[] searchStudent_ID(int student_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] data = new String[2];

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select student_id, user_id from " + TABLE + " where student_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				data[0] = String.valueOf(rs.getInt("student_id"));
				data[1] = rs.getString("user_id");
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
		return data;
	}

	/** 学生データ登録に使用
	 *  @param student - 登録する学生データ
	 *  @return row - updateの行数
	 * */
	public static int addStudent(Student student){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "update " + TABLE + " set name = ?, mail_address = ?, school_year = ?, course_id = ?, user_id = ? where student_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getMail_address());
			pstmt.setInt(3, student.getSchool_year());
			pstmt.setString(4, student.getCourse_id());
			pstmt.setString(5, student.getUser_id());
			pstmt.setInt(6, student.getStudent_id());
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

	/** ログイン成功時にユーザーIDが学生データと紐づいているかを確認するために使用
	 *  @param user_id - 登録されているユーザーID
	 *  @return student_id - 紐づいている学籍番号
	 */
	public static int searchUser_ID(String user_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int student_id = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select student_id from " + TABLE + " where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				student_id = rs.getInt("student_id");
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
		return student_id;
	}

	/** 学生のデータを取得する場合に使用
	 *  @param student_id - 学籍番号
	 *  @return student - 登録されている学生データ
	 */
	public static Student searchStudent(int student_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student student = new Student();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select name, mail_address, school_year, course_id from " + TABLE + " where student_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				student.setStudent_id(student_id);
				student.setName(rs.getString("name"));
				student.setMail_address(rs.getString("mail_address"));
				student.setSchool_year(rs.getInt("school_year"));
				student.setCourse_id(rs.getString("course_id"));
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
		return student;
	}

	/** 学生の名前を取得するために使用
	 *  @param student_id - 名前を取得したい学生の学籍番号
	 *  @return name - 取得した学生氏名
	 */
	public static String getName(int student_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select name from " + TABLE + " where student_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				name = rs.getString("name");
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
		return name;
	}
}