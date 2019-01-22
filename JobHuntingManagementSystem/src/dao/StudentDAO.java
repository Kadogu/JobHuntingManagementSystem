package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Student;

public class StudentDAO {
//	private static final String HOST = "localhost";
	private static final String HOST = "10.0.3.10";

	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://" + HOST + ":3306/gradwork?useSSL=false";
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
	 *  @param course_id - 絞込するためのコースID
	 *  @param school_year - 絞込するための学年
	 *  @return student_idList - 取得した学籍番号のリスト
	 */
	public static ArrayList<Integer> getStudent_idList(String course_id, int school_year){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Integer> student_idList = new ArrayList<Integer>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select student_id from " + TABLE + " where ";

			if(course_id != null && course_id.matches("^[a-z]{1,5}$")){	//コースが指定されていた場合
				sql += "course_id = ? and ";
			}

			if(school_year != 0){	//学年が指定されていた場合
				sql += "school_year = ? and ";
			}
			sql += "graduation_flg = false order by student_id asc";
			pstmt = con.prepareStatement(sql);

			int idx = 1;
			if(course_id != null && course_id.matches("^[a-z]{1,5}$")){	//コースが指定されていた場合
				pstmt.setString(idx, course_id);
				idx++;
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
			String sql = "select student_id, user_id from " + TABLE + " where student_id = ? and graduation_flg = false";
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

	/** アカウント確認に使用
	 *  @param name - 入力された氏名
	 *  @param mail_address - 入力されたメールアドレス
	 *  @return user_id - 氏名とメールアドレスに紐づいているユーザーID
	 */
	public static String searchUser_ID(String name, String mail_address){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String user_id = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select user_id from " + TABLE + " where name = ? and mail_address = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, mail_address);
			rs = pstmt.executeQuery();
			if(rs.next()){
				user_id = rs.getString("user_id");
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
		return user_id;
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

	/** 登録内容変更(生徒版)に使用
	 *  @param student - 登録内容を変更する学生データ
	 *  @return row - updateの行数
	 * */
	public static int updateStudent(Student student){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "update " + TABLE + " set name = ?, mail_address = ?, school_year = ?, course_id = ? where student_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getMail_address());
			pstmt.setInt(3, student.getSchool_year());
			pstmt.setString(4, student.getCourse_id());
			pstmt.setInt(5, student.getStudent_id());
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

	/** 進級処理のために使用する生徒リストを取得するためのもの
	 *  @param course_id - 進級処理をする生徒が在籍するコースのコースID
	 *  @param school_year - 進級処理をする生徒の学年
	 *  @return studentList - 取得した生徒リスト
	 */
	public static ArrayList<Integer> getStudentList(String course_id, int school_year){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Integer> studentList = new ArrayList<Integer>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select student_id from " + TABLE + " where course_id = ? and school_year = ? and graduation_flg = false";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, course_id);
			pstmt.setInt(2, school_year);
			rs = pstmt.executeQuery();
			while(rs.next()){
				studentList.add(rs.getInt("student_id"));
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
		return studentList;
	}

	/** 生徒の自動進級処理に使用
	 *  @param student_id - 進級する生徒の学籍番号
	 *  @param nextGrade - 進級後の学年
	 *  @return row - updateの行数
	 * */
	public static int pass(int student_id, int nextGrade){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "update " + TABLE + " set school_year = ? where student_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, nextGrade);
			pstmt.setInt(2, student_id);
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

	/** 生徒の卒業処理に使用
	 *  @param student_id - 卒業する生徒の学籍番号
	 *  @return row - updateの行数
	 * */
	public static int graduateSchool(int student_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "update " + TABLE + " set mail_address = null, graduation_flg = true, user_id = null where student_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_id);
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

	/** 生徒のユーザーIDを取得するためのもの
	 *  @param student_id - ユーザーIDを取得したい生徒の学籍番号
	 *  @return user_id - 取得したユーザーID
	 */
	public static String searchUser_ID(int student_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String user_id = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select user_id from " + TABLE + " where student_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				user_id = rs.getString("user_id");
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
		return user_id;
	}

	/** 生徒削除テーブルを表示するのに使用する生徒リストを取得するためのもの(検索値なし)
	 *  @return studentList - 取得した生徒リスト
	 */
	public static ArrayList<Student> getStudentList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Student> studentList = new ArrayList<Student>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select student_id, name, school_year, course_id from " + TABLE + " where graduation_flg = false";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int student_id = rs.getInt("student_id");
				String name = rs.getString("name");
				int school_year = rs.getInt("school_year");
				String course_id = rs.getString("course_id");

				Student student = new Student(student_id, name, null, school_year, course_id, null);

				studentList.add(student);
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
		return studentList;
	}

	/** 生徒削除テーブルを表示するのに使用する生徒リストを取得するためのもの(検索値:学籍番号)
	 *  @param student_id - 検索値:学籍番号
	 *  @return studentList - 取得した生徒リスト
	 */
	public static ArrayList<Student> getStudentList(int student_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Student> studentList = new ArrayList<Student>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);

			int next = student_id + 1;
			if(student_id / 1000000 == 0){	//検索値の学籍番号が7桁未満の場合
				int dial = 10;
				for(int num = 100000; num > 10; num /= 10){
					if(student_id / num == 0){	//検索値の学籍番号がその時の桁未満の場合
						dial *= 10;
					}
				}
				student_id *= dial;
				next *= dial;
			}

			String sql = "select student_id, name, school_year, course_id from " + TABLE + " where student_id >= ? and student_id < ? and graduation_flg = false";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_id);
			pstmt.setInt(2, next);
			rs = pstmt.executeQuery();
			while(rs.next()){
				student_id = rs.getInt("student_id");
				String name = rs.getString("name");
				int school_year = rs.getInt("school_year");
				String course_id = rs.getString("course_id");

				Student student = new Student(student_id, name, null, school_year, course_id, null);

				studentList.add(student);
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
		return studentList;
	}

	/** 生徒削除テーブルを表示するのに使用する生徒リストを取得するためのもの(検索値:氏名)
	 *  @param name - 検索値:氏名
	 *  @return studentList - 取得した生徒リスト
	 */
	public static ArrayList<Student> getStudentList(String name){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Student> studentList = new ArrayList<Student>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select student_id, name, school_year, course_id from " + TABLE + " where name like ? and graduation_flg = false";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();
			while(rs.next()){
				int student_id = rs.getInt("student_id");
				name = rs.getString("name");
				int school_year = rs.getInt("school_year");
				String course_id = rs.getString("course_id");

				Student student = new Student(student_id, name, null, school_year, course_id, null);

				studentList.add(student);
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
		return studentList;
	}

	/** 学生データを削除するためのもの
	 *  @param student_id - 削除する学生の学籍番号
	 *  @return row - deleteの行数
	 */
	public static int dropStudent(int student_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "delete from " + TABLE + " where student_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_id);
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