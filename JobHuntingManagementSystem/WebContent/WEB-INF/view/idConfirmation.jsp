<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>ID確認 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		String name = (String)session.getAttribute("name");
		String user_id = (String)session.getAttribute("user_id");
	%>
		<div class="main">
			<h1 class="m-t50">ID確認</h1>

			<p class="m-t30"><%= name %>さんのIDは<%= user_id %>です。</p><br>

			<p>PWを変更するには「送信」ボタンをクリックして<br>
			登録されたメールアドレス宛に届くパスコードを<br>
			入力してPW変更を行って下さい。</p><br>

			<form action="Top" method="get">
				<input type="submit" value="キャンセル" class="m-t30 m-r80">
			</form>

			<form action="PWChange" method="post">
				<input type="hidden" name="status" value="idConfirmation">
				<input type="submit" value="送信" class="m-t30">
			</form>
		</div>
	</body>
</html>