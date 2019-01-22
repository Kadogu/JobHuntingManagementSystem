package servlet;

import static bin.CreatePassCode.*;
import static bin.SendMail.*;
import static dto.MailAccount.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import dao.StudentDAO;
import dao.TeacherDAO;

/**
 * Servlet implementation class PWChangeServlet
 * PW変更の流れに使用するサーブレット
 */
@WebServlet("/PWChange")
public class PWChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PWChangeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "WEB-INF/view/accountConfirmation.jsp";	//アカウント確認ページ
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		String status = request.getParameter("status");

		String view = "WEB-INF/view/";

		if("account_confirmation".equals(status)){	//アカウント確認の場合
			String category = request.getParameter("category");

			String name = request.getParameter("name");
			String mail_address = request.getParameter("mail_address");

			String user_id = null;

			if("s".equals(category)){	//ユーザー種類が生徒の場合
				user_id = StudentDAO.searchUser_ID(name, mail_address);

			}else if("t".equals(category)){	//ユーザー種類が教師の場合
				user_id = TeacherDAO.searchUser_ID(name, mail_address);

			}

			if("s".equals(category) || "t".equals(category)){	//ユーザー種類が適正に選ばれていた場合
				if(user_id != null){	//アカウント確認ができた場合
					session.setAttribute("name", name);
					session.setAttribute("mail_address", mail_address);
					session.setAttribute("user_id", user_id);

					view += "idConfirmation.jsp";	//ID確認ページ
				}else{	//アカウント確認ができない場合
					String error = "登録内容と一致しません";
					request.setAttribute("error", error);

					view += "accountConfirmation.jsp";	//ページ
				}
			}else{	//ユーザー種類が適正に選ばれていない場合
				session.invalidate();
				view += "error.jsp";	//エラーページ
			}
		}else if("idConfirmation".equals(status)){	//ID確認の場合
			String title = "PW変更用のパスコード";

			StringBuilder sb = new StringBuilder();

			sb.append(getAccountName() + "です。\n\n");

			String passcode = createPassCode();
			sb.append("PW変更用のパスコード：" + passcode + "\n\n");

			sb.append("上記のパスコードを入力してPW変更を行って下さい。\n\n");

			sb.append(getFooter());

			String message = sb.toString();

			String receiverMail_Address = (String)session.getAttribute("mail_address");

			String receiverName = (String)session.getAttribute("name");

			boolean flg = sendMail(title, message, receiverMail_Address, receiverName);

			if(flg){	//メール送信成功の場合
				session.setAttribute("passcode", passcode);

				view += "passcodeInput.jsp";	//パスコード入力ページ
			}else{	//メール送信失敗の場合
				session.invalidate();
				view += "error.jsp";	//エラーページ
			}
		}else if("passcodeInput".equals(status)){	//パスコード入力の場合
			String passcode = (String)session.getAttribute("passcode");
			String passcodeInput = request.getParameter("passcode");

			if(passcode.equals(passcodeInput)){	//入力されたパスコードと作成されたパスコードが一致していた場合
				view += "pwChange.jsp";	//PW変更ページ
			}else{	//入力されたパスコードが作成されたパスコードと違う場合
				String error = "パスコードが違います";
				request.setAttribute("error", error);

				view += "passcodeInput.jsp";	//パスコード入力ページ
			}
		}else if("pwChange".equals(status)){	//PW変更の場合
			String user_id = (String)session.getAttribute("user_id");

			String pw = request.getParameter("pw");

			String hashPW = AccountDAO.hashPW(pw);

			int row = AccountDAO.changePW(user_id, hashPW);

			if(row >= 1){	//PW変更が完了した場合
				session.invalidate();
				view += "pwChangeCompletion.jsp";	//PW変更完了ページ
			}else{	//PW変更が失敗した場合
				session.invalidate();
				view += "error.jsp";	//エラーページ
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}