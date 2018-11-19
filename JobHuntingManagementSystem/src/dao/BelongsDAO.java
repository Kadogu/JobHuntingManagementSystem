package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dto.Belongs;

public class BelongsDAO {
	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "belongs";

	/** 所属IDに対応した所属名を表示するためのもの
	 *  戻り値 HashMap<String, String> map //Key:所属ID,Value:所属名
	 */
	public static HashMap<String, String> getBelongsMap(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<String, String> map = new HashMap<String, String>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select belongs_id, belongs_name from " + TABLE;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String key = rs.getString("belongs_id");
				String value = rs.getString("belongs_name");
				map.put(key, value);
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
		return map;
	}

	/** 所属の一覧を取得するためのもの
	 *  戻り値 ArrayList<Belongs> list //取得した所属の一覧のリスト
	 */
	public static ArrayList<Belongs> getBelongsList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Belongs> list = new ArrayList<Belongs>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select belongs_id, belongs_name from " + TABLE + " order by sort asc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String belongs_id = rs.getString("belongs_id");
				String belongs_name = rs.getString("belongs_name");
				Belongs belongs = new Belongs(belongs_id, belongs_name);
				list.add(belongs);
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
}