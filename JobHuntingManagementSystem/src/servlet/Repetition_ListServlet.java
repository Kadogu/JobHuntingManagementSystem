package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import dao.CourseDAO;
import dao.DestinationDAO;
import dao.Document_ApplicationDAO;
import dao.Document_Other_ContentsDAO;
import dao.Repetition_ListDAO;
import dao.StudentDAO;
import dto.Course;
import dto.Document_Application;
import dto.Student;

/**
 * Servlet implementation class Repetition_ListServlet
 * 留年リスト追加・確認・削除、生徒自動進級処理を行うサーブレット
 */
@WebServlet("/Repetition_List")
public class Repetition_ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Repetition_ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");

		String view = "WEB-INF/view/";

		if("add".equals(status)){	//留年リスト追加の場合
			view += "repetition_listAdd.jsp";	//留年リスト追加ページ
		}else if("addConfirmation".equals(status)){	//留年リスト追加確認の場合
			HttpSession session = request.getSession(false);

			int student_id = (int)session.getAttribute("student_id");

			int row = Repetition_ListDAO.addStudent_id(student_id);

			if(row >= 1){	//留年リスト追加完了の場合
				view += "repetition_listAddCompletion.jsp";	//留年リスト追加完了ページ
			}else{	//留年リスト追加失敗の場合
				view += "error.jsp";	//エラーページ
			}
		}else if("confirmation".equals(status)){	//留年リスト確認の場合
			view += "repetition_listConfirmation.jsp";	//留年リスト確認ページ
		}else if("pass".equals(status)){	//進級の場合
			ArrayList<Integer> repetition_list = Repetition_ListDAO.getRepetition_List();

			ArrayList<Course> courseList = CourseDAO.getCourseList();

			boolean flg = true;
			int row = 0;

			for(Course course : courseList){
				String course_id = course.getCourse_id();
				int year = course.getYear();

				for(int school_year = year; school_year >= 1; school_year--){
					ArrayList<Integer> studentList = StudentDAO.getStudentList(course_id, school_year);

					for(int student_id : studentList){
						if(repetition_list.indexOf(student_id) == -1){	//留年リストに載っていない学生の場合
							if(school_year == year){	//卒業年次の場合
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

								row = StudentDAO.graduateSchool(student_id);

								if(row == 0){	//卒業処理に失敗した場合
									flg = false;
								}

								row = AccountDAO.dropAccount(user_id);
							}else{	//卒業年次ではない場合
								row = StudentDAO.pass(student_id, school_year + 1);

								if(row == 0){	//進級処理に失敗した場合
									flg = false;
								}
							}
						}else{	//留年リストに載っている学生の場合
							continue;
						}
					}
				}
			}

			if(flg){	//生徒の自動進級処理が完了した場合
				ServletContext sc = getServletContext();

				LocalDate now = LocalDate.now();
				int y = now.getYear();
				LocalDate year = LocalDate.of(++y, 3, 1);
				sc.setAttribute("year", year);

				row = Repetition_ListDAO.deleteRepetition_List();

				if(row == 0){	//留年リストを空にする処理に失敗した場合
					view += "error.jsp";	//エラーページ
				}

				view += "passCompletion.jsp";	//学生進級完了ページ
			}else{	//生徒の自動進級処理が失敗した場合
				view += "error.jsp";	//エラーページ
			}
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

		String status = request.getParameter("status");

		String view = "WEB-INF/view/";

		if("add".equals(status)){	//留年リスト追加の場合
			int student_id = Integer.parseInt(request.getParameter("student_id"));

			Student student = StudentDAO.searchStudent(student_id);

			if(student.getStudent_id() == student_id){	//学生が見つかった場合
				session.setAttribute("student_id", student_id);

				request.setAttribute("student", student);

				view += "repetition_listAddConfirmation.jsp";	//留年リスト追加確認ページ
			}else{	//学生が見つからない場合
				String error = "学生が見つかりません";
				request.setAttribute("error", error);

				view += "repetition_listAdd.jsp";	//留年リスト追加ページ
			}
		}else if("delete".equals(status)){	//留年リスト削除の場合
			String[] deleteList = request.getParameterValues("delete");

			if(deleteList != null){	//1人以上選択されていた場合
				int row = 0;

				for(String work : deleteList){
					int student_id = Integer.parseInt(work);
					row += Repetition_ListDAO.deleteRepetition_List(student_id);
				}

				if(row == deleteList.length){	//留年リストの削除が完了した場合
					String delete = "削除しました";
					request.setAttribute("delete", delete);

					view += "repetition_listConfirmation.jsp";	//留年リスト確認ページ
				}else{	//留年リストの削除に失敗した場合
					view += "error.jsp";	//エラーページ
				}
			}else{	//誰も選択されていない場合
				view += "repetition_listConfirmation.jsp";	//留年リスト確認ページ
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}