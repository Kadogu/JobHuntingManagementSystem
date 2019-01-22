<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>学生データ確認 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%	String error = (String)request.getAttribute("error");	%>
		<div class="main">
			<h1 class="m-t50">学生データ確認</h1>
			<%	if(error != null){	%>
					<p><span><%= error %></span></p>
			<%	}	%>

			<p class="m-t30"><label>学籍番号:<input type="text" form="next" name="student_id" pattern="^[\d]{7}$" size="7" maxlength="7" required></label></p>

			<form action="Top" method="get">
				<input type="submit" value="キャンセル" class="m-t30 m-r80">
			</form>

			<form action="UserDataRegistration" method="post" id="next">
				<input type="hidden" name="status" value="confirmation">
				<input type="submit" value="次へ" class="m-t30">
			</form>
		</div>
	</body>
</html>