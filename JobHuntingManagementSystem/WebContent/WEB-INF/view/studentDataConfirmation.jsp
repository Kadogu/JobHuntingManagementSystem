<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>学生データ確認 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%	String error = (String)request.getAttribute("error");	%>
		<div>
			<h1>学生データ確認</h1>
			<%	if(error != null){	%>
					<p><span style="red"><%= error %></span></p>
			<%	}	%>

			<form action="UserDataRegistration" method="post">
				<label>学籍番号:<input type="text" name="student_id" pattern="^[\d]{7}$" size="7" maxlength="7" required></label><br>
				<input type="hidden" name="status" value="confirmation">
				<input type="submit" value="次へ">
			</form>

			<form action="Top" method="get">
				<input type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>