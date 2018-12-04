<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Company" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>報告書作成確認 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		String file_name = (String)request.getAttribute("file_name");
	%>
		<div>
			<h1>報告書作成確認</h1>

			<p>入力された内容で就活報告書を作成します。</p>

			<p>入力された内容を確認するには以下のリンクをクリックして下さい。</p>

			<p>入力し直す場合は編集ボタンを押して入力のし直しを行って下さい。</p>

			<form action="Report" method="post" name="report" target="_blank">
				<input type="hidden" name="status" value="reading">
				<input type="hidden" name="filename" value="<%= file_name %>">
				<a href="javascript:report.submit()">入力された内容を見る</a>
			</form>

			<form action="Report" method="post">
				<input type="hidden" name="status" value="create_completion">
				<input type="submit" value="作成">
			</form>

			<form action="Report" method="post">
			<%
				String pdf_id = (String)session.getAttribute("pdf_id");
				Company company = (Company)session.getAttribute("company");
				String company_id = company.getCompany_id();
			%>
				<input type="hidden" name="status" value="remake">
				<input type="hidden" name="pdf_id" value="<%= pdf_id %>">
				<input type="hidden" name="company_id" value="<%= company_id %>">
				<input type="submit" value="編集">
			</form>
		</div>
	</body>
</html>