package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Teacher;

public class TeacherDAO {
//	private static final String HOST = "localhost";
	private static final String HOST = "10.0.3.10";

	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://" + HOST + ":3306/gradwork?useSSL=false";
	private static final String USER= "jyobi";
	private static final String PASSWORD= "jyobijyobi";

	private static final String TABLE= "teacher";

	/** 教師データ登録のために使用
	 *  @param teacher - 登録したい教師データ
	 *  @reurn row - insertの行数
	 */
	public static int addTeacher(Teacher teacher){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "insert into " + TABLE + " (name, belongs_id, mail_address, user_id) value(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, teacher.getName());
			pstmt.setString(2, teacher.getBelongs_id());
			pstmt.setString(3, teacher.getMail_address());
			pstmt.setString(4, teacher.getUser_id());
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

	/** ログイン成功時にユーザーIDが教師データと紐づいているかを確認するために使用
	 *  @param user_id - ログイン時のユーザーID
	 *  @return teacher_id - ユーザーIDに紐づいている教師ID
	 */
	public static int searchTeacher_Id(String user_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int teacher_id = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select teacher_id from " + TABLE + " where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
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

	/** 教師データを取得するために使用
	 *  @param teacher_id - 取得したい教師データの教師ID
	 *  @return teacher - 取得した教師データ
	 */
	public static Teacher getTeacher(int teacher_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Teacher teacher = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select name, belongs_id, mail_address, admin_flg from " + TABLE + " where teacher_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, teacher_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				String name = rs.getString("name");
				String belongs_id = rs.getString("belongs_id");
				String mail_address = rs.getString("mail_address");
				boolean admin_flg = rs.getBoolean("admin_flg");
				teacher = new Teacher(teacher_id, name, belongs_id, mail_address, admin_flg, null);
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
		return teacher;
	}

	/** 登録内容変更(教師版)のために使用
	 *  @param teacher - 登録内容変更したい教師データ
	 *  @reurn row - updateの行数
	 */
	public static int updateTeacher(Teacher teacher){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "update " + TABLE + " set name = ?, belongs_id = ?, mail_address = ? where teacher_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, teacher.getName());
			pstmt.setString(2, teacher.getBelongs_id());
			pstmt.setString(3, teacher.getMail_address());
			pstmt.setInt(4, teacher.getTeacher_id());
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

	/** 就職課の教師の教師IDリストを取得するために使用
	 *  @return ed_Teacher_idList - 取得した就職課の教師の教師IDリスト
	 */
	public static ArrayList<Integer> getED_Teacher_idList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Integer> ed_Teacher_idList = new ArrayList<Integer>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select teacher_id from " + TABLE + " where belongs_id = \"e\"";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ed_Teacher_idList.add(rs.getInt("teacher_id"));
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
		return ed_Teacher_idList;
	}

	/** システム管理者権限変更と教師削除(検索値なし)に使用する教師リストを取得するもの
	 *  @param myTeacher_id - ログインしている教師の教師ID
	 *  @return teachers - 取得した教師リスト
	 */
	public static ArrayList<Teacher> getTeachers(int myTeacher_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select teacher_id, name, belongs_id, admin_flg from " + TABLE + " where teacher_id <> ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, myTeacher_id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int teacher_id = rs.getInt("teacher_id");
				String name = rs.getString("name");
				String belongs_id = rs.getString("belongs_id");
				boolean admin_flg = rs.getBoolean("admin_flg");
				teachers.add(new Teacher(teacher_id, name, belongs_id, null, admin_flg, null));
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
		return teachers;
	}

	/** システム管理者権限を変更するためのもの
	 *  @param teacher_id - 権限を変更する教師の教師ID
	 *  @param admin_flg - 変更する権限
	 *  @return row - updateの行数
	 */
	public static int changeAuthority(int teacher_id, boolean admin_flg){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "update " + TABLE + " set admin_flg = ? where teacher_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, admin_flg);
			pstmt.setInt(2, teacher_id);
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

	/** 教師削除(検索値:氏名)のテーブルを表示するのに使用する教師リストを取得するもの
	 *  @param myTeacher_id - ログインしている教師の教師ID
	 *  @param name - 検索値:氏名
	 *  @return teachers - 取得した教師リスト
	 */
	public static ArrayList<Teacher> getTeachers(int myTeacher_id, String name){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select teacher_id, name, belongs_id, admin_flg from " + TABLE + " where teacher_id <> ? and name like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, myTeacher_id);
			pstmt.setString(2, "%" + name + "%");
			rs = pstmt.executeQuery();
			while(rs.next()){
				int teacher_id = rs.getInt("teacher_id");
				name = rs.getString("name");
				String belongs_id = rs.getString("belongs_id");
				boolean admin_flg = rs.getBoolean("admin_flg");
				teachers.add(new Teacher(teacher_id, name, belongs_id, null, admin_flg, null));
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
		return teachers;
	}

	/** 教師のユーザーIDを取得するためのもの
	 *  @param teacher_id - ユーザーIDを取得したい教師の教師ID
	 *  @return user_id - 取得したユーザーID
	 */
	public static String searchUser_ID(int teacher_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String user_id = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select user_id from " + TABLE + " where teacher_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, teacher_id);
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

	/** 教師データを削除するためのもの
	 *  @param teacher_id - 削除する教師データの教師ID
	 *  @return row - deleteの行数
	 */
	public static int dropTeacher(int teacher_id){
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