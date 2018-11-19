package test.dao;

import dao.StudentDAO;

public class StudentDAOTest {

	public static void main(String[] args) {
//		int student_id = 4171102;
//		String[] data = StudentDAO.searchStudent_ID(student_id);
//		if(data[0] == null){
//			System.out.println("学籍番号は不正です");
//		}else if(data[1] != null){
//			System.out.println("学生データ登録済です");
//		}else{
//			String name = "サンプル";
//			String mail_address = "sample@gmail.com";
//			int school_year = 1;
//			String course_id = "se";
//			String user_id = "cccc";
//
//			Student student = new Student(student_id, name, mail_address, school_year, course_id, 0, user_id);
//
//			int row = StudentDAO.addStudent(student);
//			System.out.println(row);
//		}

//		String user_id = "cccc";
//		student_id = StudentDAO.searchUser_ID(user_id);
//		if(student_id == 0){
//			System.out.println("学生データと紐づいていません。");
//		}else{
//			System.out.println("学籍番号:" + student_id);
//		}

//		Student student = StudentDAO.searchStudent(student_id);
//		if(student.getStudent_id() != 0){
//			System.out.println("学籍番号:" + student.getStudent_id());
//			System.out.println("氏名:" + student.getName());
//			System.out.println("メールアドレス:" + student.getMail_address());
//			System.out.println("学年:" + student.getSchool_year());
//			System.out.println("コースID:" + student.getCourse_id());
//		}else{
//			System.out.println("エラー");
//		}

//		int student_id = 4181201;
//		String course_id = "wp";
//		int school_year = 1;
//		int row = StudentDAO.addStudent_id(student_id, course_id, school_year);
//		if(row >= 1){
//			System.out.println("学籍番号追加完了");
//		}else{
//			System.out.println("学籍番号追加失敗");
//		}

//		ArrayList<String> course_idList = new ArrayList<String>();
//		course_idList.add("se");
//		course_idList.add("wp");
//		course_idList.add("ne");
//		int school_year = 0;
//		ArrayList<Integer> student_idList = StudentDAO.getStudent_idList(course_idList, school_year);
//		for(int student_id : student_idList){
//			System.out.println(student_id);
//		}

		int student_id = 4171102;
		String name = StudentDAO.getName(student_id);
		System.out.println("名前:" + name);
	}
}