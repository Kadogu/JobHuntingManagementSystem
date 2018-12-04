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
import dao.BelongsDAO;
import dao.CourseDAO;
import dto.Account;
import dto.Belongs;
import dto.Course;

/**
 * Servlet implementation class AccountCreateServlet
 * 新規アカウント作成に用いるサーブレット
 */
@WebServlet("/AccountCreate")
public class AccountCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "WEB-INF/view/accountCreate.jsp";	//新規アカウント作成ページ
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String user_id = request.getParameter("user_id");
		String pw = AccountDAO.login(user_id);	//IDが使用済かどうか確認

		String view = "WEB-INF/view/";

		if(pw == null){	//IDが使用済でない場合
			HttpSession session = request.getSession(false);

			pw = request.getParameter("pw");
			String hashPW = AccountDAO.hashPW(pw);

			String category = request.getParameter("user");
			session.setAttribute("category", category);

			Account account = new Account(user_id, hashPW);
			session.setAttribute("account", account);

			if("s".equals(category)){	//ユーザーが学生の場合
				view += "studentDataConfirmation.jsp"; //学生データ確認ページ
			}else if("t".equals(category)){	//ユーザーが教師の場合
				ArrayList<Belongs> belongsList = BelongsDAO.getBelongsList();
				request.setAttribute("belongsList", belongsList);

				ArrayList<Course> courseList = CourseDAO.getCourseList();
				request.setAttribute("courseList", courseList);

				view += "teacherDataRegistration.jsp"; //教師データ登録ページ
			}else{	//ユーザーの種類が設定外の場合
				session.removeAttribute("category");
				session.removeAttribute("account");

				view += "top.jsp";	//トップページ
			}
		}else{	//IDが使用されている場合
			String error = "IDが使用されています";
			request.setAttribute("error", error);

			view += "accountCreate.jsp";	//新規アカウント作成ページ
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request,response);
	}
}