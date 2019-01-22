<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Company" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>会社修正 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		Company company = (Company)session.getAttribute("company");
		String[] postal_code = company.getPostal_code().split("-");
		String[] phone_number = company.getPhone_number().split("-");
	%>
		<div class="main">
			<h1 class="m-t50 m-b30">会社修正</h1>

			<label>会社名<span>必須</span>:<input type="text" form="correction" name="company_name" value="<%= company.getCompany_name() %>" required></label><br><br>

			<label>郵便番号<span>必須</span>:<input type="text" form="correction" class="number" name="postal_code1" value="<%= postal_code[0] %>" size="3" maxlength="3" pattern="^[\d]{3}$" required>-<input type="text" form="correction" class="number" name="postal_code2" value="<%= postal_code[1] %>" size="4" maxlength="4" pattern="^[\d]{4}$" required></label><br><br>

			<label>住所<span>必須</span>:<input type="text" form="correction" name="address" value="<%= company.getAddress() %>" required></label><br><br>

			<label>電話番号<span>必須</span>:<input type="text" form="correction" class="number" name="phone_number1" value="<%= phone_number[0] %>" size="4" maxlength="4" pattern="^[\d]{2,4}$" required>-<input type="text" form="correction" class="number" name="phone_number2" value="<%= phone_number[1] %>" size="4" maxlength="4" pattern="^[\d]{2,4}$" required>-<input type="text" form="correction" class="number" name="phone_number3" value="<%= phone_number[2] %>" size="4" maxlength="4" pattern="^[\d]{2,4}$" required></label><br>

			<form action="Company" method="get">
				<input type="hidden" name="status" value="edit">
				<input type="submit" value="キャンセル" class="m-t30 m-r80">
			</form>

			<form action="Company" method="post" id="correction">
				<input type="hidden" name="status" value="correction">
				<input type="submit" value="修正" class="m-t30">
			</form>
		</div>
	</body>
</html>