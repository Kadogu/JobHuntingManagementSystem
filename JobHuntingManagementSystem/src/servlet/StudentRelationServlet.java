package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDAO;
import dao.DepartmentDAO;
import dao.StudentDAO;
import dto.Course;
import dto.Department;

/**
 * Servlet implementation class StudentRelationServlet
 * 学生関連の学籍番号の追加・確認と留年リストの追加・削除と進級処理をするサーブレット
 */
@WebServlet("/StudentRelation")
public class StudentRelationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentRelationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");

		String view = "WEB-INF/view/";

		if("student_relation".equals(status)){	//学生関連ページへの画面遷移の場合
			view += "studentRelation.jsp";	//学生関連ページ
		}else if("student_id_add".equals(status)){	//学籍番号追加の場合
			ArrayList<Department> departmentList = DepartmentDAO.getDepartmentList();
			request.setAttribute("departmentList", departmentList);

			ArrayList<Course> courseList = CourseDAO.getCourseList();
			request.setAttribute("courseList", courseList);

			view += "student_id_add.jsp";	//学籍番号追加ページ
		}else if("student_id_confirmation".equals(status)){	//学籍番号確認の場合
			String department = request.getParameter("department");
			String course = request.getParameter("course");

			String work = request.getParameter("school_year");
			int school_year = 0;

			if(work != null && work.matches("^\\d$")){
				school_year = Integer.parseInt(work);
			}

			ArrayList<String> course_idList = new ArrayList<String>();

			if(department != null && department.matches("^[a-z]{1,5}$")){	//学科が指定されている場合
				if(course != null && "".equals(course)){	//コースが指定されていない場合
					course_idList = CourseDAO.getCourse_idList(department);
				}else if(course.matches("^[a-z]{1,5}$")){	//コースも指定されている場合
					course_idList.add(course);
				}
			}else{	//学科が指定されていない場合
				if(course != null && course.matches("^[a-z]{1,5}$")){	//コースが指定されていた場合
					course_idList.add(course);
				}
			}

			ArrayList<Integer> student_idList = StudentDAO.getStudent_idList(course_idList, school_year);
			request.setAttribute("student_idList", student_idList);

			ArrayList<Department> departmentList = DepartmentDAO.getDepartmentList();
			request.setAttribute("departmentList", departmentList);

			ArrayList<Course> courseList = CourseDAO.getCourseList();
			request.setAttribute("courseList", courseList);

			request.setAttribute("department_id", department);
			request.setAttribute("course_id", course);
			request.setAttribute("school_year", school_year);

			view += "student_id_confirmation.jsp";	//学籍番号確認ページ
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String department = request.getParameter("department");
		String course = request.getParameter("course");
		int school_year = Integer.parseInt(request.getParameter("school_year"));
		int number_of_people = Integer.parseInt(request.getParameter("number_of_people"));

		LocalDate localDate = LocalDate.now();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();

		if(month <= 3){	//1～3月だった場合の処理
			year--;
		}

		year -= (school_year - 1);	//学年分年数を減らす
		year %= 100;	//年の下2桁を取得

		int department_num = DepartmentDAO.getNumber(department);

		int course_num = CourseDAO.getNumber(course);

		String work = "4" + year + department_num + course_num;

		int student_id = 0;
		int row = 0;

		for(int i = 1; i <= number_of_people; i++){
			if(i < 10){	//iが1桁の場合
				student_id = Integer.parseInt(work + "0" + i);
			}else{	//iが2桁の場合
				student_id = Integer.parseInt(work + i);
			}

			row += StudentDAO.addStudent_id(student_id, course, school_year);
		}

		String view = "WEB-INF/view/";

		if(row == number_of_people){	//人数分ちゃんと学籍番号を格納できたかチェック
			String text = work + "01～" + work + number_of_people;
			request.setAttribute("text", text);

			view += "student_id_addCompletion.jsp";	//学籍番号追加完了ページ
		}else{

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}