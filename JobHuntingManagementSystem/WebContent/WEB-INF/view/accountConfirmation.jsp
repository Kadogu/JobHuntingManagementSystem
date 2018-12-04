<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>アカウント確認 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%	String error = (String)request.getAttribute("error");	%>
		<div>
			<h1>アカウント確認</h1>

		<%	if(error != null){	%>
				<p><span style="color:red"><%= error %></span></p>
		<%	}	%>

			<form action="PWChange" method="post">
				<p>ユーザー種類:
					<label><input type="radio" name="category" value="s" required>生徒</label>
					<label><input type="radio" name="category" value="t" required>教師</label>
				</p>

				<label>氏名<span style="color:red">必須</span>:<input type="text" name="name" size="16" maxlength="16" required></label><br>

				<label>メールアドレス<span style="color:red">必須</span>:<input type="email" name="mail_address" required></label><br>

				<input type="hidden" name="status" value="account_confirmation">
				<input type="submit" value="次へ">
			</form>

			<form action="Top" method="get">
				<input type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>