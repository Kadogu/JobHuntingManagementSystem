<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Company" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>会社修正 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		Company company = (Company)session.getAttribute("company");
		String[] postal_code = company.getPostal_code().split("-");
		String[] phone_number = company.getPhone_number().split("-");
	%>
		<div>
			<h1>会社修正</h1>

			<form action="Company" method="post">
				<label>会社名:<input type="text" name="company_name" value="<%= company.getCompany_name() %>"></label><br>

				<label>郵便番号:<input type="text" name="postal_code1" value="<%= postal_code[0] %>">-<input type="text" name="postal_code2" value="<%= postal_code[1] %>"></label><br>

				<label>住所:<input type="text" name="address" value="<%= company.getAddress() %>"></label><br>

				<label>電話番号:<input type="text" name="phone_number1" value="<%= phone_number[0] %>">-<input type="text" name="phone_number2" value="<%= phone_number[1] %>">-<input type="text" name="phone_number3" value="<%= phone_number[2] %>"></label><br>

				<input type="hidden" name="status" value="correction">
				<input type="submit" value="修正">
			</form>

			<form action="" method="">
				<input type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>