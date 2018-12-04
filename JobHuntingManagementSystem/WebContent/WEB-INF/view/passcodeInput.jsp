<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>パスコード入力 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		String error = (String)request.getAttribute("error");

		String passcode = (String)session.getAttribute("passcode");
	%>
		<div>
			<h1>パスコード入力</h1>

			<p>登録されたメールアドレスに記載された<br>
			パスコードを入力して下さい。</p>

			<p>メールが届かない場合は「再送」ボタンをクリック</p>

		<%	if(error != null){	%>
				<p><span style="color:red"><%= error %></span></p>
		<%	}	%>

			<form action="PWChange" method="post">
				<label>パスコード:<input type="text" name="passcode" size="8" maxlength="8" pattern="^[\d]{8}$" required></label><br>

				<input type="hidden" name="status" value="passcodeInput">
				<input type="submit" value="次へ">
			</form>

			<form action="PWChange" method="post">
				<input type="hidden" name="status" value="idConfirmation">
				<input type="submit" value="再送">
			</form>

			<form action="Top" method="get">
				<input type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>