<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>報告書確認 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		String file_name = (String)request.getAttribute("file_name");
	%>
		<div>
			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>報告書確認</h1>

				<p>入力された内容を確認するためには以下のリンクをクリックして下さい。</p>

				<p>生徒に報告書のやり直しを指示する場合は「修正指示」ボタンをクリックして下さい。</p>

				<form action="Report" method="post" name="report" target="_blank">
					<input type="hidden" name="status" value="reading">
					<input type="hidden" name="filename" value="<%= file_name %>">
					<a href="javascript:report.submit()">入力された内容を見る</a>
				</form>

				<form action="Report" method="post">
					<label><input type="checkbox" name="confirmation" value="true" required>確認しました</label><br>

					<input type="hidden" name="status" value="check">
					<input type="submit" value="確定">
				</form>

				<form action="Report" method="get">
					<input type="hidden" name="status" value="instruction">
					<input type="submit" value="修正指示">
				</form>

				<form action="Report" method="get">
					<input type="hidden" name="status" value="choice">
					<input type="submit" value="キャンセル">
				</form>
			</div>
		</div>
	</body>
</html>