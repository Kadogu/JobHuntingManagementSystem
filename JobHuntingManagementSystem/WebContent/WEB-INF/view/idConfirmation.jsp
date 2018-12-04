<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>ID確認 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		String name = (String)session.getAttribute("name");
		String user_id = (String)session.getAttribute("user_id");
	%>
		<div>
			<h1>ID確認</h1>

			<p><%= name %>さんのIDは<%= user_id %>です。</p>

			<p>PWを変更するには「送信」ボタンを<br>
			クリックして登録されたメールアドレス宛に<br>
			届くパスコードを入力してPW変更を行って下さい。</p>

			<form action="PWChange" method="post">
				<input type="hidden" name="status" value="idConfirmation">
				<input type="submit" value="送信">
			</form>

			<form action="Top" method="get">
				<input type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>