package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Account;

public class AccountDAO {
//	private static final String HOST = "localhost";
	private static final String HOST = "10.0.3.10";

	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://" + HOST + ":3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "account";

	/** ログイン時にIDとPWが合っているか確認する時に使う
	 *  @param user_id - ユーザーID
	 *  @return pw - 登録されているパスワード
	 */
	public static String login(String user_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		String pw = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select pw from " + TABLE + " where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pw = rs.getString("pw");
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
		return pw;
	}

	/** 新規アカウント作成時にデータを格納するために使う
	 *  @param account - 格納するアカウントデータ
	 *  @return row - insertした結果の行数
	 */
	public static int addUser(Account account){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "insert into " + TABLE + "(user_id, pw) value(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account.getUser_id());
			pstmt.setString(2, account.getPw());
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

	/** PW変更に使用
	 *  @param user_id - PWを変更したいアカウントのID
	 *  @param pw - 格納する変更後のPW(ハッシュ化済)
	 *  @return row - updateした結果の行数
	 */
	public static int changePW(String user_id, String pw){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "update " + TABLE + " set pw = ? where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, user_id);
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

	/** パスワードをハッシュ化するためのもの
	 *  @param pw - ハッシュ化前のパスワード
	 *  @return hashPW - ハッシュ化後のパスワード
	 */
	public static String hashPW(String pw){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String solt = "Z7Iq5ewviyWbs3UDLjKPlN6nckBfgXto";
		String hashPW = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select password(?) as hashPW";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pw + solt);
			rs = pstmt.executeQuery();
			if(rs.next()){
				hashPW = rs.getString("hashPW");
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
				if(con!= null){
					con.close();
				}
			}catch(SQLException e){

			}
		}
		return hashPW;
	}

	/** アカウントデータを削除するためのもの
	 *  @param user_id - 削除するアカウントのユーザーID
	 *  @return row - deleteの行数
	 */
	public static int dropAccount(String user_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "delete from " + TABLE + " where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
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