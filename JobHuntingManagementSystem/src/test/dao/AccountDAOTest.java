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

		String user_id = "aaaa";
		int row = AccountDAO.dropAccount(user_id);
		System.out.println(row);
	}
}