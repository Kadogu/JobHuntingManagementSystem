<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.LogDAO" %>
<%@ page import="dto.Log" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>ログ | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		ArrayList<Log> logList = LogDAO.getLogList();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	%>
		<div>
			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>ログ</h1>

				<table>
					<tr>
						<th>ログID</th>
						<th>日時</th>
						<th>ID</th>
						<th>PW</th>
						<th>ログイン成否</th>
					</tr>
				<%
					for(Log log : logList){
				%>
						<tr>
							<td><%= log.getLog_id() %></td>
							<td><%= log.getLog_datetime().format(formatter) %></td>
							<td><%= log.getLogin_id() %></td>
							<td><%= log.getLogin_pw() %></td>
							<td><%= log.isLogin_sorf() %></td>
						</tr>
				<%	}	%>
				</table>

				<form action="Main" method="get">
					<input type="submit" value="メインページへ">
				</form>
			</div>
		</div>
	</body>
</html>