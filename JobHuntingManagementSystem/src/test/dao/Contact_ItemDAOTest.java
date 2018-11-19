package test.dao;

import dao.Contact_ItemDAO;

public class Contact_ItemDAOTest {

	public static void main(String[] args) {
		int contact_item_id = 3;
		String item_name = Contact_ItemDAO.getItem_name(contact_item_id);
		System.out.println(item_name);
	}
}