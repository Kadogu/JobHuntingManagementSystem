package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import dao.Charge_ClassDAO;
import dao.CourseDAO;
import dao.DepartmentDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import dto.Account;
import dto.Course;
import dto.Department;
import dto.Student;
import dto.Teacher;

/**
 * Servlet implementation class UserDataRegistrationServlet
 * 学生データ登録と教師データ登録を行うサーブレット
 */
@WebServlet("/UserDataRegistration")
public class UserDataRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDataRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String category = (String)session.getAttribute("category");

		String view = "WEB-INF/view/";

		if("s".equals(category)){	//ユーザー種類が生徒だった場合の処理

			String status = request.getParameter("status");

			if("confirmation".equals(status)){	//学生データ確認だった場合の処理
				int student_id = Integer.parseInt(request.getParameter("student_id"));
				String[] data = StudentDAO.searchStudent_ID(student_id);

				if(data[0] == null){	//学籍番号が存在しなかった場合
					String error = "学籍番号は不正です";
					request.setAttribute("error", error);

					view += "studentDataConfirmation.jsp";	//学生データ確認ページ
				}else if(data[1] != null){	//学生データがアカウントと連携していた場合
					String error = "学生データ登録済です";
					request.setAttribute("error", error);

					view += "studentDataConfirmation.jsp";	//学生データ確認ページ
				}else{	//学生データが登録できる状態だった場合
					ArrayList<Department> departmentList = DepartmentDAO.getDepartmentList();
					request.setAttribute("departmentList", departmentList);

					ArrayList<Course> courseList = CourseDAO.getCourseList();
					request.setAttribute("courseList", courseList);

					Student student = StudentDAO.searchStudent(student_id);
					request.setAttribute("student", student);

					String department_id = CourseDAO.getDepartment_Id(student.getCourse_id());
					request.setAttribute("department_id", department_id);

					session.setAttribute("student_id", student_id);

					view += "studentDataRegistration.jsp";	//学生データ登録ページ
				}
			}else{	//学生データ登録だった場合の処理
				String name = request.getParameter("name");
				String course_id = request.getParameter("course");
				int school_year = Integer.parseInt(request.getParameter("school_year"));
				String mail_address = request.getParameter("mail_address");

				Account account = (Account)session.getAttribute("account");
				int row = AccountDAO.addUser(account);

				if(row >= 1){	//アカウント登録に成功した場合
					int student_id = (int)session.getAttribute("student_id");
					String user_id = account.getUser_id();
					Student student = new Student(student_id, name, mail_address, school_year, course_id, 0, user_id);
					row = StudentDAO.addStudent(student);

					if(row >= 1){	//学生データ登録に成功した場合
						view += "accountRegistrationCompletion.jsp";	//アカウント登録完了ページ
					}else{	//学生データ登録に失敗した場合
						view += "accountRegistrationError.jsp";	//アカウント登録失敗ページ
					}
				}else{	//アカウント登録に失敗した場合
					view += "accountRegistrationError.jsp";	//アカウント登録失敗ページ
				}
			}
		}else if("t".equals(category)){	//ユーザー種類が教師だった場合の処理
			String name = request.getParameter("name");

			String belongs_id = request.getParameter("belongs");

			String charge_class = request.getParameter("charge_class");
			String course_id = charge_class.substring(0, charge_class.length() - 1);
			int school_year = Integer.parseInt(charge_class.substring(charge_class.length() - 1));

			String mail_address = request.getParameter("mail_address");
			String image_filename = request.getParameter("image_filename");

			Account account = (Account)session.getAttribute("account");
			int row = AccountDAO.addUser(account);

			if(row >= 1){	//アカウント登録が成功した場合
				String user_id = account.getUser_id();

				Teacher teacher = new Teacher(0, name, belongs_id, mail_address, false, image_filename, user_id);
				row = TeacherDAO.addTeacher(teacher);

				if(row >= 1){	//教師データ登録に成功した場合
					int teacher_id = TeacherDAO.searchTeacher_id(user_id);

					row = Charge_ClassDAO.addCharge_Class(teacher_id, course_id, school_year);

					if(row >= 1){	//担当クラスデータ登録に成功した場合
						view += "accountRegistrationCompletion.jsp";	//アカウント登録完了ページ
					}else{
						row = TeacherDAO.dropTeacher(teacher_id);
						row = AccountDAO.dropAccount(user_id);

						view += "accountRegistrationError.jsp";	//アカウント登録失敗ページ
					}
				}else{	//教師データ登録に失敗した場合
					row = AccountDAO.dropAccount(user_id);

					view += "accountRegistrationError.jsp";	//アカウント登録失敗ページ
				}
			}else{	//アカウント登録に失敗した場合
				view += "accountRegistrationError.jsp";	//アカウント登録失敗ページ
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}