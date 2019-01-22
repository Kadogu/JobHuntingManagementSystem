package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainServlet
 * メインページに画面遷移するサーブレット
 */
@WebServlet("/Main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
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

		if("s".equals(category)){	//ユーザーが生徒の場合
			view += "mainStudent.jsp";	//メインページ(生徒版)
		}else if("t".equals(category)){	//ユーザーが教師の場合
			view += "mainTeacher.jsp";	//メインページ(教師版)
		}else{	//それ以外の場合
			session.invalidate();
			view += "top.jsp";	//トップページ
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if(session != null){	//セッションがあった場合
			session.invalidate();
		}

		String view = "./Top";	//トップページ
		response.sendRedirect(view);
	}
}