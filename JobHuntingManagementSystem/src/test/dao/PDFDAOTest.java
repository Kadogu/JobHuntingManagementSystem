package test.dao;

import java.util.ArrayList;

import dao.PDFDAO;

public class PDFDAOTest {

	public static void main(String[] args) {
//		String pdf_id = CreateID.createID(8);
//		int student_id = 4171102;
//		String sorf = "合格";
//		LocalDate sfdate = LocalDate.of(2018, 4, 28);
//		String company_id = "0isODEXy";
//		String type_of_industry = "it";
//		String occupations = "se";
//		String application_form = "学校";
//		PDF pdf = new PDF(pdf_id, null, student_id, null, sorf, sfdate, company_id, type_of_industry, occupations, application_form, null, null, false, false, false);
//		int row = PDFDAO.addReport1(pdf);
//		if(row >= 1){
//			System.out.println("追加完了");
//		}else{
//			System.out.println("追加失敗");
//		}

//		int student_id = 4171102;
//		ArrayList<PDF> reportList = PDFDAO.getReportList(student_id);
//		for(PDF report : reportList){
//			System.out.println("報告書ID:" + report.getPdf_id());
//			System.out.println("会社ID:" + report.getCompany_id());
//			System.out.println();
//		}

//		String pdf_id = "INTa83Bb";
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M月d日");
//		PDF report = PDFDAO.getReport1(pdf_id);
//		if(report != null){
//			System.out.println("合否:" + report.getSorf());
//			System.out.println("合否期日:" + report.getSfdate().format(formatter));
//			System.out.println("業種:" + report.getType_of_industry());
//			System.out.println("職種:" + report.getOccupations());
//			System.out.println("応募形態:" + report.getApplication_form());
//		}else{
//			System.out.println("エラー");
//		}

//		String pdf_id = "INTa83Bb";
//		String content_of_test = "試験は圧迫面接でした。<br>困りました。";
//		String advice_to_junior = "理不尽には負けないように頑張ろう!!";
//		PDF report = new PDF();
//		report.setPdf_id(pdf_id);
//		report.setContent_of_test(content_of_test);
//		report.setAdvice_to_junior(advice_to_junior);
//		int row = PDFDAO.addReport3(report);
//		if(row >= 1){
//			System.out.println("格納完了");
//		}else{
//			System.out.println("格納失敗");
//		}

//		String pdf_id = "INTa83Bb";
//		PDF report = PDFDAO.getReport3(pdf_id);
//		if(report != null){
//			System.out.println("試験の内容:" + report.getContent_of_test());
//			System.out.println("後輩へのアドバイス:" + report.getAdvice_to_junior());
//		}else{
//			System.out.println("エラー");
//		}

		int student_id = 41;
		String name = "ン";
		int year = 0;
		String company_name = "";
		ArrayList<String> file_nameList = PDFDAO.getFile_nameList(student_id, name, year, company_name);
		for(String file_name : file_nameList){
			System.out.println(file_name);
		}
	}
}