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

import dao.BelongsDAO;
import dao.Charge_ClassDAO;
import dao.CourseDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import dto.Belongs;
import dto.Charge_Class;
import dto.Course;
import dto.Student;
import dto.Teacher;

/**
 * Servlet implementation class RegistrationContentsChangeServlet
 */
@WebServlet("/RegistrationContentsChange")
public class RegistrationContentsChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationContentsChangeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		String category = (String)session.getAttribute("category");

		String view = "WEB-INF/view/";

		if("s".equals(category)){	//ユーザー種類が生徒の場合
			ArrayList<Course> courseList = CourseDAO.getCourseList();
			request.setAttribute("courseList", courseList);

			int student_id = (int)session.getAttribute("student_id");
			Student student = StudentDAO.searchStudent(student_id);
			request.setAttribute("student", student);

			view += "studentDataChange.jsp";	//登録内容変更ページ(生徒版)
		}else if("t".equals(category)){	//ユーザー種類が教師の場合
			ArrayList<Belongs> belongsList = BelongsDAO.getBelongsList();
			request.setAttribute("belongsList", belongsList);

			ArrayList<Course> courseList = CourseDAO.getCourseList();
			request.setAttribute("courseList", courseList);

			int teacher_id = (int)session.getAttribute("teacher_id");

			Teacher teacher = TeacherDAO.getTeacher(teacher_id);
			request.setAttribute("teacher", teacher);

			ArrayList<Charge_Class> charge_classList = Charge_ClassDAO.getCharge_ClassList(teacher_id);

			ArrayList<String> list = new ArrayList<String>();
			for(Charge_Class charge_class : charge_classList){
				list.add(charge_class.getCourse_id() + charge_class.getSchool_year());
			}
			request.setAttribute("charge_classList", list);

			view += "teacherDataChange.jsp";	//登録内容変更ページ(教師版)
		}else{	//それ以外の場合
			view += "top.jsp";	//トップページ
		}

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

		if("s".equals(category)){	//ユーザー種類が生徒の場合
			int student_id = (int)session.getAttribute("student_id");

			String name = request.getParameter("name");

			String course_id = request.getParameter("course");

			int school_year = Integer.parseInt(request.getParameter("school_year"));
			int year = CourseDAO.getYear(course_id);

			if(school_year > year){	//選択された学年が卒業年次を越えていた場合
				school_year = year;
			}

			String mail_address = request.getParameter("mail_address");

			Student student = new Student(student_id, name, mail_address, school_year, course_id, null);

			int row = StudentDAO.updateStudent(student);

			if(row >= 1){	//登録内容変更に完了した場合
				view += "registrationContentsChangeCompletion.jsp";	//登録内容変更完了ページ
			}else{	//登録内容変更に失敗した場合
				view += "error.jsp";	//エラーページ
			}
		}else if("t".equals(category)){	//ユーザー種類が教師の場合
			int teacher_id = (int)session.getAttribute("teacher_id");

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

			Teacher teacher = new Teacher(teacher_id, name, belongs_id, mail_address, false, null);

			int row = TeacherDAO.updateTeacher(teacher);

			if(row >= 1){	//登録内容変更に完了した場合
				row = Charge_ClassDAO.dropCharge_Class(teacher_id);

				if(row >= 1){	//担当クラスの削除に成功した場合
					if(charge_classFlg){	//担当クラスがない場合はこちらが選ばれていた場合
						length--;
					}

					row = 0;

					for(int i = 0; i < length; i++){
						row += Charge_ClassDAO.addCharge_Class(new Charge_Class(teacher_id, course_idList.get(i), school_yearList.get(i)));
					}

					if(row == length){	//担当クラスデータ登録に成功した場合
						view += "registrationContentsChangeCompletion.jsp";	//登録内容変更完了ページ
					}else{	//登録失敗の場合
						view += "error.jsp";	//エラーページ
					}
				}else{	//担当クラスの削除に失敗した場合
					view += "error.jsp";	//エラーページ
				}
			}else{	//登録内容変更に失敗した場合
				view += "error.jsp";	//エラーページ
			}
		}else{	//それ以外の場合
			view += "top.jsp";	//トップページ
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}