package test.dao;

import dao.CompanyDAO;

public class CompanyDAOTest {

	public static void main(String[] args) {
//		Company company = new Company();
//		String company_name = "株式会社サンプル";
//		String postal_code = "023" + "-" + "0096";
//		String address = "岩手県盛岡市中央通り三丁目";
//		String phone_number = "019" + "-" + "456" + "-" + "6811";
//		String company_id = CreateID.createID(8);
//
//		company.setCompany_id(company_id);
//		company.setCompany_name(company_name);
//		company.setPostal_code(postal_code);
//		company.setAddress(address);
//		company.setPhone_number(phone_number);
//
//		int row = CompanyDAO.addCompany(company);
//		if(row >= 1){
//			System.out.println("追加完了");
//		}else{
//			System.out.println("追加失敗");
//		}
//
//		company_name = "サンプルコーポレーション";
//		postal_code = "029" + "-" + "8635";
//		address = "東京都港区みなとみらい一丁目一番地";
//		phone_number = "03" + "-" + "5469" + "-" + "1476";
//
//		company.setCompany_name(company_name);
//		company.setPostal_code(postal_code);
//		company.setAddress(address);
//		company.setPhone_number(phone_number);
//		row = CompanyDAO.updateCompany(company);
//		if(row >= 1){
//			System.out.println("修正完了");
//		}else{
//			System.out.println("修正失敗");
//		}

//		String serch = "";
//		ArrayList<Company> list = CompanyDAO.getCompanies(serch);
//		for(Company com : list){
//			System.out.println("会社名:" + com.getCompany_name());
//			System.out.println("郵便番号:" + com.getPostal_code());
//			System.out.println("住所:" + com.getAddress());
//			System.out.println("電話番号:" + com.getPhone_number());
//
//			System.out.println();
//		}

//		String company_id = "4DuYjVSB";
//		Company company = CompanyDAO.searchCompany(company_id);
//
//		System.out.println("会社名:" + company.getCompany_name());
//		System.out.println("郵便番号:" + company.getPostal_code());
//		System.out.println("住所:" + company.getAddress());
//		System.out.println("電話番号:" + company.getPhone_number());

		String company_id = "4DuYjVSB";
		String company_name = CompanyDAO.getCompany_name(company_id);
		System.out.println(company_name);
	}
}