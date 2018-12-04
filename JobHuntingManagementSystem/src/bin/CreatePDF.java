package bin;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.CompanyDAO;
import dao.Contents_TestDAO;
import dao.CourseDAO;
import dao.DepartmentDAO;
import dao.OccupationsDAO;
import dao.PDFDAO;
import dao.StudentDAO;
import dao.Type_Of_IndustryDAO;
import dto.Company;
import dto.Contents_Test;
import dto.PDF;
import dto.Student;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CreatePDF {

	/** 報告書データを基にPDFファイルを作成するもの
	 *  @param pdf_id - PDFファイルを作成したい報告書データの報告書ID
	 *  @return flg - PDFファイル作成成功か失敗かを判断するためのもの
	 */
	public static boolean createPDF(String pdf_id, String jasperPath, String pdfPath){
		try {
	          // テンプレートの読み込み
	          File jasperFile = new File(jasperPath);
	          if(jasperFile.exists()){

	          // データ作成（パラメータ）
	          HashMap<String,  Object> params = new HashMap<String,  Object>();

	          //報告書データ取得
	          PDF report = PDFDAO.getReport1(pdf_id);
	          PDF report3 = PDFDAO.getReport3(pdf_id);
	          ArrayList<Contents_Test> report2 = Contents_TestDAO.getContents_TestList(pdf_id);

	          //報告書3/3のデータを報告書1/3のデータと合わせる
	          report.setContent_of_test(report3.getContent_of_test());
	          report.setAdvice_to_junior(report3.getAdvice_to_junior());

	          //提出日
	          LocalDate localDate = PDFDAO.getFiling_date(pdf_id);
	          int year = 0;
	          int month = 0;
	          int day = 0;
	          String filing_date = "提出日　";
	          if(localDate != null){
	        	  year = localDate.getYear();
	        	  month = localDate.getMonthValue();
	        	  day = localDate.getDayOfMonth();
	        	  filing_date += year + " 年 " + month + " 月 " + day + " 日";
	          }
	          params.put("filing_date", filing_date);

	          //氏名
	          int student_id = PDFDAO.getStudent_Id(pdf_id);
	          Student student = StudentDAO.searchStudent(student_id);
	          String name = student.getName();
	          params.put("name", name);

	          //学科・コース
	          String course_id = student.getCourse_id();
	          String department_id = CourseDAO.getDepartment_Id(course_id);
	          String department = DepartmentDAO.getDepartment_name(department_id) + " 科　";
	          String course = department + CourseDAO.getCourse_name(course_id) + " コース";
	          params.put("course", course);

	          //試験結果
	          String sorf = report.getSorf();
	          params.put("sorf", sorf);

	          //合否期日
	          localDate = report.getSfdate();
	          month = 0;
	          day = 0;
	          String sfdate = "";
	          if(localDate != null){
	        	  month = localDate.getMonthValue();
	        	  day = localDate.getDayOfMonth();
	        	  sfdate += month + " 月 " + day + " 日";
	          }
	          params.put("sfdate", sfdate);

	          //応募形態
	          String application_form = report.getApplication_form();
	          params.put("application_form", application_form);

	          //会社名
	          String company_id = PDFDAO.getCompany_Id(pdf_id);
	          Company company = CompanyDAO.searchCompany(company_id);
	          String company_name = company.getCompany_name();
	          params.put("company_name", company_name);

	          //会社の電話番号
	          String phone_number = company.getPhone_number();
	          params.put("phone_number", phone_number);

	          //会社所在地郵便番号
	          String postal_code = "〒" + company.getPostal_code();
	          params.put("postal_code",postal_code);

	          //会社所在地住所
	          String address = company.getAddress();
	          params.put("address", address);

	          //業種
	          String industry_code = report.getType_of_industry();
	          String type_of_industry = Type_Of_IndustryDAO.getIndustry_name(industry_code);
	          params.put("type_of_industry", type_of_industry);

	          //職種
	          int occupations_code = report.getOccupations();
	          String occupations = OccupationsDAO.getOccupations_name(occupations_code);
	          params.put("occupations", occupations);

	          //n次試験内容
	          String[] times = {"1st_", "2nd_", "3rd_"};
	          for(int n = 1; n <= 3; n++){
	        	  Contents_Test contents_test = report2.get(n - 1);

	        	  //試験日時
	        	  LocalDate start_date = contents_test.getStart_date();
	        	  String date = null;
	        	  if(start_date != null){
	        		  year = start_date.getYear();
	        		  month = start_date.getMonthValue();
	        		  day = start_date.getDayOfMonth();
	        		  int start_hour = contents_test.getStart_hour();
	        		  int start_minute = contents_test.getStart_minute();
	        		  int last_hour = contents_test.getLast_hour();
	        		  int last_minute = contents_test.getLast_minute();
	        		  date = year + " 年 " + month + " 月 " + day + " 日 " + start_hour + " 時 " + start_minute + " 分～ " + last_hour + " 時 " + last_minute + " 分";
	        	  }
	        	  params.put(times[n - 1] + "date", date);

	        	  //試験区分
	        	  String test_category = contents_test.getTest_category();
	        	  params.put(times[n - 1] + "test_category", test_category);

	        	  //筆記試験内容
	        	  String writing = contents_test.getWriting();
	        	  if(writing != null){
	        		  writing = writing.replaceAll(",", "　");
	        	  }
	        	  params.put(times[n - 1] + "writing", writing);

	        	  //面接官数と面接時間
	        	  int viewer_no = contents_test.getViewer_no();
	        	  int view_time = contents_test.getView_time();
	        	  String interview = null;
	        	  if(viewer_no != 0 && view_time != 0){
	        		  interview = "面接官: " + viewer_no + " 名 (" + view_time + " 分間)";
	        	  }
	        	  params.put(times[n - 1] + "interview", interview);

	        	  //面接試験内容
	        	  String view_content = contents_test.getView_content();
	        	  int groop_no = 0;
	        	  int discussion_no = 0;
	        	  String interview_contents = view_content;
	        	  if("集団面接".equals(view_content)){
	        		  groop_no = contents_test.getGroop_no();
	        		  interview_contents += " ( " + groop_no + " 名)";
	        	  }else if("グループディスカッション".equals(view_content)){
	        		  discussion_no = contents_test.getDiscussion_no();
	        		  interview_contents += " ( " + discussion_no + " 名)";
	        	  }
	        	  params.put(times[n - 1] + "interview_contents", interview_contents);
	          }

	          //試験の内容
	          String content_of_test = report.getContent_of_test().replaceAll("\r\n","\n").replaceAll("\n","<br/>");
	          params.put("content_of_test", content_of_test);

	          //後輩へのアドバイス
	          String advice_to_junior = report.getAdvice_to_junior().replaceAll("\r\n","\n").replaceAll("\n","<br/>");
	          params.put("advice_to_junior", advice_to_junior);

	          List<String> list = new ArrayList<String>();
	          list.add("");

	          JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile.getAbsolutePath());

	          // データの設定
	          JasperPrint print = JasperFillManager.fillReport(jasperReport,  params, new JRBeanCollectionDataSource(list));

	          // PDFファイル作成
	          String file_name = PDFDAO.getFile_name(pdf_id);
	          File pdf = new File(pdfPath + file_name + ".pdf");

	          // PDF出力
	          JasperExportManager.exportReportToPdfFile(print,  pdf.getAbsolutePath());

	          return true;
		     }else{
		    	 System.out.println("jrxmlファイルが見つかりませんでした。");
		    	 return false;
	    	 }
	     } catch (Exception e) {
	          e.printStackTrace();
	          return false;
	     }
	}
}