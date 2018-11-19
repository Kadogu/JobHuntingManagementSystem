package test.dao;

import dao.DestinationDAO;
import dto.Destination;

public class DestinationDAOTest {

	public static void main(String[] args) {
//		String document_application_id = "uYT6En6c";
//		String postal_code = "034" + "-" + "0876";
//		String address = "岩手県盛岡市中央通り三丁目2番地";
//		String individual = "細川純也";
//		int row = DestinationDAO.addDestination(new Destination(document_application_id, postal_code, address, individual));
//		if(row >= 1){
//			System.out.println("追加完了");
//		}else{
//			System.out.println("追加失敗");
//		}

		String document_application_id = "sBGwpuR5";
		Destination destination = DestinationDAO.getDestination(document_application_id);
		System.out.println("郵便番号:" + destination.getPostal_code());
		System.out.println("住所:" + destination.getAddress());
		System.out.println("個人名:" + destination.getIndividual());
	}
}