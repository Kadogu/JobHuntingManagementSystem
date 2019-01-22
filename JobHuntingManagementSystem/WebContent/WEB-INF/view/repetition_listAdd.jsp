<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>留年リスト追加 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%	String error = (String)request.getAttribute("error");	%>
		<div class="logout">
			<div class="right m-r30">
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>
		</div>

		<div class="main">
			<h1>留年リスト追加</h1>

			<%	if(error != null){	%>
					<p class="m-t10"><span><%= error %></span></p>
			<%	}	%>

			<p class="m-t30"><label>学籍番号:<input type="text" form="add" name="student_id" pattern="^[\d]{7}$" size="7" maxlength="7" required></label></p>

			<form action="Main" method="get">
				<input type="submit" value="キャンセル" class="m-t30 m-r80">
			</form>

			<form action="Repetition_List" method="get">
				<input type="hidden" name="status" value="confirmation">
				<input type="submit" value="確認" class="m-t30 m-r80">
			</form>

			<form action="Repetition_List" method="post" id="add">
				<input type="hidden" name="status" value="add">
				<input type="submit" value="追加" class="m-t30">
			</form>
		</div>
	</body>
</html>