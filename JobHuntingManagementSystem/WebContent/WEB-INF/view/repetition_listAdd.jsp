<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>留年リスト追加 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%	String error = (String)request.getAttribute("error");	%>
		<div>
			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>留年リスト追加</h1>

			<%	if(error != null){	%>
					<p><span style="color:red"><%= error %></span></p>
			<%	}	%>

				<form action="Repetition_List" method="post">
					<label>学籍番号:<input type="text" name="student_id" pattern="^[\d]{7}$" size="7" maxlength="7" required></label><br>

					<input type="hidden" name="status" value="add">
					<input type="submit" value="追加">
				</form>

				<form action="Repetition_List" method="get">
					<input type="hidden" name="status" value="confirmation">
					<input type="submit" value="確認">
				</form>

				<form action="Main" method="get">
					<input type="submit" value="キャンセル">
				</form>
			</div>
		</div>
	</body>
</html>