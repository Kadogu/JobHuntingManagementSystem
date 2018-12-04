<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bin.Cast" %>
<%@ page import="dto.Company" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>会社編集 | モリジョビ就活管理システム</title>
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
				<h1>会社編集</h1>

				<form action="Company" method="get">
				<%	if(search == null){	%>
						<input type="search" name="search" placeholder="会社名">
				<%	}else{	%>
						<input type="search" name="search" value="<%= search %>">
				<%	}	%>
					<input type="hidden" name="status" value="edit">
					<input type="submit" value="検索">
				</form>

				<form action="Company" method="post">
					<table>
						<tr>
							<th></th>
							<th>会社名</th>
							<th>郵便番号</th>
							<th>住所</th>
							<th>電話番号</th>
						</tr>

					<%	for(Company company : companies){	%>
							<tr>
								<td><input type="radio" name="company" value="<%= company.getCompany_id() %>" id="<%= company.getCompany_id() %>"></td>
								<td><label for="<%= company.getCompany_id() %>"><%= company.getCompany_name() %></label></td>
								<td><label for="<%= company.getCompany_id() %>"><%= company.getPostal_code() %></label></td>
								<td><label for="<%= company.getCompany_id() %>"><%= company.getAddress() %></label></td>
								<td><label for="<%= company.getCompany_id() %>"><%= company.getPhone_number() %></label></td>
							</tr>
					<%	}	%>
					</table>

					<input type="hidden" name="status" value="edit">
					<input type="submit" value="修正">
				</form>

				<form action="Main" method="get">
					<input type="submit" value="メインページへ">
				</form>
			</div>
		</div>
	</body>
</html>