<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>学籍番号追加完了 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<% String text = (String)request.getAttribute("text"); %>
		<div>
			<div>
				<form action="" method="">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>学籍番号追加完了</h1>

				<p><%= text %>を追加しました。</p>

				<form action="StudentRelation" method="get">
					<input type="hidden" name="status" value="student_id_add">
					<input type="submit" value="追加">
				</form>

				<form action="StudentRelation" method="get">
					<input type="hidden" name="status" value="student_id_confirmation">
					<input type="submit" value="確認">
				</form>

				<form action="" method="">
					<input type="submit" value="メインページへ">
				</form>
			</div>
		</div>
	</body>
</html>