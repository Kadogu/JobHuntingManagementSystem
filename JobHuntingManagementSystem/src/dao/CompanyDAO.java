package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Company;

public class CompanyDAO {
	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "company";

	/** 会社追加のために使用
	 *  @param company - 追加する会社データ
	 *  @return row - insertの行数
	 */
	public static int addCompany(Company company){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "insert into " + TABLE + " (company_id, company_name, postal_code, address, phone_number) value(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, company.getCompany_id());
			pstmt.setString(2, company.getCompany_name());
			pstmt.setString(3, company.getPostal_code());
			pstmt.setString(4, company.getAddress());
			pstmt.setString(5, company.getPhone_number());
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

	/** 会社データ修正のために使用
	 *  @param company - 編集する会社データ
	 *  @return row - updateの行数
	 */
	public static int updateCompany(Company company){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "update " + TABLE + " set company_name = ?, postal_code = ?, address = ?, phone_number = ? where company_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, company.getCompany_name());
			pstmt.setString(2, company.getPostal_code());
			pstmt.setString(3, company.getAddress());
			pstmt.setString(4, company.getPhone_number());
			pstmt.setString(5, company.getCompany_id());
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

	/** 会社選択・確認のために使用
	 *  @param company_name - 検索する会社名(部分一致)
	 *  @return companies - 会社のリスト
	 */
	public static ArrayList<Company> getCompanies(String company_name){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Company> companies = new ArrayList<Company>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select company_id, company_name, postal_code, address, phone_number from " + TABLE + " where company_name like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + company_name + "%");
			rs = pstmt.executeQuery();
			while(rs.next()){
				Company company = new Company();
				company.setCompany_id(rs.getString("company_id"));
				company.setCompany_name(rs.getString("company_name"));
				company.setPostal_code(rs.getString("postal_code"));
				company.setAddress(rs.getString("address"));
				company.setPhone_number(rs.getString("phone_number"));
				companies.add(company);
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
		return companies;
	}

	/** 会社のデータを取得するために使用
	 *  @param company_id - 取得してくる会社データの会社ID
	 *  @return Company company - 取得してきた会社データ
	 */
	public static Company searchCompany(String company_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Company company = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select company_name, postal_code, address, phone_number from " + TABLE + " where company_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, company_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				String company_name = rs.getString("company_name");
				String postal_code = rs.getString("postal_code");
				String address = rs.getString("address");
				String phone_number = rs.getString("phone_number");

				company = new Company(company_id, company_name, postal_code, address, phone_number);
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
		return company;
	}

	/** 会社名だけを取得したい場合に使用
	 *  @param company_id - 取得したい会社の会社ID
	 *  @return company_name - 取得した会社名
	 */
	public static String getCompany_name(String company_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String company_name = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select company_name from " + TABLE + " where company_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, company_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				company_name = rs.getString("company_name");
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
		return company_name;
	}

	/** 会社IDが存在するかどうかの確認のために使用
	 *  @param company_id - 存在するか確認したい会社ID
	 *  @return dbCompany_id - 存在しない場合{@code null}
	 */
	public static String searchCompany_Id(String company_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbCompany_id = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select company_id from " + TABLE + " where company_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, company_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				dbCompany_id = rs.getString("company_id");
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
		return dbCompany_id;
	}
}