package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Teacher;

public class TeacherDAO {
	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL= "jdbc:mysql://localhost:3306/gradwork?useSSL=false";
	private static final String USER= "jyobi";
	private static final String PASSWORD= "jyobijyobi";

	private static final String TABLE= "teacher";

	/** 教師データ登録のために使用
	 *  Teacher teacher //登録したい教師データ
	 *  teacherの中身
	 *  teacher.getName() String //教師氏名
	 *  teacher.getBelongs_id() String //所属ID
	 *  teacher.getMail_address() String //教師のメールアドレス
	 *  teacher.getImage_filename() String //ハンコの画像のファイル名
	 *  teacher.getUser_id() String //アカウントとの連携のため
	 *  戻り値 int row //insertの行数
	 */
	public static int addTeacher(Teacher teacher){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "insert into " + TABLE + " (name, belongs_id, mail_address, image_filename, user_id) value(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, teacher.getName());
			pstmt.setString(2, teacher.getBelongs_id());
			pstmt.setString(3, teacher.getMail_address());
			pstmt.setString(4, teacher.getImage_filename());
			pstmt.setString(5, teacher.getUser_id());
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
	 *  String user_id //ログイン時のユーザーID
	 *  戻り値 int teacher_id //ユーザーIDに紐づいている教師ID
	 */
	public static int searchTeacher_id(String user_id){
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

	/** 教師データを取得するために使用
	 *  int teacher_id //取得したい教師データの教師ID
	 *  戻り値 Teacher teacher //取得した教師データ
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
				teacher = new Teacher(teacher_id, name, belongs_id, mail_address, admin_flg, null, null);
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

	/** システム管理者権限変更に使用するもの
	 *  int myTeacher_id //自分の教師ID
	 *  戻り値 ArrayList<Teacher> list //取得した教師の一覧
	 */
	public static ArrayList<Teacher> getTeachers(int myTeacher_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Teacher> list = new ArrayList<Teacher>();

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
				list.add(new Teacher(teacher_id, name, belongs_id, null, admin_flg, null, null));
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

	/** 管理者権限を変更するもの
	 *  int teacher_id //権限を変更する教師の教師ID
	 *  boolean admin_flg //変更する権限
	 *  戻り値 int row //updateの行数
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

	/** 教師データを削除するためのもの
	 *  int teacher_id //削除する教師データの教師ID
	 *  戻り値 int row //deleteの行数
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