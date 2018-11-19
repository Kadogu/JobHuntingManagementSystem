package test.dao;

import dao.CourseDAO;

public class CourseDAOTest {

	public static void main(String[] args) {
//		String course_id = "se";
//		int number = CourseDAO.getNumber(course_id);
//		System.out.println("数字:" + number);

//		String department_id = "s";
//		ArrayList<String> list = CourseDAO.getCourse_idList(department_id);
//		for(String course_id : list){
//			System.out.println(course_id);
//		}

//		String belongs_id = "d";
//		ArrayList<Course> courseList = CourseDAO.getCourseList();
//		for(Course course : courseList){
//			if(belongs_id.equals(course.getBelongs_id())){
//				for(int i = 1; i <= course.getYear(); i++){
//					System.out.println(course.getCourse_name() + i + "年");
//				}
//			}
//		}

		String course_id = "ne";
		String department_id = CourseDAO.getDepartment_Id(course_id);
		System.out.println("学科ID:" + department_id);
	}
}