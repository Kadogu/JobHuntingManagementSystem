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

import bin.Cast;
import dao.AccountDAO;
import dao.Charge_ClassDAO;
import dao.DestinationDAO;
import dao.Document_ApplicationDAO;
import dao.Document_Other_ContentsDAO;
import dao.Repetition_ListDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import dto.Document_Application;
import dto.Student;
import dto.Teacher;

/**
 * Servlet implementation class AccountManagementServlet
 * アカウント管理の生徒削除・教師削除・システム管理者権限変更・ログ取得を行うためのサーブレット
 */
@WebServlet("/AccountManagement")
public class AccountManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");

		HttpSession session = request.getSession(false);

		int teacher_id = (int)session.getAttribute("teacher_id");
		Teacher teacher = TeacherDAO.getTeacher(teacher_id);

		String view = "WEB-INF/view/";

		if(teacher != null && teacher.isAdmin_flg()){	//正常な遷移の場合
			if("student".equals(status)){	//生徒削除の場合
				ArrayList<Student> studentList = new ArrayList<Student>();

				String search = request.getParameter("search");

				if(search != null && !"".equals(search)){	//検索されていた場合
					int student_id = 0;

					if(search.matches("^[\\d]{1,7}$")){	//検索値が学籍番号の場合
						student_id = Integer.parseInt(search);

						studentList.addAll(StudentDAO.getStudentList(student_id));
					}else{
						studentList.addAll(StudentDAO.getStudentList(search));
					}

					request.setAttribute("search", search);
				}else{	//検索されていない場合
					studentList.addAll(StudentDAO.getStudentList());
				}

				request.setAttribute("studentList", studentList);

				view += "studentDelete.jsp";	//生徒削除ページ
			}else if("teacher".equals(status)){	//教師削除の場合
				int myTeacher_id = (int)session.getAttribute("teacher_id");

				ArrayList<Teacher> teacherList = new ArrayList<Teacher>();

				String search = request.getParameter("search");

				if(search != null && !"".equals(search)){	//検索されていた場合
					teacherList.addAll(TeacherDAO.getTeachers(myTeacher_id, search));

					request.setAttribute("search", search);
				}else{	//検索されていない場合
					teacherList.addAll(TeacherDAO.getTeachers(myTeacher_id));
				}

				request.setAttribute("teacherList", teacherList);

				view += "teacherDelete.jsp";	//教師削除ページ
			}else if("authority_change".equals(status)){	//権限変更の場合
				int myTeacher_id = (int)session.getAttribute("teacher_id");

				ArrayList<Teacher> teachers = TeacherDAO.getTeachers(myTeacher_id);
				session.setAttribute("teachers", teachers);

				view += "authority_change.jsp";	//システム管理者権限ページ
			}else if("log".equals(status)){	//ログ取得の場合
				view += "log.jsp";	//ログページ
			}
		}else{	//不正な遷移の場合
			view += "top.jsp";	//トップページ
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");

		HttpSession session = request.getSession(false);

		int teacher_id = (int)session.getAttribute("teacher_id");
		Teacher teacher = TeacherDAO.getTeacher(teacher_id);

		String view = "WEB-INF/view/";

		if(teacher != null && teacher.isAdmin_flg()){	//正常な遷移の場合
			if("student".equals(status)){	//生徒削除の場合
				String[] student_idList = request.getParameterValues("student_id");

				if(student_idList != null){	//1人以上選択されていた場合
					int row = 0;
					boolean flg = true;

					for(String work : student_idList){
						int student_id = Integer.parseInt(work);

						String user_id = StudentDAO.searchUser_ID(student_id);

						ArrayList<Document_Application> list = Document_ApplicationDAO.getDocument_ApplicationList(student_id);

						for(Document_Application document_application : list){
							String document_application_id = document_application.getDocument_application_id();
							int[] documents_flg = document_application.getDocuments_flg();

							if(documents_flg[5] == 1){	//その他にチェックが入っていた場合
								row = Document_Other_ContentsDAO.dropDocument_Other_Contents(document_application_id);
							}

							int destination = document_application.getDestination();

							if(destination == 1 || destination == 2){	//送付先が会社＋個人名入または別の場所の場合
								row = DestinationDAO.dropDestination(document_application_id);
							}
						}

						row = Document_ApplicationDAO.dropDocument_Application(student_id);

						row = Repetition_ListDAO.deleteRepetition_List(student_id);

						row = StudentDAO.graduateSchool(student_id);

						if(row == 0){	//削除処理に失敗した場合
							flg = false;
						}

						row = AccountDAO.dropAccount(user_id);
					}

					if(flg){	//削除処理成功の場合
						String delete = "削除しました";
						request.setAttribute("delete", delete);

						ArrayList<Student> studentList = StudentDAO.getStudentList();
						request.setAttribute("studentList", studentList);

						view += "studentDelete.jsp";	//生徒削除ページ
					}else{	//削除処理失敗の場合
						view += "error.jsp";	//エラーページ
					}
				}else{	//1人も選択されていない場合
					ArrayList<Student> studentList = StudentDAO.getStudentList();
					request.setAttribute("studentList", studentList);

					view += "studentDelete.jsp";	//生徒削除ページ
				}
			}else if("teacher".equals(status)){	//教師削除の場合
				String[] teacher_idList = request.getParameterValues("teacher_id");

				if(teacher_idList != null){	//1人以上選択されていた場合
					int row = 0;
					boolean flg = true;

					for(String work : teacher_idList){
						teacher_id = Integer.parseInt(work);

						row = Charge_ClassDAO.dropCharge_Class(teacher_id);

						String user_id = TeacherDAO.searchUser_ID(teacher_id);

						row = TeacherDAO.dropTeacher(teacher_id);

						if(row == 0){	//教師削除処理に失敗した場合
							flg = false;
						}

						row = AccountDAO.dropAccount(user_id);
					}

					if(flg){	//教師削除に成功した場合
						String delete = "削除しました";
						request.setAttribute("delete", delete);

						int myTeacher_id = (int)session.getAttribute("teacher_id");

						ArrayList<Teacher> teacherList = TeacherDAO.getTeachers(myTeacher_id);
						request.setAttribute("teacherList", teacherList);

						view += "teacherDelete.jsp";	//教師削除ページ
					}else{	//教師削除に失敗した場合
						view += "error.jsp";	//エラーページ
					}
				}else{	//1人も選択されていない場合
					int myTeacher_id = (int)session.getAttribute("teacher_id");

					ArrayList<Teacher> teacherList = TeacherDAO.getTeachers(myTeacher_id);
					request.setAttribute("teacherList", teacherList);

					view += "teacherDelete.jsp";	//教師削除ページ
				}
			}else if("authority_change".equals(status)){	//権限変更の場合
				ArrayList<Teacher> teachers = Cast.autoCast(session.getAttribute("teachers"));

				int row = 0;

				for(Teacher t : teachers){
					teacher_id = t.getTeacher_id();
					boolean admin_flg = t.isAdmin_flg();

					boolean flg = Boolean.valueOf(request.getParameter("" + teacher_id));

					if(admin_flg != flg){	//確定前と確定後の権限の値が異なる場合の処理
						row += TeacherDAO.changeAuthority(teacher_id, flg);
					}else{
						row++;
					}
				}

				if(row == teachers.size()){	//変更処理完了の場合
					session.removeAttribute("teachers");

					view += "mainTeacher.jsp";	//メインページ(教師版)
				}else{
					view += "authority_change.jsp";	//システム管理者権限ページ
				}
			}
		}else{	//不正な遷移の場合
			view += "top.jsp";	//トップページ
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}