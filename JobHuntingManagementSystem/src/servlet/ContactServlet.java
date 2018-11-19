package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bin.SendMail;
import dao.Contact_ItemDAO;

/**
 * Servlet implementation class ContactServlet
 * お問い合わせメールを送信するサーブレット
 */
@WebServlet("/Contact")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "WEB-INF/view/contact.jsp";	//お問い合わせページ
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int contact_item_id = Integer.parseInt(request.getParameter("contact_item"));
		String item_name = Contact_ItemDAO.getItem_name(contact_item_id);

		String title = "お問い合わせ(" + item_name + ")";

		String detail = request.getParameter("detail");

		/*以下は消す奴*/
		String name = "システム管理者名";
		String mail_address = "mj.se.oy@gmail.com";

		boolean flg = SendMail.sendMail(title, detail, mail_address, name);

		String view = "WEB-INF/view/sendMail";

		if(flg){	//メール送信に成功した場合
			view += "Completion.jsp";	//メール送信完了ページ
		}else{	//メール送信に失敗した場合
			view += "Error.jsp";	//メール送信失敗ページ
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}