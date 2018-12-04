<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>新規アカウント作成 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<% 	String error = (String)request.getAttribute("error");	%>
		<div>
			<h1>新規アカウント作成</h1>
			<%	if(error != null){	%>
					<p><span style="color:red"><%= error %></span></p>
			<%	}	%>

			<form action="AccountCreate" method="post">
				<label>ID<span style="color:red">必須</span>:<input type="text" name="user_id" pattern="^[a-zA-Z\d._]{1,16}$" size="16" maxlength="16" placeholder="半角英数字._16字以内" required></label><br>
				<label>PW<span style="color:red">必須</span>:<input type="password" name="pw" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[._]).{8,16}$" size="16" maxlength="16" placeholder="半角英数字._8～16字" required></label><br>
				<p>User種類:
				<label><input type="radio" name="user" value="s" checked=checked required>生徒</label>
				<label><input type="radio" name="user" value="t" required>教師</label>
				</p>
				<input type="submit" value="次へ">
			</form>

			<form action="Top" method="get">
				<input type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>