<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>修正指示 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		String pdf_id = (String)session.getAttribute("pdf_id");
	%>
		<div class="logout">
			<div class="right m-r30">
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>
		</div>

		<div class="main">
			<h1 class="m-b30">修正指示</h1>

			<p>以下に入力した内容はメールで生徒に通知されます。</p><br>

			<label class="v-align_top" for="message">通知内容:</label><textarea form="notification" id="message" name="message" cols="40" rows="10" maxlength="400" wrap="soft" placeholder="入力された内容はそのまま生徒にメールで通知されます。"></textarea><br>

			<form action="Report" method="post">
				<input type="hidden" name="pdf_id" value="<%= pdf_id %>">
				<input type="hidden" name="status" value="choice">
				<input type="submit" value="キャンセル" class="m-t30 m-r80">
			</form>

			<form action="Report" method="post">
				<input type="hidden" name="status" value="send_mail">
				<input type="submit" value="通知" class="m-t30">
			</form>
		</div>
	</body>
</html>