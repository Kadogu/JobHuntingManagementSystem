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
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		ArrayList<Log> logList = LogDAO.getLogList();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	%>
		<div class="logout">
			<div class="right m-r30">
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>
		</div>

		<div class="main">
			<h1 class="m-b30">ログ</h1>

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
					<%	if(log.isLogin_sorf()){	%>
							<td><%= log.getLog_id() %></td>
							<td><%= log.getLog_datetime().format(formatter) %></td>
							<td><%= log.getLogin_id() %></td>
							<td><%= log.getLogin_pw() %></td>
							<td>成功</td>
					<%	}else{	%>
							<td><span><%= log.getLog_id() %></span></td>
							<td><span><%= log.getLog_datetime().format(formatter) %></span></td>
							<td><span><%= log.getLogin_id() %></span></td>
							<td><span><%= log.getLogin_pw() %></span></td>
							<td><span>失敗</span></td>
					<%	}	%>
					</tr>
			<%	}	%>
			</table>

			<form action="Main" method="get">
				<input type="submit" value="メインページへ" class="m-t30 m-b30">
			</form>
		</div>
	</body>
</html>