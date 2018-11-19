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
import dao.TeacherDAO;
import dto.Teacher;

/**
 * Servlet implementation class AccountManagementServlet
 * アカウント管理の生徒削除・教師削除・システム管理者権限変更を行うためのサーブレット
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

		String view = "WEB-INF/view/";

		if("account_management".equals(status)){	//アカウント管理の場合
			view += "accountManagement.jsp";	//アカウント管理ページ
		}else if("".equals(status)){	//の場合
			view += ".jsp";	//ページ
		}else if("".equals(status)){	//の場合
			view += ".jsp";	//ページ
		}else if("authority_change".equals(status)){	//権限変更の場合
			int myTeacher_id = (int)session.getAttribute("teacher_id");

			ArrayList<Teacher> list = TeacherDAO.getTeachers(myTeacher_id);

			session.setAttribute("list", list);

			view += "authority_change.jsp";	//システム管理者権限ページ
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

		String view = "WEB-INF/view/";

		if("".equals(status)){	//の場合

		}else if("authority_change".equals(status)){	//権限変更の場合
			ArrayList<Teacher> list = Cast.autoCast(session.getAttribute("list"));

			int row = 0;

			for(Teacher teacher : list){
				int teacher_id = teacher.getTeacher_id();
				boolean admin_flg = teacher.isAdmin_flg();

				boolean flg = Boolean.valueOf(request.getParameter("" + teacher_id));

				if(admin_flg != flg){	//確定前と確定後の権限の値が異なる場合の処理
					row += TeacherDAO.changeAuthority(teacher_id, flg);
				}else{
					row++;
				}
			}

			if(row == list.size()){	//変更処理完了の場合
				view += "accountManagement.jsp";	//アカウント管理ページ
			}else{
				view += "authority_change.jsp";	//システム管理者権限ページ
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}