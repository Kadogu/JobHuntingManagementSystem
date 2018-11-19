package servlet;

import static bin.SendMail.*;
import static dto.MailAccount.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bin.CreateID;
import bin.DateConversion;
import dao.Charge_ClassDAO;
import dao.CompanyDAO;
import dao.DestinationDAO;
import dao.Document_ApplicationDAO;
import dao.Document_ContentsDAO;
import dao.Document_Other_ContentsDAO;
import dao.StudentDAO;
import dto.Company;
import dto.Destination;
import dto.Document_Application;
import dto.Student;

/**
 * Servlet implementation class ApplicationFormServlet
 * 書類申請届出書を作成・閲覧をするためのサーブレット
 */
@WebServlet("/ApplicationForm")
public class ApplicationFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		String[] documents = Document_ContentsDAO.getDocuments();
		request.setAttribute("documents", documents);

		String view = "WEB-INF/view/application";

		String status = request.getParameter("status");

		if("confirmation".equals(status)){	//届出書確認の場合
			boolean bring_mailing = Boolean.valueOf(request.getParameter("bring"));
			request.setAttribute("bring_mailing", bring_mailing);

			view += "Confirmation.jsp";	//確認ページ
		}else{	//それ以外の場合
			int student_id = (int)session.getAttribute("student_id");

			if("creating".equals(status)){	//届出書作成の場合
				view += "Create.jsp";	//作成ページ
			}else if("reading".equals(status)){	//届出書閲覧の場合
				ArrayList<Document_Application> list = Document_ApplicationDAO.getDocument_Applications(student_id);
				request.setAttribute("list", list);

				view += "Reading.jsp";	//閲覧ページ
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

		String view = "WEB-INF/view/application";

		if("creating".equals(status)){	//届出書作成の場合
			HttpSession session = request.getSession(false);

			Document_Application document_application = new Document_Application();

			String document_application_id = CreateID.createID(8);

			while(Document_ApplicationDAO.searchDocument_Application_Id(document_application_id) != null){
				document_application_id = CreateID.createID(8);
			}

			document_application.setDocument_application_id(document_application_id);

			LocalDate application_date = LocalDate.now();
			document_application.setApplication_date(application_date);

			int student_id = (int)session.getAttribute("student_id");
			document_application.setStudent_id(student_id);

			Company company = (Company)session.getAttribute("company");
			document_application.setCompany_id(company.getCompany_id());

			boolean bring_mailing = Boolean.valueOf(request.getParameter("bring_mailing"));
			document_application.setBring_mailing(bring_mailing);

			LocalDate deadline = DateConversion.input_dateConversion(request.getParameter("deadline"));
			document_application.setDeadline(deadline);

			int[] documents_flg = new int[6];

			for(int i = 0; i < documents_flg.length; i++){
				String work = request.getParameter("" + i);
				if(work == null){
					documents_flg[i] = 0;
				}else{
					documents_flg[i] = 1;
				}
			}

			document_application.setDocuments_flg(documents_flg);

			if(!bring_mailing){	//郵送の場合
				int destination_num = Integer.parseInt(request.getParameter("destination"));
				document_application.setDestination(destination_num);
			}

			Student student = StudentDAO.searchStudent(student_id);
			int teacher_id = Charge_ClassDAO.searchTeacher_id(student.getCourse_id(), student.getSchool_year());
			document_application.setTeacher_id(teacher_id);

			int row = Document_ApplicationDAO.createApplication(document_application);

			session.removeAttribute("company");

			if(row >= 1){	//作成完了した場合
				boolean flg = true;	//送付先データ及びその他の内容データ格納に成功したか失敗したかどうかのフラグ

				if(!bring_mailing){	//郵送の場合
					int destination_num = document_application.getDestination();

					if(destination_num == 1 || destination_num == 2){	//会社＋個人名入か別の場所の場合
						Destination destination = new Destination();

						if(destination_num == 2){	//別の場所の場合
							String postal_code1 = request.getParameter("postal_code1");
							String postal_code2 = request.getParameter("postal_code2");
							destination.setPostal_code(postal_code1 + "-" + postal_code2);

							destination.setAddress(request.getParameter("address"));
						}

						destination.setDocument_application_id(document_application_id);

						destination.setIndividual(request.getParameter("individual"));

						row = DestinationDAO.addDestination(destination);

						if(row == 0){	//送付先データの格納に失敗した場合
							flg = false;
						}
					}
				}

				if(documents_flg[documents_flg.length - 1] == 1){	//その他にチェックが入っていた場合
					String other_contents = request.getParameter("other_contents");
					row = Document_Other_ContentsDAO.addDocument_Other_Contents(document_application_id, other_contents);

					if(row == 0){	//その他の内容データの格納に失敗した場合
						flg = false;
					}
				}

				if(flg){	//送付先データ及びその他の内容データ格納に成功した場合
					String text = "書類";

					if(bring_mailing){	//持参の場合
						text += "の準備ができた";
					}else{	//郵送の場合
						text += "が郵送された";
					}

					request.setAttribute("text", text);

					view += "Completion.jsp";
				}else{	//送付先データ及びその他の内容データ格納に失敗した場合
					/*送付先データ削除処理*/
					/*その他の内容データ削除処理*/
					/*届出書データ削除処理*/
					view += "Error.jsp";
				}
			}else{	//作成失敗した場合
				view += "Error.jsp";
			}
		}else if("notification".equals(status)){	//届出書確認後の通知の場合
			HashMap<Integer, Student> studentMap = new HashMap<Integer, Student>();
			HashMap<String, String> companyMap = new HashMap<String, String>();

			boolean bring_mailing = Boolean.valueOf(request.getParameter("bring_mailing"));
			String[] document_application_idList = request.getParameterValues("document_application_id");
			int cnt = 0;

			for(String document_application_id : document_application_idList){
				Document_Application document_application = Document_ApplicationDAO.getDocument_Application(document_application_id);

				if(document_application != null){	//届出書のデータが返ってきた場合
					int student_id = document_application.getStudent_id();
					Student student = studentMap.get(student_id);

					if(student == null){	//studentMapに登録されていない場合
						student = StudentDAO.searchStudent(student_id);
						studentMap.put(student_id, student);
					}

					String company_id = document_application.getCompany_id();
					String company_name = companyMap.get(company_id);

					if(company_name == null){	//companyMapに登録されていない場合
						company_name = CompanyDAO.getCompany_name(company_id);
					}

					int issue_fee = document_application.getIssue_fee();

					if(student != null && company_name != null){	//学生データと会社名が取得できた場合
						String title = "申請していた届出書の";

						if(bring_mailing){	//持参の場合
							title += "準備";
						}else{	//郵送の場合
							title += "郵送";
						}

						title += "完了通知";

						StringBuilder sb = new StringBuilder();

						sb.append(getAccountName() + "です。\n\n");

						sb.append("あなたが申請していた" + company_name);

						if(bring_mailing){
							sb.append("に提出する書類の準備が完了しましたので、担任の先生に取りに行って下さい。\n\n");
						}else{
							sb.append("への書類の郵送が完了しました。\n\n");
						}

						sb.append("発行手数料は" + issue_fee + "円です。\n\n");

						sb.append("忘れずにお支払い下さい。");

						String message = sb.toString();

						String receiverMail_Address = student.getMail_address();

						String receiverName = student.getName();

						boolean flg = sendMail(title, message, receiverMail_Address, receiverName);

						if(flg){	//メールの送信に成功した場合
							cnt += Document_ApplicationDAO.updateIssue_flg(document_application_id);
						}
					}
				}
			}
			if(cnt == document_application_idList.length){
				view += "NotificationCompletion.jsp";	//通知完了ページ
			}else{
				view += "";	//通知失敗ページ
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}