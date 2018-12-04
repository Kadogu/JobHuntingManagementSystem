<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="bin.Cast" %>
<%@ page import="dao.CompanyDAO" %>
<%@ page import="dao.DestinationDAO" %>
<%@ page import="dao.Document_ApplicationDAO" %>
<%@ page import="dao.Document_Other_ContentsDAO" %>
<%@ page import="dao.StudentDAO" %>
<%@ page import="dto.Destination" %>
<%@ page import="dto.Document_Application" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>届出書確認 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		int teacher_id = (int)session.getAttribute("teacher_id");
		String[] documents = (String[])request.getAttribute("documents");
		boolean bring_mailing = (boolean)request.getAttribute("bring_mailing");
		String title;
		String column;
		ArrayList<Document_Application> document_applicationList;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM月dd日");

		if(bring_mailing){
			title = "持参";
			column = "準備完了";
			document_applicationList = Document_ApplicationDAO.getDocument_ApplicationList(true, teacher_id);
		}else{
			title = "郵送";
			column = "済";
			document_applicationList = Document_ApplicationDAO.getDocument_ApplicationList(false, teacher_id);
		}
	%>
		<div>
			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>届出書確認(<%= title %>)</h1>

				<form action="ApplicationForm" method="post">
					<table>
						<tr>
							<th><%= column %></th>
							<th>締切日</th>
							<th>申請書類</th>
							<th>会社名</th>

						<%	if(!bring_mailing){	%>
								<th>郵便番号</th>
								<th>住所</th>
								<th>個人名</th>
						<%	}	%>

							<th>申請者</th>
							<th>学籍番号</th>
							<th>発行手数料</th>
						</tr>
					<%
						for(Document_Application d_aList : document_applicationList){
							String d_a_id = d_aList.getDocument_application_id();
					%>
							<tr>
								<td><input type="checkbox" name="document_application_id" value="<%= d_a_id %>" id="<%= d_a_id %>"></td>
								<td><label for="<%= d_a_id %>"><%= d_aList.getDeadline().format(formatter) %></label></td>

							<%
								int[] documents_flg = d_aList.getDocuments_flg();
								StringBuilder sb = new StringBuilder();
								for(int i = 0; i < documents.length - 1; i++){
									if(documents_flg[i] == 1){	//チェックが入っていた場合
										sb.append("," + documents[i]);
									}
								}
								if(documents_flg[documents.length - 1] == 1){	//その他にチェックが入っていた場合
									sb.append("," + Document_Other_ContentsDAO.getContents(d_a_id));
								}
							%>
								<td><label for="<%= d_a_id %>"><%= sb.substring(1, sb.length()) %></label></td>
								<td><label for="<%= d_a_id %>"><%= CompanyDAO.getCompany_name(d_aList.getCompany_id()) %></label></td>

							<%
								if(!bring_mailing){	//郵送の場合
									Destination destination = DestinationDAO.getDestination(d_a_id);
									String postal_code = "-";
									String address = "-";
									String individual = "-";
									if(destination != null){	//データを取得できた場合
										String work = destination.getPostal_code();
										if(work != null){	//データがあった場合
											postal_code = work;
										}
										work = destination.getAddress();
										if(work != null){	//データがあった場合
											address = work;
										}
										work = destination.getIndividual();
										if(work != null){	//データがあった場合
											individual = work;
										}
									}
							%>
									<td><label for="<%= d_a_id %>"><%= postal_code %></label></td>
									<td><label for="<%= d_a_id %>"><%= address %></label></td>
									<td><label for="<%= d_a_id %>"><%= individual %></label></td>
							<%
								}
								int student_id = d_aList.getStudent_id();
							%>
								<td><label for="<%= d_a_id %>"><%= StudentDAO.getName(student_id) %></label></td>
								<td><label for="<%= d_a_id %>"><%= student_id %></label></td>
								<td><label for="<%= d_a_id %>"><%= d_aList.getIssue_fee() %>円</label></td>
							</tr>
					<%	}	%>
					</table>

					<input type="hidden" name="bring_mailing" value="<%= bring_mailing %>">
					<input type="hidden" name="status" value="notification">
					<input type="submit" value="通知">
				</form>

				<form action="Main" method="get">
					<input type="submit" value="キャンセル">
				</form>
			</div>
		</div>
	</body>
</html>