package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import bin.DateConversion;
import dto.Log;

public class LogDAO {
//	private static final String HOST = "localhost";
	private static final String HOST = "10.0.3.10";

	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://" + HOST + ":3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "log";

	/** ログイン時のログを格納するために使用
	 *  @param log - 格納するログデータ
	 *  @return row - insertの行数
	 */
	public static int addLog(Log log){
		Connection con = null;
		PreparedStatement pstmt  = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "insert into " + TABLE + " (log_datetime, login_id, login_pw, login_sorf) value(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setTimestamp(1, DateConversion.timestampConversion(log.getLog_datetime()));
			pstmt.setString(2, log.getLogin_id());
			pstmt.setString(3, log.getLogin_pw());
			pstmt.setBoolean(4, log.isLogin_sorf());
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

	/** ログ取得用
	 *  @return logList - 取得したログリスト
	 */
	public static ArrayList<Log> getLogList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Log> logList = new ArrayList<Log>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select log_id, log_datetime, login_id, login_pw, login_sorf from " + TABLE;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int log_id = rs.getInt("log_id");
				LocalDateTime log_datetime = DateConversion.localDateTimeConversion(rs.getTimestamp("log_datetime"));
				String login_id = rs.getString("login_id");
				String login_pw = rs.getString("login_pw");
				boolean login_sorf = rs.getBoolean("login_sorf");
				logList.add(new Log(log_id, log_datetime, login_id, login_pw, login_sorf));
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
		return logList;
	}
}