<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Company" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>会社選択確認 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		Company company = (Company)session.getAttribute("company");
		String use = (String)session.getAttribute("use");
		String action = "Report";
		if("application".equals(use)){	//届出書作成の場合
			action = "ApplicationForm";
		}
	%>
		<div class="main">
			<h1 class="m-t50 m-b30">会社選択確認</h1>

			<p>会社名:&nbsp;<%= company.getCompany_name() %></p><br>
			<p>郵便番号:&nbsp;<%= company.getPostal_code() %></p><br>
			<p>住所:&nbsp;<%= company.getAddress() %></p><br>
			<p>電話番号:&nbsp;<%= company.getPhone_number() %></p><br>
			<p>このデータで作成開始します。</p><br>
			<p>よろしいですか？</p>

			<form action="Company" method="get">
				<input type="hidden" name="status" value="choice">
				<input type="hidden" name="use" value="<%= use %>">
				<input type="submit" value="キャンセル" class="m-t30 m-r80">
			</form>

			<form action="<%= action %>" method="get">
				<input type="hidden" name="status" value="creating">
				<input type="submit" value="OK" class="m-t30 m-r80">
			</form>
		</div>
	</body>
</html>