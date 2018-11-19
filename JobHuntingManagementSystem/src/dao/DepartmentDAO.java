package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Department;

public class DepartmentDAO {
	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "department";

	/** 学科IDから学籍番号作成用の数字を取得するためのもの
	 *  String department_id //取得したい数字の学科ID
	 *  戻り値 int number //取得した数字
	 */
	public static int getNumber(String department_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int number = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select number from " + TABLE + " where department_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, department_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				number = rs.getInt("number");
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
		return number;
	}

	/** 学科一覧のリストを取得するためのもの
	 *  戻り値 ArrayList<Department> departmentList //取得した学科一覧のリスト
	 */
	public static ArrayList<Department> getDepartmentList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Department> departmentList = new ArrayList<Department>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select department_id, department_name from " + TABLE + " order by sort asc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String department_id = rs.getString("department_id");
				String department_name = rs.getString("department_name");
				departmentList.add(new Department(department_id, department_name));
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
		return departmentList;
	}
}