<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>報告書確認完了 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<div class="logout">
			<div class="right m-r30">
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>
		</div>

		<div class="main">
			<h1 class="m-b30">報告書確認済</h1>

			<p>報告書を確認済にしました。</p><br>
			<p>報告書一覧に加わるのはもう一人の<br>
			確認が済んでからになります。</p>

			<form action="Main" method="get">
				<input type="submit" value="メインページへ" class="m-t30 m-r80">
			</form>

			<form action="Report" method="get">
				<input type="hidden" name="status" value="choice">
				<input type="submit" value="確認" class="m-t30">
			</form>
		</div>
	</body>
</html>