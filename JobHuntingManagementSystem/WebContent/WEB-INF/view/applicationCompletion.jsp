<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="static dto.MailAccount.*" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>届出書作成完了 | モリジョビ就活管理システム</title>
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
			<h1 class="m-b30">届出書作成完了</h1>

			<p>届出書の作成完了しました。</p><br>

			<p>担任の先生が確認して<%= text %>後に<br>登録されているメールアドレス宛にメールを送信します。</p><br>

			<p><%= getMailAddress() %>からの<br>メールの受け取りを許可して下さい。</p>

			<form action="Main" method="get">
				<input type="submit" value="メインページへ" class="m-t30 m-r80">
			</form>

			<form action="ApplicationForm" method="get">
				<input type="hidden" name="status" value="reading">
				<input type="submit" value="届出書閲覧" class="m-t30 m-r80">
			</form>

			<form action="Company" method="get">
				<input type="hidden" name="status" value="choice">
				<input type="hidden" name="use" value="application">
				<input type="submit" value="届出書作成" class="m-t30">
			</form>
		</div>
	</body>
</html>