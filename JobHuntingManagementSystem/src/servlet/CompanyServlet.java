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

import bin.CreateID;
import dao.CompanyDAO;
import dto.Company;

/**
 * Servlet implementation class CompanyServlet
 * 会社追加・編集・確認・選択に使用するサーブレット
 */
@WebServlet("/Company")
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String status = request.getParameter("status");

		String view = "WEB-INF/view/";

		if("add".equals(status)){	//会社追加の場合
			view += "companyAdd.jsp"; //会社追加ページ
		}else{	//その他の場合
			String search = request.getParameter("search");

			if(search == null){	//検索値がなかった場合の処理
				search = "";
			}else{	//検索値があった場合の処理
				request.setAttribute("search", search);
			}

			ArrayList<Company> companies = CompanyDAO.getCompanies(search);
			request.setAttribute("companies", companies);

			if("edit".equals(status)){	//会社編集の場合
				view += "companyEdit.jsp"; //会社編集ページ
			}else if("confirmation".equals(status)){	//会社確認の場合
				view += "companyConfirmation.jsp"; //会社確認ページ
			}else if("choice".equals(status)){	//会社選択の場合
				HttpSession session = request.getSession(false);

				String use = request.getParameter("use");
				session.setAttribute("use", use);

				view += "companyChoice.jsp"; //会社確認ページ
			}else{
				view += "top.jsp";	//トップページ
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

		String status = request.getParameter("status");

		String view = "WEB-INF/view/";

		if("add".equals(status)){	//会社追加の場合
			String company_name = request.getParameter("company_name");

			String postal_code1 = request.getParameter("postal_code1");
			String postal_code2 = request.getParameter("postal_code2");
			String postal_code = postal_code1 + "-" + postal_code2;

			String address = request.getParameter("address");

			String phone_number1 = request.getParameter("phone_number1");
			String phone_number2 = request.getParameter("phone_number2");
			String phone_number3 = request.getParameter("phone_number3");
			String phone_number = phone_number1 + "-" + phone_number2 + "-" + phone_number3;

			String company_id = CreateID.createID(8);
			while(CompanyDAO.searchCompany_Id(company_id) != null){
				company_id = CreateID.createID(8);
			}

			int row = CompanyDAO.addCompany(new Company(company_id, company_name, postal_code, address, phone_number));
			if(row >= 1){	//追加完了の場合
				view += "companyAddCompletion.jsp";	//追加完了ページ
			}else{	//追加失敗の場合
				view += "error.jsp";	//エラーページ
			}
		}else if("correction".equals(status)){	//会社修正の場合
			HttpSession session = request.getSession(false);

			Company company = (Company)session.getAttribute("company");

			String company_name = request.getParameter("company_name");
			company.setCompany_name(company_name);

			String postal_code1 = request.getParameter("postal_code1");
			String postal_code2 = request.getParameter("postal_code2");
			String postal_code = postal_code1 + "-" + postal_code2;
			company.setPostal_code(postal_code);

			String address = request.getParameter("address");
			company.setAddress(address);

			String phone_number1 = request.getParameter("phone_number1");
			String phone_number2 = request.getParameter("phone_number2");
			String phone_number3 = request.getParameter("phone_number3");
			String phone_number = phone_number1 + "-" + phone_number2 + "-" + phone_number3;
			company.setPhone_number(phone_number);

			int row = CompanyDAO.updateCompany(company);

			if(row >= 1){	//修正成功の場合
				view += "companyCorrectionCompletion.jsp";	//修正完了ページ
			}else{	//修正失敗の場合
				view += "エラー";	//エラーページ
			}
		}else{	//それ以外の場合
			String company_id = request.getParameter("company");
			Company company = CompanyDAO.searchCompany(company_id);

			if(company != null){	//会社データが取得できた場合
				HttpSession session = request.getSession(false);
				session.setAttribute("company", company);

				if("edit".equals(status)){	//会社編集の場合
					view += "companyCorrection.jsp";	//会社修正ページ
				}else if("choice".equals(status)){	//会社選択の場合
					view += "companyChoiceConfirmation.jsp";	//会社選択確認ページ
				}else{	//それ以外の場合
					view += "top.jsp";	//トップページ
				}
			}else{	//取得できない場合
				view += "error.jsp";	//エラーページ
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}