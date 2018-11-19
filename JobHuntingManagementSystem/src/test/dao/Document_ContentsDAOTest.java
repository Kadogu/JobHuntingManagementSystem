package test.dao;

import dao.Document_ContentsDAO;

public class Document_ContentsDAOTest {

	public static void main(String[] args) {
		String[] documents = Document_ContentsDAO.getDocuments();
		for(String document : documents){
			System.out.println(document);
		}
	}
}