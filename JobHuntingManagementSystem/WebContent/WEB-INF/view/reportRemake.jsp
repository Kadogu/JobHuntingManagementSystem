<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.CompanyDAO" %>
<%@ page import="dao.PDFDAO" %>
<%@ page import="dto.PDF" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>続きから作成 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		int student_id = (int)session.getAttribute("student_id");

		ArrayList<PDF> reportList = PDFDAO.getReportList(student_id);
	%>
		<div class="logout">
			<div class="right m-r30">
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>
		</div>

		<div class="main">
			<h1 class="m-b30">作成を再開するデータ選択</h1>

		<%
			if(reportList.size() == 1){
				PDF report = reportList.get(0);
				String company_id = report.getCompany_id();
		%>
				<form action="Report" method="post" name="report">
					<input type="hidden" name="status" value="remake">
					<input type="hidden" name="pdf_id" value="<%= report.getPdf_id() %>">
					<input type="hidden" name="company_id" value="<%= company_id %>">
					<a href="javascript:report.submit()"><%= CompanyDAO.getCompany_name(company_id) %></a>
				</form><br><br>
		<%
			}else{
				for(int i = 0; i < reportList.size(); i++){
					PDF report = reportList.get(i);
					String company_id = report.getCompany_id();
		%>
					<form action="Report" method="post" name="report">
						<input type="hidden" name="status" value="remake">
						<input type="hidden" name="pdf_id" value="<%= report.getPdf_id() %>">
						<input type="hidden" name="company_id" value="<%= company_id %>">
						<a href="javascript:report[<%= i %>].submit()"><%= CompanyDAO.getCompany_name(company_id) %></a>
					</form><br><br>
		<%
				}
			}
		%>

			<form action="Main" method="get">
				<input type="submit" value="キャンセル" class="m-b30 m-r80">
			</form>

			<form action="Company" method="get">
				<input type="hidden" name="status" value="edit">
				<input type="submit" value="会社編集" class="m-b30">
			</form>
		</div>
	</body>
</html>