package test.dao;

import dao.AccountDAO;

public class AccountDAOTest {

	public static void main(String[] args) {
//		String user_id = "aaaa";
//		String pw = AccountDAO.login(user_id);
//		System.out.println(pw);

//		String user_id = "aaaa";
//		String pw = "aaaa";
//		String hashPW = AccountDAO.hashPW(pw);
//		Account account = new Account(user_id, hashPW, false);
//		int row = AccountDAO.addUser(account);
//		System.out.println(row);

//		String user_id = "aaaa";
//		int row = AccountDAO.dropAccount(user_id);
//		System.out.println(row);

		String user_id = "cccc";
		String pw = "ccccCCCC.3";
		String hashPW = AccountDAO.hashPW(pw);
		int row = AccountDAO.changePW(user_id, hashPW);
		if(row >= 1){
			System.out.println("PW変更完了");
		}else{
			System.out.println("PW変更失敗");
		}
	}
}