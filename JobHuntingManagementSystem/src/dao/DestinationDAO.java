package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Destination;

public class DestinationDAO {
//	private static final String HOST = "localhost";
	private static final String HOST = "10.0.3.10";

	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://" + HOST + ":3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "destination";

	/** 送付先のデータを格納するために使用
	 *  @param destination - 格納する送付先のデータ
	 *  @return row - insertの行数
	 */
	public static int addDestination(Destination destination){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "insert into " + TABLE + "(document_application_id, postal_code, address, individual) value(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, destination.getDocument_application_id());
			pstmt.setString(2, destination.getPostal_code());
			pstmt.setString(3, destination.getAddress());
			pstmt.setString(4, destination.getIndividual());
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

	/** 送付先データを取得するために使用
	 *  @param document_application_id - 送付先データと関連する届出書の届出書ID
	 *  @return destination - 取得した送付先データ
	 */
	public static Destination getDestination(String document_application_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Destination destination = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select postal_code, address, individual from " + TABLE + " where document_application_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, document_application_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				String postal_code = rs.getString("postal_code");
				String address = rs.getString("address");
				String individual = rs.getString("individual");
				destination = new Destination(document_application_id, postal_code, address, individual);
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
		return destination;
	}

	/** 送付先データを削除するためのもの
	 *  @param document_application_id - 削除する送付先データに関連する届出書データの届出書ID
	 *  @return row - deleteの行数
	 */
	public static int dropDestination(String document_application_id){
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