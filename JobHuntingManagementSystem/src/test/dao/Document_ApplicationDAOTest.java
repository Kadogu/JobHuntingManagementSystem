package test.dao;

import dao.Document_ApplicationDAO;

public class Document_ApplicationDAOTest {

	public static void main(String[] args) {
//		Document_Application document_application = new Document_Application();
//		document_application.setDocument_application_id(CreateID.createID(8));
//		document_application.setApplication_date(LocalDate.now());
//		document_application.setStudent_id(4181101);
//		document_application.setCompany_id("OgMUeaRJ");
//		boolean bring_mailing = true;
//		document_application.setBring_mailing(bring_mailing);
//		if(!bring_mailing){
//			document_application.setDestination(1);
//		}
//		document_application.setDeadline(LocalDate.of(2018, 11, 15));
//		int[] documents_flg = {1, 0, 0, 0, 1, 1};
//		document_application.setDocuments_flg(documents_flg);
//		document_application.setTeacher_id(1);
//
//		int row = Document_ApplicationDAO.createApplication(document_application);
//		if(row >= 1){
//			System.out.println("届出書の作成完了");
//		}else{
//			System.out.println("届出書の作成失敗");
//		}
//		System.out.println();

//		int student_id = 4181101;
//		ArrayList<Document_Application> list = Document_ApplicationDAO.getDocument_Applications(student_id);
//		DateTimeFormatter f = DateTimeFormatter.ofPattern("MM月dd日");
//		for(Document_Application d_a : list){
//			System.out.println("届出書ID:" + d_a.getDocument_application_id());
//			System.out.println("申込日:" + d_a.getApplication_date().format(f));
//			System.out.println("会社ID:" + d_a.getCompany_id());
//			if(d_a.isBring_mailing()){
//				System.out.println("提出方法:持参");
//			}else{
//				System.out.println("提出方法:郵送");
//			}
//			System.out.println("締切日:" + d_a.getDeadline().format(f));
//			int[] documents_flg = d_a.getDocuments_flg();
//			String[] documents_name = {"履歴書","卒業見込証明書","成績証明書","健康診断書","推薦書","その他"};
//			for(int i = 0; i < documents_flg.length; i++){
//				if(documents_flg[i] == 1){
//					System.out.println(documents_name[i] + ":○");
//				}else{
//					System.out.println(documents_name[i] + ":");
//				}
//			}
//			System.out.println("発行手数料:" + d_a.getIssue_fee());
//			if(d_a.isIssue_flg()){
//				System.out.println("発行状態:発行済");
//			}else{
//				System.out.println("発行状態:発行前");
//			}
//			System.out.println();
//		}

//		String document_application_id = "cMANPoa6";
//		if(document_application_id.equals(Document_ApplicationDAO.searchDocument_Application_Id(document_application_id))){
//			System.out.println("一致");
//		}else{
//			System.out.println("不一致");
//		}

//		boolean bring_mailing = false;
//		int teacher_id = 2;
//		ArrayList<Document_Application> document_applicationList = Document_ApplicationDAO.getDocument_ApplicationList(bring_mailing, teacher_id);
//		for(Document_Application d_aList : document_applicationList){
//			System.out.println("届出書ID:" + d_aList.getDocument_application_id());
//			System.out.println("学籍番号:" + d_aList.getStudent_id());
//			System.out.println("会社ID:" + d_aList.getCompany_id());
//			System.out.println("締切日:" + d_aList.getDeadline());
//			int[] documents_flg = d_aList.getDocuments_flg();
//			String[] documents_name = {"履歴書", "卒業見込証明書", "成績証明書", "健康診断書", "推薦書", "その他"};
//			StringBuilder sb = new StringBuilder();
//			for(int i = 0; i < documents_name.length; i++){
//				if(documents_flg[i] == 1){
//					sb.append(documents_name[i] + ",");
//				}
//			}
//			System.out.println("申請書類:" + sb.toString());
//			System.out.println("手数料:" + d_aList.getIssue_fee());
//			System.out.println("送付先:" + d_aList.getDestination());
//			System.out.println();
//		}

//		String document_application_id = "Oprd3BJH";
//		Document_Application document_application = Document_ApplicationDAO.getDocument_Application(document_application_id);
//		System.out.println("学籍番号:" + document_application.getStudent_id());
//		System.out.println("会社ID:" + document_application.getCompany_id());
//		System.out.println("発行手数料:" + document_application.getIssue_fee() + "円");

		String document_application_id = "BZbjxURY";
		int row = Document_ApplicationDAO.updateIssue_flg(document_application_id);
		if(row >= 1){
			System.out.println("変更完了");
		}else{
			System.out.println("変更失敗");
		}
	}
}