<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bin.Cast" %>
<%@ page import="dto.Company" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>会社選択 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		ArrayList<Company> companies = Cast.autoCast(request.getAttribute("companies"));
		String search = (String)request.getAttribute("search");
	%>
		<div class="logout">
			<div class="right m-r30">
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>
		</div>

		<div class="main">
			<h1 class="m-b30">会社選択</h1>

			<form action="Company" method="get" class="search m-b30">
			<%	if(search == null){	%>
					<input type="search" name="search" placeholder="会社名">
			<%	}else{	%>
					<input type="search" name="search" value="<%= search %>">
			<%	}	%>
				<input type="hidden" name="status" value="choice">
				<input type="submit" value="検索">
			</form>

			<table>
				<tr>
					<th></th>
					<th>会社名</th>
					<th>郵便番号</th>
					<th>住所</th>
					<th>電話番号</th>
				</tr>

				<% for(Company company : companies){ %>
					<tr>
						<td><input type="radio" form="decision" name="company" value="<%= company.getCompany_id() %>" id="<%= company.getCompany_id() %>"></td>
						<td><label class="not" for="<%= company.getCompany_id() %>"><%= company.getCompany_name() %></label></td>
						<td><label class="not" for="<%= company.getCompany_id() %>"><%= company.getPostal_code() %></label></td>
						<td><label class="not" for="<%= company.getCompany_id() %>"><%= company.getAddress() %></label></td>
						<td><label class="not" for="<%= company.getCompany_id() %>"><%= company.getPhone_number() %></label></td>
					</tr>
				<% } %>
			</table>

			<form action="Main" method="get">
				<input type="submit" value="キャンセル" class="m-t30  m-b30 m-r80">
			</form>

			<form action="Company" method="get">
				<input type="hidden" name="status" value="edit">
				<input type="submit" value="会社編集" class="m-t30 m-b30 m-r80">
			</form>

			<form action="Company" method="get">
				<input type="hidden" name="status" value="add">
				<input type="submit" value="会社追加" class="m-t30 m-b30 m-r80">
			</form>

			<form action="Company" method="post" id="decision">
            	<input type="hidden" name="status" value="choice">
            	<input type="submit" value="決定" class="m-t30 m-b30">
            </form>
		</div>
	</body>
</html>