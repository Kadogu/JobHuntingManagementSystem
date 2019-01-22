<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>通知完了 | モリジョビ就活管理システム</title>
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
			<h1 class="m-b30">通知完了</h1>

			<p>通知が完了しました。</p>

			<form action="Main" method="get">
				<input type="submit" value="メインページへ"  class="m-t30">
			</form>
		</div>
	</body>
</html>