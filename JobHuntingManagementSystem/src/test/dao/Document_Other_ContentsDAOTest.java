package test.dao;

import dao.Document_Other_ContentsDAO;

public class Document_Other_ContentsDAOTest {

	public static void main(String[] args) {
//		String document_application_id = "uYT6En6c";
//		String contents = "在学証明書";
//		int row = Document_Other_ContentsDAO.addDocument_Other_Contents(document_application_id, contents);
//		if(row >= 1){
//			System.out.println("追加完了");
//		}else{
//			System.out.println("追加失敗");
//		}

		String document_application_id = "uYT6En6c";
		String contents = Document_Other_ContentsDAO.getContents(document_application_id);
		System.out.println("その他:" + contents);
	}
}