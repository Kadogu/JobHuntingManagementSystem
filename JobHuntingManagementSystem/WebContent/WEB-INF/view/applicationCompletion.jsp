<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="static dto.MailAccount.*" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>届出書作成完了 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%	String text = (String)request.getAttribute("text");	%>
		<div>
			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>届出書作成完了</h1>

				<p>届出書の作成完了しました。</p>

				<p>担任の先生が確認して<%= text %>後に登録されているメールアドレス宛にメールを送信します。</p>

				<p><%= getMailAddress() %>からのメールの受け取りを許可して下さい。</p>

				<form action="Company" method="get">
					<input type="hidden" name="status" value="choice">
					<input type="hidden" name="use" value="application">
					<input type="submit" value="届出書作成">
				</form>

				<form action="ApplicationForm" method="get">
					<input type="hidden" name="status" value="reading">
					<input type="submit" value="届出書閲覧">
				</form>

				<form action="Main" method="get">
					<input type="submit" value="メインページへ">
				</form>
			</div>
		</div>
	</body>
</html>