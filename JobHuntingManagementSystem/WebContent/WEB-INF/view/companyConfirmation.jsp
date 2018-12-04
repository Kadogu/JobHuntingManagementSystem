<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bin.Cast" %>
<%@ page import="dto.Company" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>会社確認 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		ArrayList<Company> companies = Cast.autoCast(request.getAttribute("companies"));
		String search = (String)request.getAttribute("search");
	%>
		<div>
			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>会社確認</h1>

				<form action="Company" method="get">
				<%	if(search == null){	%>
						<input type="search" name="search" placeholder="会社名">
				<%	}else{	%>
						<input type="search" name="search" value="<%= search %>">
				<%	}	%>
					<input type="hidden" name="status" value="confirmation">
					<input type="submit" value="検索">
				</form>

				<table>
					<tr>
						<th>会社名</th>
						<th>郵便番号</th>
						<th>住所</th>
						<th>電話番号</th>
					</tr>

				<%	for(Company company : companies){	%>
						<tr>
							<td><%= company.getCompany_name() %></td>
							<td><%= company.getPostal_code() %></td>
							<td><%= company.getAddress() %></td>
							<td><%= company.getPhone_number() %></td>
						</tr>
				<%	}	%>
				</table>

				<form action="Company" method="get">
					<input type="hidden" name="status" value="add">
					<input type="submit" value="追加">
				</form>

				<form action="Company" method="get">
					<input type="hidden" name="status" value="edit">
					<input type="submit" value="編集">
				</form>

				<form action="Main" method="get">
					<input type="submit" value="メインページへ">
				</form>
			</div>
		</div>
	</body>
</html>