package servlet;

import static bin.SendMail.*;
import static dto.MailAccount.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bin.CreateID;
import bin.CreatePDF;
import bin.DateConversion;
import dao.CompanyDAO;
import dao.Contents_TestDAO;
import dao.OccupationsDAO;
import dao.PDFDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import dao.Type_Of_IndustryDAO;
import dto.Company;
import dto.Contents_Test;
import dto.Occupations;
import dto.PDF;
import dto.Student;
import dto.Teacher;
import dto.Type_Of_Industry;

/**
 * Servlet implementation class ReportServlet
 * 報告書の作成・確認・閲覧に使用するサーブレット
 */
@WebServlet("/Report")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		String status = request.getParameter("status");

		String view = "WEB-INF/view/";

		if("creating".equals(status)){	//報告書作成の場合
			session.removeAttribute("remake");

			ArrayList<Type_Of_Industry> type_of_industryList = Type_Of_IndustryDAO.getType_Of_IndustryList();
			request.setAttribute("type_of_industryList", type_of_industryList);

			ArrayList<Occupations> occupationsList = OccupationsDAO.getOccupationsList();
			request.setAttribute("occupationsList", occupationsList);

			view += "report1.jsp";	//報告書1/3ページ
		}else if("remake".equals(status)){	//続きから作成の場合
			view += "reportRemake.jsp";	//作成を再開するデータ選択ページ
		}else if("choice".equals(status)){	//報告書確認の場合
			view += "choiceReportCheck.jsp";	//確認したい報告書選択ページ
		}else if("instruction".equals(status)){	//修正指示の場合
			view += "instructReportRemake.jsp";	//修正指示ページ
		}else if("reading".equals(status)){	//報告書閲覧の場合
			String category = (String)session.getAttribute("category");

			if("s".equals(category)){	//ログインしているユーザー種類が生徒の場合
				ArrayList<String> file_nameList = new ArrayList<String>();

				String myreport = request.getParameter("myreport");

				if("myreport".equals(myreport)){	//自分のを見るの場合
					int student_id = (int)session.getAttribute("student_id");

					request.setAttribute("myreport", "myreport");

					file_nameList.addAll(PDFDAO.getMyFile_nameList(student_id));
				}else{	//それ以外の場合
					String search = request.getParameter("search");

					int year = 0;
					String company_name = null;

					if("search".equals(search)){	//検索された場合
						String work = request.getParameter("year");
						if(work != null && !work.equals("")){
							year = Integer.parseInt(work);
						}
						company_name = request.getParameter("company_name");
						if("".equals(company_name)){
							company_name = null;
						}

						request.setAttribute("search", "search");
						request.setAttribute("fy", year);
						request.setAttribute("company_name", company_name);
					}

					if(company_name == null){	//会社名が指定されていない場合
						company_name = "";
					}

					file_nameList.addAll(PDFDAO.getFile_nameList(year, company_name));
				}

				request.setAttribute("file_nameList", file_nameList);

				view += "reportReadingStudent.jsp";	//報告書一覧ページ(生徒版)
			}else if("t".equals(category)){	//ログインしているユーザー種類が教師の場合
				String search = request.getParameter("search");

				int student_id = 0;
				String name = null;
				int year = 0;
				String company_name = null;

				if("search".equals(search)){	//検索された場合
					String work = request.getParameter("student_id");

					if(work != null && !work.equals("")){	//null及び空白チェック
						student_id = Integer.parseInt(work);
					}
					name = request.getParameter("name");

					if("".equals(name)){	//検索値がない場合の処理
						name = null;
					}

					work = request.getParameter("year");

					if(work != null && !work.equals("")){	//null及び空白チェック
						year = Integer.parseInt(work);
					}

					company_name = request.getParameter("company_name");

					if("".equals(company_name)){	//検索値がない場合の処理
						company_name = null;
					}

					request.setAttribute("search", "search");
					request.setAttribute("student_id", student_id);
					request.setAttribute("name", name);
					request.setAttribute("fy", year);
					request.setAttribute("company_name", company_name);
				}

				if(student_id == 0){	//学籍番号が指定されていない場合
					student_id = 4;
				}

				if(name == null){	//氏名が指定されていない場合
					name = "";
				}

				if(company_name == null){	//会社名が指定されていない場合
					company_name = "";
				}

				ArrayList<String> file_nameList = PDFDAO.getFile_nameList(student_id, name, year, company_name);
				request.setAttribute("file_nameList", file_nameList);

				view += "reportReadingTeacher.jsp";	//報告書一覧ページ(教師版)
			}else{	//それ以外の場合
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

		if("creating".equals(status)){	//作成の場合
			String report = request.getParameter("report");

			if("1".equals(report)){	//報告書1/3からの遷移の場合
				String remake = (String)session.getAttribute("remake");

				String pdf_id = null;

				if(remake != null && "remake".equals(remake)){	//続きから作成の場合
					pdf_id = (String)session.getAttribute("pdf_id");
				}else{	//新規作成の場合
					pdf_id = CreateID.createID(8);

					while(PDFDAO.searchPDF_Id(pdf_id) != null){
						pdf_id = CreateID.createID(8);
					}
				}

				int student_id = (int)session.getAttribute("student_id");

				String sorf = request.getParameter("sorf");

				LocalDate sfdate = DateConversion.input_dateConversion(request.getParameter("sfdate"));

				Company company = (Company)session.getAttribute("company");
				String company_id = company.getCompany_id();

				String type_of_industry = request.getParameter("type_of_industry");

				int occupations = Integer.parseInt(request.getParameter("occupations"));

				String application_form = request.getParameter("application_form");

				PDF pdf = new PDF(pdf_id, null, student_id, null, sorf, sfdate, company_id, type_of_industry, occupations, application_form, null, null, false, false, false);

				int row = 0;

				if("remake".equals(remake)){	//続きから作成の場合
					row = PDFDAO.updateReport1(pdf);
				}else{	//新規作成の場合
					row = PDFDAO.addReport1(pdf);
				}

				if(row >= 1){	//格納成功の場合
					session.setAttribute("pdf_id", pdf_id);

					view += "report2.jsp";	//報告書2/3ページ
				}else{	//格納失敗の場合
					view += "error.jsp";	//エラーページ
				}
			}else if("2".equals(report)){	//報告書2/3からの遷移の場合
				String remake = (String)session.getAttribute("remake");

				String pdf_id = (String)session.getAttribute("pdf_id");
				int row = 0;

				for(int n = 1; n <= 3; n++){
					String work = request.getParameter("start_date" + n);
					LocalDate start_date = null;
					if(work != null && !work.equals("")){	//null及び空白チェック
						start_date = DateConversion.input_dateConversion(work);
					}

					work = request.getParameter("start_hour" + n);
					int start_hour = -1;
					if(work != null && !work.equals("")){	//null及び空白チェック
						start_hour = Integer.parseInt(work);
					}

					work = request.getParameter("start_minute" + n);
					int start_minute = -1;
					if(work != null && !work.equals("")){	//null及び空白チェック
						start_minute = Integer.parseInt(work);
					}

					work = request.getParameter("last_hour" + n);
					int last_hour = -1;
					if(work != null && !work.equals("")){	//null及び空白チェック
						last_hour = Integer.parseInt(work);
					}

					work = request.getParameter("last_minute" + n);
					int last_minute = -1;
					if(work != null && !work.equals("")){	//null及び空白チェック
						last_minute = Integer.parseInt(work);
					}

					String[] test_categoryList = request.getParameterValues("test_category" + n);
					int idx = 0;
					String test_category = "";
					String writing = null;
					int viewer_no = 0;
					int view_time = 0;
					String view_content = null;
					int groop_no = 0;
					int discussion_no = 0;

					if(test_categoryList != null && "writing".equals(test_categoryList[idx])){	//筆記試験の場合
						idx++;

						test_category += "筆記";

						String[] writingList = request.getParameterValues("writing" + n);
						StringBuilder sb = new StringBuilder();
						for(int i = 0; i < writingList.length; i++){
							if("other".equals(writingList[i])){	//その他にチェックが入っていた場合
								sb.append(",その他(" + request.getParameter("other" + n) + ")");
							}else{	//それ以外の場合
								sb.append("," + writingList[i]);
							}
						}
						writing = sb.substring(1, sb.length());
					}

					if(test_categoryList != null && idx < test_categoryList.length && "interview".equals(test_categoryList[idx])){	//面接試験の場合
						if(idx == 1){	//筆記試験も選ばれていた場合
							test_category += ",";
						}
						test_category += "面接";

						work = request.getParameter("view_time" + n);
						if(work != null && !work.equals("")){	//null及び空白チェック
							view_time = Integer.parseInt(work);
						}

						work = request.getParameter("viewer_no" + n);
						if(work != null && !work.equals("")){	//null及び空白チェック
							viewer_no = Integer.parseInt(work);
						}

						view_content = request.getParameter("view_content" + n);

						if("groop_interview".equals(view_content)){	//集団面接が選ばれている場合
							view_content = "集団面接";

							work = request.getParameter("groop_no" + n);
							if(work != null && !work.equals("")){	//null及び空白チェック
								groop_no = Integer.parseInt(work);
							}
						}else if("groop_discussion".equals(view_content)){	//グループディスカッションが選ばれている場合
							view_content = "グループディスカッション";

							work = request.getParameter("discussion_no" + n);
							if(work != null && !work.equals("")){	//null及び空白チェック
								discussion_no = Integer.parseInt(work);
							}
						}
					}

					int contents_test_id = 0;

					Contents_Test contents_test = new Contents_Test(contents_test_id, pdf_id, n, start_date, start_hour, start_minute,
							last_hour, last_minute, test_category, writing, viewer_no, view_time, view_content,
							groop_no, discussion_no);

					if("remake".equals(remake)){	//続きから作成の場合
						contents_test_id = Contents_TestDAO.getContents_Test_Id(pdf_id, n);

						if(contents_test_id == 0){	//まだデータが格納されていなかった場合
							row += Contents_TestDAO.addContents_Test(contents_test);
						}else{	//データが格納されていた場合
							contents_test.setContents_test_id(contents_test_id);
							row += Contents_TestDAO.updateContents_Test(contents_test);
						}
					}else{	//新規作成の場合
						row += Contents_TestDAO.addContents_Test(contents_test);
					}

				}
				if(row >= 3){	//格納成功の場合
					view += "report3.jsp";	//報告書3/3ページ
				}else{
					view += "error.jsp";	//エラーページ
				}
			}else if("3".equals(report)){	//報告書3/3からの遷移の場合
				PDF pdf = new PDF();

				String pdf_id = (String)session.getAttribute("pdf_id");
				pdf.setPdf_id(pdf_id);

				String content_of_test = request.getParameter("content_of_test");
				pdf.setContent_of_test(content_of_test);

				String advice_to_junior = request.getParameter("advice_to_junior");
				pdf.setAdvice_to_junior(advice_to_junior);

				int row = PDFDAO.addReport3(pdf);
				if(row >= 1){	//格納成功の場合
					Company company = (Company)session.getAttribute("company");
					String company_name = company.getCompany_name();

					int student_id = (int)session.getAttribute("student_id");
					String name = StudentDAO.getName(student_id);

					String file_name = company_name + "_" + student_id + "_" + name;

					int length = file_name.length();
					int num = 1;
					String work = PDFDAO.searchFile_name(file_name);
					while(work != null){
						if(pdf_id.equals(work)){	//検索したファイル名を作成している報告書が使っていた場合
							break;
						}else{	//検索したファイル名を他の報告書が使っていた場合
							file_name = file_name.substring(0, length) + "(" + num++ +  ")";
							work = PDFDAO.searchFile_name(file_name);
						}
					}
					pdf.setFile_name(file_name);

					LocalDate filing_date = LocalDate.now();
					pdf.setFiling_date(filing_date);

					row = PDFDAO.createReport(pdf, false);

					if(row >= 1){	//報告書確認用データ格納成功の場合
						request.setAttribute("file_name", file_name);

						String jasperPath = request.getServletContext().getRealPath("jasper/report.jrxml");
						String pdfPath = request.getServletContext().getRealPath("pdf/");
						boolean flg = CreatePDF.createPDF(pdf_id, jasperPath, pdfPath);

						if(flg){	//PDF化成功の場合
							view += "reportCreateConfirmation.jsp";	//報告書作成確認ページ
						}else{	////PDF化失敗の場合
							request.setAttribute("e", flg);
							view += "error.jsp";	//エラーページ
						}
					}else{	//報告書確認用データ格納失敗の場合
						view += "error.jsp";	//エラーページ
					}
				}else{	//格納失敗の場合
					view += "error.jsp";	//エラーページ
				}
			}else{	//正常な遷移ではない場合
				view += "top.jsp";	//トップページ
			}
		}else if("remake".equals(status)){	//続きから作成の場合
			String pdf_id = request.getParameter("pdf_id");

			if(pdf_id != null){	//作成を再開するデータ選択からの遷移の場合
				session.setAttribute("pdf_id", pdf_id);

				String company_id = request.getParameter("company_id");
				Company company = CompanyDAO.searchCompany(company_id);
				session.setAttribute("company", company);

				ArrayList<Type_Of_Industry> type_of_industryList = Type_Of_IndustryDAO.getType_Of_IndustryList();
				request.setAttribute("type_of_industryList", type_of_industryList);

				ArrayList<Occupations> occupationsList = OccupationsDAO.getOccupationsList();
				request.setAttribute("occupationsList", occupationsList);

				session.setAttribute("remake", "remake");

				view += "report1.jsp";	//報告書1/3ページ
			}
		}else if("create_completion".equals(status)){	//報告書作成確認からの遷移の場合
			PDF pdf = new PDF();

			String pdf_id = (String)session.getAttribute("pdf_id");
			pdf.setPdf_id(pdf_id);

			Company company = (Company)session.getAttribute("company");
			String company_name = company.getCompany_name();

			int student_id = (int)session.getAttribute("student_id");
			String name = StudentDAO.getName(student_id);

			String file_name = company_name + "_" + student_id + "_" + name;

			int length = file_name.length();
			int num = 1;
			String work = PDFDAO.searchFile_name(file_name);
			while(work != null){
				if(pdf_id.equals(work)){	//検索したファイル名を作成している報告書が使っていた場合
					break;
				}else{	//検索したファイル名を他の報告書が使っていた場合
					file_name = file_name.substring(0, length) + "(" + num++ +  ")";
					work = PDFDAO.searchFile_name(file_name);
				}
			}
			pdf.setFile_name(file_name);

			LocalDate filing_date = LocalDate.now();
			pdf.setFiling_date(filing_date);

			int row = PDFDAO.createReport(pdf, true);

			if(row >= 1){	//作成完了した場合
				String jasperPath = request.getServletContext().getRealPath("jasper/report.jrxml");
				String pdfPath = request.getServletContext().getRealPath("pdf/");
				boolean flg = CreatePDF.createPDF(pdf_id, jasperPath, pdfPath);

				if(flg){	//PDF化成功の場合
					session.removeAttribute("pdf_id");
					session.removeAttribute("company");

					view += "reportCreateCompletion.jsp";	//報告書作成完了ページ
				}else{	////PDF化失敗の場合
					view += "error.jsp";	//エラーページ
				}
			}else{	//作成失敗した場合
				view += "error.jsp";	//エラーページ
			}
		}else if("choice".equals(status)){	//確認したい報告書選択からの遷移の場合
			String pdf_id = request.getParameter("pdf_id");

			if(pdf_id != null){	//ちゃんと選択されていた場合
				session.setAttribute("pdf_id", pdf_id);

				String file_name = PDFDAO.getFile_name(pdf_id);
				request.setAttribute("file_name", file_name);

				view += "reportCheck.jsp";	//報告書確認ページ
			}else{	//選択されていない場合
				view += "error.jsp";	//エラーページ
			}
		}else if("check".equals(status)){	//報告書確認からの遷移の場合
			boolean confirmation = Boolean.valueOf(request.getParameter("confirmation"));

			if(confirmation){	//確認された場合
				String pdf_id = (String)session.getAttribute("pdf_id");

				int teacher_id = (int)session.getAttribute("teacher_id");
				Teacher teacher = TeacherDAO.getTeacher(teacher_id);

				int row = PDFDAO.checkReport(pdf_id, teacher.getBelongs_id());

				if(row >= 1){	//フラグを立てることに成功した場合
					boolean[] flgs = PDFDAO.getFlgs(pdf_id);

					if(flgs[0] && flgs[1]){	//報告書がPDFを作成できる状態になっている場合
						LocalDate filing_date = LocalDate.now();
						row = PDFDAO.updateFiling_Date(pdf_id, filing_date);

						if(row >= 1){	//提出日の更新に成功した場合
							String jasperPath = request.getServletContext().getRealPath("jasper/report.jrxml");
							String pdfPath = request.getServletContext().getRealPath("pdf/");
							boolean flg = CreatePDF.createPDF(pdf_id, jasperPath, pdfPath);

							if(flg){	//PDF作成成功の場合
								view += "pdfAddCompletion.jsp";	//PDF追加完了ページ
							}else{	//PDF作成失敗の場合
								view += "error.jsp";	//エラーページ
							}
						}else{	//提出日の更新に失敗した場合
							view += "error.jsp";	//エラーページ
						}
					}else{	//片方だけ確認が終わった場合
						view += "reportCheckCompletion.jsp";	//報告書確認済ページ
					}
				}else{	//失敗した場合
					view += "error.jsp";	//エラーページ
				}
			}else{	//それ以外の場合
				view += "error.jsp";	//エラーページ
			}
		}else if("send_mail".equals(status)){	//やり直しの通知メール送信の場合
			String title = "報告書の修正指示通知";

			StringBuilder sb = new StringBuilder();

			int teacher_id = (int)session.getAttribute("teacher_id");
			Teacher teacher = TeacherDAO.getTeacher(teacher_id);

			String pdf_id = (String)session.getAttribute("pdf_id");

			String company_id = PDFDAO.getCompany_Id(pdf_id);
			String company_name = CompanyDAO.getCompany_name(company_id);

			int student_id = PDFDAO.getStudent_Id(pdf_id);
			Student student = StudentDAO.searchStudent(student_id);

			sb.append(getAccountName() + "です。\n\n");

			sb.append("あなたが作成していた" + company_name + "の報告書の修正指示が" + teacher.getName() + "先生から出ています。\n\n");

			sb.append("以下の修正指示を確認して速やかに修正した後、再度提出して下さい。\n\n");

			sb.append("先生の入力した内容をそのまま表示します。\n\n");

			String message = request.getParameter("message");
			sb.append(message + "\n\n");

			sb.append("以上が" + teacher.getName() + "先生の入力された内容です。\n\n");

			sb.append(getFooter());

			message = sb.toString();

			String receiverMail_Address = student.getMail_address();

			String receiverName = student.getName();

			boolean flg = sendMail(title, message, receiverMail_Address, receiverName);

			if(flg){	//メールの送信に成功した場合
				int row = PDFDAO.remakeReport(pdf_id);

				if(row >= 1){	//やり直し処理に成功した場合
					view += "reportRemakeNotificationCompletion.jsp";	//修正指示通知完了ページ
				}else{	//やり直し処理に失敗した場合
					view += "error.jsp";	//エラーページ
				}
			}else{	//メールの送信に失敗した場合
				view += "error.jsp";	//エラーページ
			}
		}else if("reading".equals(status)){	//PDFファイルを開く場合
			String filename = request.getParameter("filename");
			request.setAttribute("filename", filename);

			view += "report.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}