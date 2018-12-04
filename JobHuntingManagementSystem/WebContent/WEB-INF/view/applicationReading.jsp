<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="bin.Cast" %>
<%@ page import="dao.CompanyDAO" %>
<%@ page import="dao.Document_Other_ContentsDAO" %>
<%@ page import="dto.Document_Application" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>届出書閲覧 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		String[] documents = (String[])request.getAttribute("documents");
		ArrayList<Document_Application> list = Cast.autoCast(request.getAttribute("list"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM月dd日");
	%>
		<div>
			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>作成済届出書</h1>

				<table>
					<tr>
						<th>会社名</th>
						<th>申込日</th>
						<th>締切日</th>

					<%	for(String document : documents){	%>
							<th><%= document %></th>
					<%	}	%>

						<th>提出方法</th>
						<th>発行手数料</th>
					</tr>

					<%
						for(Document_Application d_a : list){
							boolean issue_flg = d_a.isIssue_flg();
							String company_name = CompanyDAO.getCompany_name(d_a.getCompany_id());
							if(issue_flg){	//発行済の場合
					%>
								<tr style="background:#d3d3d3">
					<% 		}else{	//発行前の場合	%>
								<tr>
					<% 		}		%>
									<td><%= company_name %></td>
									<td><%= d_a.getApplication_date().format(formatter) %></td>
									<td><%= d_a.getDeadline().format(formatter) %></td>
								<%
									int[] documents_flg = d_a.getDocuments_flg();
									for(int i = 0; i < documents_flg.length - 1; i++){
										if(documents_flg[i] == 1){
								%>
											<td>○</td>
								<%		}else{	%>
											<td>-</td>
								<%
										}
									}

									String contents = "-";

									if(documents_flg[documents_flg.length - 1] == 1){	//その他にチェックが入っていた場合
										String document_application_id = d_a.getDocument_application_id();
										contents = Document_Other_ContentsDAO.getContents(document_application_id);
									}
								%>
									<td><%= contents %></td>
								<%
									boolean bring_mailing = d_a.isBring_mailing();
									if(bring_mailing){	//持参の場合
								%>
										<td>持参</td>
								<%	}else{	//郵送の場合	%>
										<td>郵送</td>
								<%	}	%>
									<td><%= d_a.getIssue_fee() %>円</td>
								</tr>
					<% 	} %>
				</table>

				<form action="Company" method="get">
					<input type="hidden" name="status" value="choice">
					<input type="hidden" name="use" value="application">
					<input type="submit" value="届出書作成">
				</form>

				<form action="Main" method="get">
					<input type="submit" value="メインページへ">
				</form>
			</div>
		</div>
	</body>
</html>