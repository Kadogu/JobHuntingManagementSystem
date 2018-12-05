package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import bin.DateConversion;
import dto.Document_Application;

public class Document_ApplicationDAO {
	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "document_application";

	/** 届出書作成するために使用
	 *  @param document_application - 作成した届出書のデータ
	 *  @return row - insertの行数
	 */
	public static int createApplication(Document_Application document_application){
		Connection con = null;
		PreparedStatement pstmt = null;
		int[] documents_flg = document_application.getDocuments_flg();
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "insert into " + TABLE + " (document_application_id, application_date, "
					+ "student_id, company_id, bring_mailing, deadline, resume_flg, graduation_certificate_flg, "
					+ "record_certificate_flg, health_certificate_flg, nomination_form_flg, other_flg, "
					+ "destination) value(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, document_application.getDocument_application_id());
			pstmt.setDate(2, DateConversion.dateConversion(document_application.getApplication_date()));
			pstmt.setInt(3, document_application.getStudent_id());
			pstmt.setString(4, document_application.getCompany_id());
			pstmt.setBoolean(5, document_application.isBring_mailing());
			pstmt.setDate(6, DateConversion.dateConversion(document_application.getDeadline()));
			pstmt.setInt(7, documents_flg[0]);
			pstmt.setInt(8, documents_flg[1]);
			pstmt.setInt(9, documents_flg[2]);
			pstmt.setInt(10, documents_flg[3]);
			pstmt.setInt(11, documents_flg[4]);
			pstmt.setInt(12, documents_flg[5]);
			pstmt.setInt(13, document_application.getDestination());
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

	/** 会社宛に変更するのに使用
	 *  @param document_application_id - 会社宛に変更したい届出書の届出書ID
	 *  @return row - updateの行数
	 */
	public static int updateDestination(String document_application_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "update " + TABLE + " set destination = 0 where document_application_id = ?";
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

	/** 作成済届出書リストを取得するために使用
	 *  @param student_id - 作成した学生の学籍番号
	 *  @return list - 取得した作成済届出書リスト
	 */
	public static ArrayList<Document_Application> getDocument_Applications(int student_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Document_Application> list = new ArrayList<Document_Application>();
		String[] documents_name = {"resume_flg", "graduation_certificate_flg", "record_certificate_flg",
				"health_certificate_flg", "nomination_form_flg", "other_flg"};

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select document_application_id, application_date, "
					+ "company_id, bring_mailing, deadline, ";
			for(String document_name : documents_name){
				sql += document_name + ", ";
			}
			sql += "issue_fee, issue_flg from " + TABLE + " where student_id = ? "
					+ "order by issue_flg asc, deadline asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Document_Application document_application = new Document_Application();
				document_application.setDocument_application_id(rs.getString("document_application_id"));
				document_application.setApplication_date(DateConversion.localDateConversion(rs.getDate("application_date")));
				document_application.setCompany_id(rs.getString("company_id"));
				document_application.setBring_mailing(rs.getBoolean("bring_mailing"));
				document_application.setDeadline(DateConversion.localDateConversion(rs.getDate("deadline")));

				int[] documents_flg = new int[6];
				for(int i = 0; i < documents_flg.length; i++){
					documents_flg[i] = rs.getInt(documents_name[i]);
				}

				document_application.setDocuments_flg(documents_flg);
				document_application.setIssue_fee(rs.getInt("issue_fee"));
				document_application.setIssue_flg(rs.getBoolean("issue_flg"));
				list.add(document_application);
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

	/** 作成した届出書IDが存在するかどうか確認するために使用
	 * 	@param document_application_id - 作成した届出書ID
	 *  @return dbDocument_application_id - 届出書IDが存在しない場合 {@code null}
	 */
	public static String searchDocument_Application_Id(String document_application_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		String dbDocument_application_id = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select document_application_id from " + TABLE + " where document_application_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, document_application_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				dbDocument_application_id = rs.getString("document_application_id");
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
		return dbDocument_application_id;
	}

	/** 確認が必要な届出書数をカウントするためのもの
	 *  @param bring_mailing - 持参か郵送かの振り分けのため
	 * 	@param student_id - 確認したい届出書を作成した生徒の学籍番号
	 *  @return count - 確認が必要な届出書数
	 */
	public static int countDocument_ApplicationList(boolean bring_mailing, int student_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		int count = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select count(document_application_id) as count from " + TABLE + " where bring_mailing = ? and student_id = ? and issue_flg = false";
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, bring_mailing);
			pstmt.setInt(2, student_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt("count");
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
		return count;
	}

	/** 確認が必要な届出書数をカウントするためのもの(就職課用)
	 *  @param bring_mailing - 持参か郵送かの振り分けのため
	 *  @return count - 確認が必要な届出書数
	 */
	public static int countDocument_ApplicationList(boolean bring_mailing){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		int count = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select count(document_application_id) as count from " + TABLE + " where bring_mailing = ? and issue_flg = false";
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, bring_mailing);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt("count");
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
		return count;
	}

	/** 確認が必要な届出書のリストを取得するためのもの
	 *  @param bring_mailing - 持参か郵送かの振り分けのため
	 * 	@param student_id - 確認が必要な届出書を作成した生徒の学籍番号
	 *  @return document_applicationList - 確認が必要な届出書のリスト
	 */
	public static ArrayList<Document_Application> getDocument_ApplicationList(boolean bring_mailing, int student_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		ArrayList<Document_Application> document_applicationList = new ArrayList<Document_Application>();
		String[] documents_name = {"resume_flg", "graduation_certificate_flg", "record_certificate_flg",
				"health_certificate_flg", "nomination_form_flg", "other_flg"};

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select document_application_id, company_id, deadline, ";

			for(int i = 0; i < documents_name.length; i++){
				sql += documents_name[i] + ", ";
			}

			sql += "issue_fee";

			if(!bring_mailing){	//郵送の場合
				sql += ", destination";
			}

			sql += " from " + TABLE + " where bring_mailing = ? and student_id = ? and issue_flg = false";
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, bring_mailing);
			pstmt.setInt(2, student_id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String document_application_id = rs.getString("document_application_id");

				String company_id = rs.getString("company_id");

				LocalDate deadline = DateConversion.localDateConversion(rs.getDate("deadline"));

				int[] documents_flg = new int[documents_name.length];
				for(int i = 0; i < documents_name.length; i++){
					documents_flg[i] = rs.getInt(documents_name[i]);
				}

				int issue_fee = rs.getInt("issue_fee");

				int destination = -1;
				if(!bring_mailing){	//郵送の場合
					destination = rs.getInt("destination");
				}
				document_applicationList.add(new Document_Application(document_application_id, null,
						student_id, company_id, bring_mailing, deadline, documents_flg, issue_fee, false,
						destination));
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
		return document_applicationList;
	}

	/** 確認が必要な届出書のリストを取得するためのもの(就職課用)
	 *  @param bring_mailing - 持参か郵送かの振り分けのため
	 * 	@param student_id - 確認が必要な届出書を作成した生徒の学籍番号
	 *  @return document_applicationList - 確認が必要な届出書のリスト
	 */
	public static ArrayList<Document_Application> getDocument_ApplicationList(boolean bring_mailing){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		ArrayList<Document_Application> document_applicationList = new ArrayList<Document_Application>();
		String[] documents_name = {"resume_flg", "graduation_certificate_flg", "record_certificate_flg",
				"health_certificate_flg", "nomination_form_flg", "other_flg"};

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select document_application_id, student_id, company_id, deadline, ";

			for(int i = 0; i < documents_name.length; i++){
				sql += documents_name[i] + ", ";
			}

			sql += "issue_fee";

			if(!bring_mailing){	//郵送の場合
				sql += ", destination";
			}

			sql += " from " + TABLE + " where bring_mailing = ? and issue_flg = false";
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, bring_mailing);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String document_application_id = rs.getString("document_application_id");

				int student_id = rs.getInt("student_id");

				String company_id = rs.getString("company_id");

				LocalDate deadline = DateConversion.localDateConversion(rs.getDate("deadline"));

				int[] documents_flg = new int[documents_name.length];
				for(int i = 0; i < documents_name.length; i++){
					documents_flg[i] = rs.getInt(documents_name[i]);
				}

				int issue_fee = rs.getInt("issue_fee");

				int destination = -1;
				if(!bring_mailing){	//郵送の場合
					destination = rs.getInt("destination");
				}
				document_applicationList.add(new Document_Application(document_application_id, null,
						student_id, company_id, bring_mailing, deadline, documents_flg, issue_fee, false,
						destination));
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
		return document_applicationList;
	}

	/** 届出書データを取得するためのもの
	 * 	@param document_application_id - 取得したい届出書データの届出書ID
	 *  @return document_application - 取得した届出書データ
	 */
	public static Document_Application getDocument_Application(String document_application_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		Document_Application document_application = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select student_id, company_id, issue_fee from " + TABLE + " where document_application_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, document_application_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				document_application = new Document_Application();

				int student_id = rs.getInt("student_id");
				document_application.setStudent_id(student_id);

				String company_id = rs.getString("company_id");
				document_application.setCompany_id(company_id);

				int issue_fee = rs.getInt("issue_fee");
				document_application.setIssue_fee(issue_fee);
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
		return document_application;
	}

	/** 発行状態を発行済にするために使用
	 *  @param document_application_id - 発行状態を変えたい届出書の届出書ID
	 *  @return row - updateの行数
	 */
	public static int updateIssue_flg(String document_application_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "update " + TABLE + " set issue_flg = true where document_application_id = ?";
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

	/** 生徒の卒業、アカウント削除の処理で届出書データを削除する時に使用する届出書リストを取得するためのもの
	 *  @param student_id - 作成した学生の学籍番号
	 *  @return list - 取得した届出書リスト
	 */
	public static ArrayList<Document_Application> getDocument_ApplicationList(int student_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Document_Application> list = new ArrayList<Document_Application>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select document_application_id, other_flg, destination from " + TABLE + " where student_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Document_Application document_application = new Document_Application();
				document_application.setDocument_application_id(rs.getString("document_application_id"));
				int[] documents_flg = new int[6];
				documents_flg[5] = rs.getInt("other_flg");
				document_application.setDocuments_flg(documents_flg);
				document_application.setDestination(rs.getInt("destination"));
				list.add(document_application);
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

	/** 届出書データを削除するためのもの
	 *  @param student_id - 削除する届出書データを作成した学生の学籍番号
	 *  @return row - deleteの行数
	 */
	public static int dropDocument_Application(int student_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "delete from " + TABLE + " where student_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_id);
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

	/** 作成したばかりの届出書データを削除するためのもの
	 *  @param document_application_id - 削除する届出書の届出書ID
	 *  @return row - deleteの行数
	 */
	public static int dropDocument_Application(String document_application_id){
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