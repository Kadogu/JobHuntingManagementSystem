package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bin.Login;
import dao.StudentDAO;
import dao.TeacherDAO;

/**
 * Servlet implementation class TopServlet
 * トップページを表示する最初のサーブレット
 */
@WebServlet("/Top")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopServlet() {
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
		String user_id = request.getParameter("user_id");
		String pw = request.getParameter("pw");

		boolean flg = Login.login(user_id, pw); //ログイン成功か失敗かを判断

		HttpSession session = request.getSession();
		String view = "WEB-INF/view/";

		if(flg){	//ログイン成功の場合
			int student_id = StudentDAO.searchUser_ID(user_id);

			if(student_id != 0){	//学生データと連携していた場合
				session.setAttribute("category", "s");
				session.setAttribute("student_id", student_id);
				view += "mainStudent.jsp";
			}else{	//学生データと連携していない場合
				int teacher_id = TeacherDAO.searchTeacher_id(user_id);

				if(teacher_id != 0){	//教師データと連携していた場合
					session.setAttribute("category", "t");
					session.setAttribute("teacher_id", teacher_id);
					view += "mainTeacher.jsp";
				}else{

				}
			}
		}else{	//ログイン失敗の場合
			String error = "IDまたはPWが違います";
			request.setAttribute("error", error);
			view += "top.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}