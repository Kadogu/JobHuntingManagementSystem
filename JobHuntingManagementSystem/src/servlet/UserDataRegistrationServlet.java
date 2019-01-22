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
import dao.StudentDAO;
import dao.TeacherDAO;
import dto.Account;
import dto.Charge_Class;
import dto.Course;
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
		String view = "WEB-INF/view/top.jsp";	//トップページ
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		String category = (String)session.getAttribute("category");

		String view = "WEB-INF/view/";

		if("s".equals(category)){	//ユーザー種類が生徒だった場合の処理

			String status = request.getParameter("status");

			if("confirmation".equals(status)){	//学生データ確認だった場合の処理
				String work = request.getParameter("student_id");

				int student_id = 0;

				if(work.matches("^[\\d]{7}$")){	//入力された内容が指定された通りの場合
					student_id = Integer.parseInt(work);
				}

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
					ArrayList<Course> courseList = CourseDAO.getCourseList();
					request.setAttribute("courseList", courseList);

					Student student = StudentDAO.searchStudent(student_id);
					request.setAttribute("student", student);

					session.setAttribute("student_id", student_id);

					view += "studentDataRegistration.jsp";	//学生データ登録ページ
				}
			}else if("registration".equals(status)){	//学生データ登録だった場合の処理
				String name = request.getParameter("name");

				String course_id = request.getParameter("course");

				int school_year = Integer.parseInt(request.getParameter("school_year"));
				int year = CourseDAO.getYear(course_id);

				if(school_year > year){	//選択された学年が卒業年次を越えていた場合
					school_year = year;
				}

				String mail_address = request.getParameter("mail_address");

				Account account = (Account)session.getAttribute("account");
				session.removeAttribute("account");

				session.removeAttribute("category");

				int row = AccountDAO.addUser(account);

				if(row >= 1){	//アカウント登録に成功した場合
					int student_id = (int)session.getAttribute("student_id");
					session.removeAttribute("student_id");

					String user_id = account.getUser_id();

					Student student = new Student(student_id, name, mail_address, school_year, course_id, user_id);
					row = StudentDAO.addStudent(student);

					if(row >= 1){	//学生データ登録に成功した場合
						view += "accountRegistrationCompletion.jsp";	//アカウント登録完了ページ
					}else{	//学生データ登録に失敗した場合
						row = StudentDAO.dropStudent(student_id);
						row = AccountDAO.dropAccount(user_id);

						view += "accountRegistrationError.jsp";	//アカウント登録失敗ページ
					}
				}else{	//アカウント登録に失敗した場合
					view += "accountRegistrationError.jsp";	//アカウント登録失敗ページ
				}
			}else{	//それ以外の場合
				view += "top.jsp";	//トップページ
			}
		}else if("t".equals(category)){	//ユーザー種類が教師だった場合の処理
			String name = request.getParameter("name");

			String belongs_id = request.getParameter("belongs");

			String[] charge_class = request.getParameterValues("charge_class");
			int length = charge_class.length;
			ArrayList<String> course_idList = new ArrayList<String>();
			ArrayList<Integer> school_yearList = new ArrayList<Integer>();
			boolean charge_classFlg = false;

			for(int i = 0; i < length; i++){
				String c_c = charge_class[i];

				if("-".equals(c_c)){	//担当クラスがない場合はこちらを選んだら
					charge_classFlg = true;
				}else{	//それ以外の場合
					int idx = c_c.length();
					course_idList.add(c_c.substring(0, idx - 1));
					school_yearList.add(Integer.parseInt(c_c.substring(idx - 1)));
				}
			}

			String mail_address = request.getParameter("mail_address");

			Account account = (Account)session.getAttribute("account");
			session.removeAttribute("account");

			session.removeAttribute("category");

			int row = AccountDAO.addUser(account);

			if(row >= 1){	//アカウント登録が成功した場合
				String user_id = account.getUser_id();

				Teacher teacher = new Teacher(0, name, belongs_id, mail_address, false, user_id);
				row = TeacherDAO.addTeacher(teacher);

				if(row >= 1){	//教師データ登録に成功した場合
					int teacher_id = TeacherDAO.searchTeacher_Id(user_id);

					if(charge_classFlg){	//担当クラスがない場合はこちらが選ばれていた場合
						length--;
					}

					row = 0;

					for(int i = 0; i < length; i++){
						row += Charge_ClassDAO.addCharge_Class(new Charge_Class(teacher_id, course_idList.get(i), school_yearList.get(i)));
					}

					if(row == length){	//担当クラスデータ登録に成功した場合
						view += "accountRegistrationCompletion.jsp";	//アカウント登録完了ページ
					}else{	//登録失敗の場合
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
		}else{
			session.removeAttribute("account");
			session.removeAttribute("category");

			view += "top.jsp";	//トップページ
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}