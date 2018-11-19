package test.dao;

import java.util.ArrayList;

import dao.TeacherDAO;
import dto.Teacher;

public class TeacherDAOTest {

	public static void main(String[] args) {
//		String name = "サンプル";
//		String belongs_id = "s";
//		String mail_address = "sample@gmail.com";
//		String image_filename = "hanko.jpg";
//		String user_id = "aaaa";
//		Teacher teacher = new Teacher(0, name, belongs_id, mail_address, false, image_filename, user_id);
//		int row = TeacherDAO.addTeacher(teacher);
//		System.out.println(row);

//		String user_id = "aaaa";
//		int teacher_id = TeacherDAO.searchTeacher_id(user_id);
//		if(teacher_id != 0){
//			System.out.println("教師ID:" + teacher_id);
//		}else{
//			System.out.println("教師データと紐づいていません。");
//		}

//		int teacher_id = 1;
//		Teacher teacher = TeacherDAO.getTeacher(teacher_id);
//		if(teacher != null){
//			System.out.println("教師ID:" + teacher.getTeacher_id());
//			System.out.println("教師名:" + teacher.getName());
//			System.out.println("所属:" + teacher.getBelongs_id());
//			System.out.println("メールアドレス:" + teacher.getMail_address());
//			System.out.println("システム管理者フラグ:" + teacher.isAdmin_flg());
//		}else{
//			System.out.println("該当の教師データはありません。");
//		}

		int myTeacher_id = 5;
		ArrayList<Teacher> list = TeacherDAO.getTeachers(myTeacher_id);
		for(Teacher teacher : list){
			System.out.println("教師ID:" + teacher.getTeacher_id());
			System.out.println("氏名:" + teacher.getName());
			System.out.println("所属:" + teacher.getBelongs_id());
			System.out.println("権限:" + teacher.isAdmin_flg());
			System.out.println();
		}

//		int teacher_id = 5;
//		boolean admin_flg = true;
//		int row = TeacherDAO.changeAuthority(teacher_id, admin_flg);
//		if(row >= 1){
//			System.out.println("変更完了");
//		}else{
//			System.out.println("変更失敗");
//		}

//		int teacher_id = 1;
//		int row = TeacherDAO.dropTeacher(teacher_id);
//		System.out.println(row);
	}
}