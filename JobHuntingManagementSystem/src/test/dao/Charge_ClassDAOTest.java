package test.dao;

import dao.Charge_ClassDAO;

public class Charge_ClassDAOTest {

	public static void main(String[] args) {
		int teacher_id = 1;
		String course_id = "se";
		int school_year = 1;

		int row = Charge_ClassDAO.addCharge_Class(teacher_id, course_id, school_year);
		if(row >= 1){
			System.out.println("追加完了");
		}else{
			System.out.println("追加失敗");
		}

		teacher_id = Charge_ClassDAO.searchTeacher_id(course_id, school_year);
		System.out.println(teacher_id);
	}
}