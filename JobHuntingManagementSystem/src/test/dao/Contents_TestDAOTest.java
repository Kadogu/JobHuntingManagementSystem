package test.dao;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.Contents_TestDAO;
import dto.Contents_Test;

public class Contents_TestDAOTest {

	public static void main(String[] args) {
//		String pdf_id = "1MPWsdcZ";
//		int n = 1;
//		LocalDate start_date = LocalDate.of(2018, 4, 14);
//		int start_hour = 10;
//		int start_minute = 30;
//		int last_hour = 12;
//		int last_minute = 10;
//		String test_category = "筆記,面接";
//		String writing = "一般常識,SPI,適性(性格),作文,その他(ITパスポート模擬試験)";
//		int viewer_no = 3;
//		int view_time = 60;
//		String view_content = "集団面接";
//		int groop_no = 5;
//		int discussion_no = 0;
//		Contents_Test contents_test = new Contents_Test(pdf_id, n, start_date, start_hour, start_minute, last_hour, last_minute, test_category, writing, viewer_no, view_time, view_content, groop_no, discussion_no);
//		int row = Contents_TestDAO.addContents_Test(contents_test);
//		if(row >= 1){
//			System.out.println("追加完了");
//		}else{
//			System.out.println("追加失敗");
//		}

		String pdf_id = "1MPWsdcZ";
		String[] n = {"一", "二", "三"};
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M月d日 ");
		ArrayList<Contents_Test> contents_testList = Contents_TestDAO.getContents_TestList(pdf_id);
		for(Contents_Test contents_test : contents_testList){
			System.out.println(n[contents_test.getN() - 1] +  "次試験");
			System.out.print("日時:" + contents_test.getStart_date().format(formatter));
			System.out.print(contents_test.getStart_hour() + "時 ");
			System.out.print(contents_test.getStart_minute() + "分～ ");
			System.out.print(contents_test.getLast_hour() + "時 ");
			System.out.println(contents_test.getLast_minute() + "分");
			System.out.println("区分:" + contents_test.getTest_category());
			System.out.println("筆記試験内容:" + contents_test.getWriting()) ;
			System.out.println("面接官:" + contents_test.getViewer_no() + "名") ;
			System.out.println("面接時間:" + contents_test.getView_time() + "分間") ;
			System.out.println("面接試験内容:" + contents_test.getView_content()) ;
			System.out.println("集団面接人数:" + contents_test.getGroop_no() + "名") ;
			System.out.println("グループディスカッション人数:" + contents_test.getDiscussion_no() + "名") ;
			System.out.println();
		}
	}
}