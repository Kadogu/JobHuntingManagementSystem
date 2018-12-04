package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Document_ContentsDAO {
	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "document_contents";

	/** 届出書の応募書類の一覧を取得するために使用
	 *  @return documents - 取得した応募書類の一覧
	 */
	public static String[] getDocuments(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] documents = new String[6];

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select document from " + TABLE;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			for(int i = 0; i < documents.length; i++){
				if(rs.next()){
					documents[i] = rs.getString("document");
				}else{
					break;
				}
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
		return documents;
	}
}