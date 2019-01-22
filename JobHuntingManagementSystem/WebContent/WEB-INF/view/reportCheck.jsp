<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>報告書確認 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		String file_name = (String)request.getAttribute("file_name");
	%>
		<div class="logout">
			<div class="right m-r30">
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>
		</div>

		<div class="main">
			<h1 class="m-b30">報告書確認</h1>

			<p>入力された内容を確認するためには<br>
			以下のリンクをクリックして下さい。</p><br>

			<p>生徒に報告書のやり直しを指示する場合は<br>
			「修正指示」ボタンをクリックして下さい。</p><br>

			<form action="Report" method="post" name="report" target="_blank">
				<input type="hidden" name="status" value="reading">
				<input type="hidden" name="filename" value="<%= file_name %>">
				<a href="javascript:report.submit()">入力された内容を見る</a>
			</form><br><br>

			<label class="not"><input type="checkbox" form="confirm"  name="confirmation" value="true" required>確認しました</label><br>

			<form action="Report" method="get">
				<input type="hidden" name="status" value="choice">
				<input type="submit" value="キャンセル" class="m-t30 m-r80">
			</form>

			<form action="Report" method="get">
				<input type="hidden" name="status" value="instruction">
				<input type="submit" value="修正指示" class="m-t30 m-r80">
			</form>

			<form action="Report" method="post" id="confirm">
				<input type="hidden" name="status" value="check">
				<input type="submit" value="確定" class="m-t30">
			</form>
		</div>
	</body>
</html>