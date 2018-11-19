package test.dao;

import java.util.ArrayList;
import java.util.HashMap;

import dao.BelongsDAO;
import dto.Belongs;

public class BelongsDAOTest {

	public static void main(String[] args) {
		HashMap<String, String> map = BelongsDAO.getBelongsMap();
		String key = "s";
		String value = map.get(key);
		System.out.println("所属名:" + value);

		ArrayList<Belongs> list = BelongsDAO.getBelongsList();
		for(Belongs belongs : list){
			System.out.println("所属ID:" + belongs.getBelongs_id() + "　所属名:" + belongs.getBelongs_name());
		}
	}
}