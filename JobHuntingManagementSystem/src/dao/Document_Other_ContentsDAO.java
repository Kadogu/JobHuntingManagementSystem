package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Document_Other_ContentsDAO {
//	private static final String HOST = "localhost";
	private static final String HOST = "10.0.3.10";

	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://" + HOST + ":3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "document_other_contents";

	/** その他の内容を格納するために使用
	 *  @param document_application_id - 連携する届出書の届出書ID
	 *  @param contents - 格納するその他の内容
	 *  @return row - insertの行数
	 */
	public static int addDocument_Other_Contents(String document_application_id, String contents){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "insert into " + TABLE + " (document_application_id, contents) value(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, document_application_id);
			pstmt.setString(2, contents);
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

	/** その他の内容を取得するために使用
	 *  @param document_application_id - 取得したいその他の内容の届出書ID
	 *  @return contents - 取得したその他の内容
	 */
	public static String getContents(String document_application_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String contents = "";

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select contents from " + TABLE + " where document_application_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, document_application_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				contents = rs.getString("contents");
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
		return contents;
	}

	/** 届出書その他内容データを削除するためのもの
	 *  @param document_application_id - 削除する届出書その他内容データに関連する届出書データの届出書ID
	 *  @return row - deleteの行数
	 */
	public static int dropDocument_Other_Contents(String document_application_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "delete from " + TABLE + " where document_application_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, document_application_id);
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