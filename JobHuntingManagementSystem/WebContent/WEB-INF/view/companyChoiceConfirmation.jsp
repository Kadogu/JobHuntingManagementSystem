<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Company" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>会社選択確認 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		Company company = (Company)session.getAttribute("company");
		String use = (String)session.getAttribute("use");
		String action = "Report";
		if("application".equals(use)){
			action = "ApplicationForm";
		}
	%>
		<div>
			<h1>会社選択確認</h1>
			<p>会社名:<%= company.getCompany_name() %></p>
			<p>郵便番号:<%= company.getPostal_code() %></p>
			<p>住所:<%= company.getAddress() %></p>
			<p>電話番号:<%= company.getPhone_number() %></p>
			<p>このデータで作成開始します。</p>
			<p>よろしいですか？</p>

			<form action="<%= action %>" method="get">
				<input type="hidden" name="status" value="creating">
				<input type="submit" value="OK">
			</form>

			<form action="" method="">
				<input type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>