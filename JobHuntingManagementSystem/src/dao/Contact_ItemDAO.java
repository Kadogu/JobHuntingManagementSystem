package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Contact_ItemDAO {
	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "contact_item";

	/** お問い合わせ項目名を取得するために使用
	 *  int contact_item_id //取得したい項目名のお問い合わせ項目ID
	 *  戻り値 String item_name //取得した項目名
	 */
	public static String getItem_name(int contact_item_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String item_name = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select item_name from " + TABLE + " where contact_item_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, contact_item_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				item_name = rs.getString("item_name");
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
		return item_name;
	}
}