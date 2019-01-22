<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>学籍番号追加完了 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%	String text = (String)request.getAttribute("text");	%>
		<div class="logout">
			<div class="right m-r30">
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>
		</div>

		<div class="main">
			<h1 class="m-b30">学籍番号追加完了</h1>

			<p><%= text %>を追加しました。</p>

			<form action="Main" method="get">
				<input type="submit" value="メインページへ" class="m-t30 m-r80">
			</form>

			<form action="StudentRelation" method="get">
				<input type="hidden" name="status" value="student_id_confirmation">
				<input type="submit" value="確認" class="m-t30 m-r80">
			</form>

			<form action="StudentRelation" method="get">
				<input type="hidden" name="status" value="student_id_add">
				<input type="submit" value="追加" class="m-t30">
			</form>
		</div>
	</body>
</html>