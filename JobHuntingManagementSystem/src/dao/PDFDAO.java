package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import bin.DateConversion;
import dto.PDF;

public class PDFDAO {
	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/gradwork?useSSL=false";
	private static final String USER = "jyobi";
	private static final String PASSWORD = "jyobijyobi";

	private static final String TABLE = "pdf";

	/** 新規報告書作成時に報告書1/3のデータを格納するために使う
	 *  @param pdf - 格納する報告書データ
	 *  @return row - insertした結果の行数
	 */
	public static int addReport1(PDF pdf){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "insert into " + TABLE + "(pdf_id, student_id, sorf, sfdate, company_id, type_of_industry, occupations, application_form) value(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdf.getPdf_id());
			pstmt.setInt(2, pdf.getStudent_id());
			pstmt.setString(3, pdf.getSorf());
			pstmt.setDate(4, DateConversion.dateConversion(pdf.getSfdate()));
			pstmt.setString(5, pdf.getCompany_id());
			pstmt.setString(6, pdf.getType_of_industry());
			pstmt.setInt(7, pdf.getOccupations());
			pstmt.setString(8, pdf.getApplication_form());
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

	/** 続きから作成時に報告書1/3のデータを格納するために使う
	 *  @param pdf - 格納する報告書データ
	 *  @return row - updateした結果の行数
	 */
	public static int updateReport1(PDF pdf){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "update " + TABLE + " set sorf = ?, sfdate = ?, type_of_industry = ?, occupations = ?, application_form = ? where pdf_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdf.getSorf());
			pstmt.setDate(2, DateConversion.dateConversion(pdf.getSfdate()));
			pstmt.setString(3, pdf.getType_of_industry());
			pstmt.setInt(4, pdf.getOccupations());
			pstmt.setString(5, pdf.getApplication_form());
			pstmt.setString(6, pdf.getPdf_id());
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

	/** 続きから作成する報告書数をカウントするためのもの
	 *  @param student_id - ログインしている学生の学籍番号
	 *  @return count - 続きから作成する報告書数
	 */
	public static int countReportList(int student_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		int count = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select count(pdf_id) as count from " + TABLE + " where student_id = ? and cc_flg = false";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_id);
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

	/** 続きから作成する報告書のリストを取得するためのもの
	 *  @param student_id - ログインしている学生の学籍番号
	 *  @return reportList - 続きから作成する報告書のリスト
	 */
	public static ArrayList<PDF> getReportList(int student_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		ArrayList<PDF> reportList = new ArrayList<PDF>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select pdf_id, company_id from " + TABLE + " where student_id = ? and cc_flg = false";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				PDF pdf = new PDF();
				pdf.setPdf_id(rs.getString("pdf_id"));
				pdf.setCompany_id(rs.getString("company_id"));
				reportList.add(pdf);
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
		return reportList;
	}

	/**報告書1/3に表示する報告書データを取得するためのもの
	 *  @param pdf_id - 取得したい報告書データの報告書ID
	 *  @return report - 取得した報告書のデータ
	 */
	public static PDF getReport1(String pdf_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		PDF report = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select sorf, sfdate, type_of_industry, occupations, application_form from " + TABLE + " where pdf_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdf_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				report = new PDF();

				String sorf = rs.getString("sorf");
				report.setSorf(sorf);

				LocalDate sfdate = DateConversion.localDateConversion(rs.getDate("sfdate"));
				report.setSfdate(sfdate);

				String type_of_industry = rs.getString("type_of_industry");
				report.setType_of_industry(type_of_industry);

				int occupations = rs.getInt("occupations");
				report.setOccupations(occupations);

				String application_form = rs.getString("application_form");
				report.setApplication_form(application_form);
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
		return report;
	}

	/** 作成した報告書IDが存在するかどうか確認するために使用
	 * 	@param pdf_id - 作成した報告書ID
	 *  @return dbPdf_id - 報告書IDが存在しない場合 {@code null}
	 */
	public static String searchPDF_Id(String pdf_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		String dbPdf_id = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select pdf_id from " + TABLE + " where pdf_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdf_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				dbPdf_id = rs.getString("pdf_id");
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
		return dbPdf_id;
	}

	/** 報告書作成時に報告書3/3のデータを格納するために使う
	 *  @param pdf - 格納する報告書データ
	 *  @return row - updateした結果の行数
	 */
	public static int addReport3(PDF pdf){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "update " + TABLE + " set content_of_test = ?, advice_to_junior = ? where pdf_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdf.getContent_of_test());
			pstmt.setString(2, pdf.getAdvice_to_junior());
			pstmt.setString(3, pdf.getPdf_id());
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

	/** 報告書3/3に表示する報告書データを取得するためのもの
	 *  @param pdf_id - 取得したい報告書データの報告書ID
	 *  @return report - 取得した報告書のデータ
	 */
	public static PDF getReport3(String pdf_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		PDF report = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select content_of_test, advice_to_junior from " + TABLE + " where pdf_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdf_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				report = new PDF();

				String content_of_test = rs.getString("content_of_test");
				report.setContent_of_test(content_of_test);

				String advice_to_junior = rs.getString("advice_to_junior");
				report.setAdvice_to_junior(advice_to_junior);
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
		return report;
	}

	/** 報告書作成確認と報告書作成完了時に使用するためのもの
	 *  @param pdf - 作成確認もしくは完了する報告書のデータ
	 *  @return row - updateした結果の行数
	 */
	public static int createReport(PDF pdf, boolean cc_flg){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "update " + TABLE + " set file_name = ?, filing_date = ?, cc_flg = ? where pdf_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdf.getFile_name());
			pstmt.setDate(2, DateConversion.dateConversion(pdf.getFiling_date()));
			pstmt.setBoolean(3, cc_flg);
			pstmt.setString(4, pdf.getPdf_id());
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

	/** 担任の先生の確認が必要な報告書数をカウントするためのもの
	 *  @param student_id - 先生が担当しているクラスに在籍している学生の学籍番号
	 *  @return count - 確認が必要な作成済報告書数
	 */
	public static int countCreatedReportList(int student_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		int count = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select count(pdf_id) as count from " + TABLE + " where student_id = ? and cc_flg = true and tc_flg = false";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_id);
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

	/** 担任の先生が報告書確認する時に使用する作成済報告書のリストを取得するためのもの
	 *  @param student_id - 先生が担当しているクラスに在籍している学生の学籍番号
	 *  @return createdReportList - 確認が必要な作成済報告書のリスト
	 */
	public static ArrayList<PDF> getCreatedReportList(int student_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		ArrayList<PDF> createdReportList = new ArrayList<PDF>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select pdf_id, file_name from " + TABLE + " where student_id = ? and cc_flg = true and tc_flg = false";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				PDF pdf = new PDF();
				pdf.setPdf_id(rs.getString("pdf_id"));
				pdf.setFile_name(rs.getString("file_name"));
				createdReportList.add(pdf);
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
		return createdReportList;
	}

	/** 就職課の先生の確認が必要な報告書数をカウントするためのもの
	 *  @return count - 確認が必要な作成済報告書数
	 */
	public static int countCreatedReportList(){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		int count = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select count(pdf_id) as count from " + TABLE + " where cc_flg = true and edc_flg = false";
			pstmt = con.prepareStatement(sql);
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

	/** 就職課の先生が報告書確認する時に使用する作成済報告書のリストを取得するためのもの
	 *  @return createdReportList - 確認が必要な作成済報告書のリスト
	 */
	public static ArrayList<PDF> getCreatedReportList(){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		ArrayList<PDF> createdReportList = new ArrayList<PDF>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select pdf_id, file_name from " + TABLE + " where cc_flg = true and edc_flg = false";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				PDF pdf = new PDF();
				pdf.setPdf_id(rs.getString("pdf_id"));
				pdf.setFile_name(rs.getString("file_name"));
				createdReportList.add(pdf);
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
		return createdReportList;
	}

	/** 提出日を取得するためのもの
	 *  @param pdf_id - 提出日を取得したい報告書の報告書ID
	 *  @return filing_date - 取得した提出日
	 */
	public static LocalDate getFiling_date(String pdf_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		LocalDate filing_date = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select filing_date from " + TABLE + " where pdf_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdf_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				filing_date = DateConversion.localDateConversion(rs.getDate("filing_date"));
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
		return filing_date;
	}

	/** 報告書を作成した学生の学籍番号を取得するためのもの
	 *  @param pdf_id - 学生の学籍番号を取得したい報告書の報告書ID
	 *  @return student_id - 取得した学生の学籍番号
	 */
	public static int getStudent_Id(String pdf_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		int student_id = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select student_id from " + TABLE + " where pdf_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdf_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				student_id = rs.getInt("student_id");
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
		return student_id;
	}

	/** 報告書確認が済んでtc_flgかedc_flgを立てるためのもの
	 * 	@param pdf_id - 確認が済んだ報告書の報告書ID
	 *  @param belongs_id - 教師の所属ID
	 *  @return row - updateした結果の行数
	 */
	public static int checkReport(String pdf_id, String belongs_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "update " + TABLE + " set ";

			if("e".equals(belongs_id)){	//所属が就職課の場合
				sql += "edc_flg = true ";
			}else{	//所属がそれ以外の場合
				sql += "tc_flg = true ";
			}

			sql += "where pdf_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdf_id);
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

	/** 担任の先生と就職課の先生か確認終了したか確認するために使用
	 *  @param pdf_id - 双方が確認終了したか確認したい報告書の報告書ID
	 *  @return flgs - 取得したtc_flgとedc_flg
	 */
	public static boolean[] getFlgs(String pdf_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		boolean[] flgs = new boolean[2];

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select tc_flg, edc_flg from " + TABLE + " where pdf_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdf_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				flgs[0] = rs.getBoolean("tc_flg");
				flgs[1] = rs.getBoolean("edc_flg");
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
		return flgs;
	}

	/** 報告書に関連した会社IDを取得するためのもの
	 *  @param pdf_id - 会社IDを取得したい報告書の報告書ID
	 *  @return company_id - 取得した会社ID
	 */
	public static String getCompany_Id(String pdf_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		String company_id = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select company_id from " + TABLE + " where pdf_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdf_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				company_id = rs.getString("company_id");
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
		return company_id;
	}

	/** 報告書のファイル名を取得するためのもの
	 *  @param pdf_id - ファイル名を取得したい報告書の報告書ID
	 *  @return file_name - 取得したファイル名
	 */
	public static String getFile_name(String pdf_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		String file_name = null;

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select file_name from " + TABLE + " where pdf_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdf_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				file_name = rs.getString("file_name");
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
		return file_name;
	}

	/** 生徒閲覧時に報告書のファイル名リストを取得するためのもの
	 *  @param year - 年度の検索値
	 *  @param company_name - 会社名の検索値
	 *  @return file_nameList - 取得したファイル名リスト
	 */
	public static ArrayList<String> getFile_nameList(int year, String company_name){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		LocalDate start_date = LocalDate.now();
		LocalDate last_date = LocalDate.now();
		ArrayList<String> file_nameList = new ArrayList<String>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select file_name from " + TABLE + " where file_name like \"%";
			sql += company_name + "%\\_%\\_%\"";

			if(year != 0){	//年度が指定されていた場合
				sql += " and filing_date >= ? and filing_date <= ?";
			}

			sql += " and cc_flg = true and tc_flg = true and edc_flg = true";
			pstmt = con.prepareStatement(sql);

			if(year != 0){	//年度が指定されていた場合
				start_date = LocalDate.of(year, 4, 2);
				last_date = LocalDate.of(year + 1, 4, 1);
				pstmt.setDate(1, Date.valueOf(start_date));
				pstmt.setDate(2, Date.valueOf(last_date));
			}

			rs = pstmt.executeQuery();
			while(rs.next()){
				file_nameList.add(rs.getString("file_name"));
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
		return file_nameList;
	}

	/** 生徒閲覧時に自分が作成した報告書のファイル名リストを取得するためのもの
	 *  @param student_id - ログインしている生徒の学籍番号
	 *  @return myFile_nameList - 取得したファイル名リスト
	 */
	public static ArrayList<String> getMyFile_nameList(int student_id){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		ArrayList<String> myFile_nameList = new ArrayList<String>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select file_name from " + TABLE + " where student_id = ? "
					+ "and cc_flg = true and tc_flg = true and edc_flg = true";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student_id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				myFile_nameList.add(rs.getString("file_name"));
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
		return myFile_nameList;
	}

	/** 教師閲覧時に報告書のファイル名リストを取得するためのもの
	 *  @param student_id - 学籍番号の検索値
	 *  @param name - 氏名の検索値
	 *  @param year - 年度の検索値
	 *  @param company_name - 会社名の検索値
	 *  @return file_nameList - 取得したファイル名リスト
	 */
	public static ArrayList<String> getFile_nameList(int student_id, String name, int year, String company_name){
		Connection con = null;
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		LocalDate start_date = LocalDate.now();
		LocalDate last_date = LocalDate.now();
		ArrayList<String> file_nameList = new ArrayList<String>();

		try{
			Class.forName(CLASSNAME);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "select file_name from " + TABLE + " where file_name like \"%";
			sql += company_name + "%\\_" + student_id + "%\\_%" + name + "%\"";

			if(year != 0){	//年度が指定されていた場合
				sql += " and filing_date >= ? and filing_date <= ?";
			}

			sql += " and cc_flg = true and tc_flg = true and edc_flg = true";
			pstmt = con.prepareStatement(sql);

			if(year != 0){	//年度が指定されていた場合
				start_date = LocalDate.of(year, 4, 2);
				last_date = LocalDate.of(year + 1, 4, 1);
				pstmt.setDate(1, Date.valueOf(start_date));
				pstmt.setDate(2, Date.valueOf(last_date));
			}

			rs = pstmt.executeQuery();
			while(rs.next()){
				file_nameList.add(rs.getString("file_name"));
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
		return file_nameList;
	}
}