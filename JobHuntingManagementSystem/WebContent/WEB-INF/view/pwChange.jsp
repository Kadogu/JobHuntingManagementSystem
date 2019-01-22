<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>PW変更 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<div class="main">
			<h1 class="m-t50">PW変更</h1>

			<p class="m-t30">新しいPWを設定して下さい。</p><br>

			<label>PW<span>必須</span>:<input type="password" form="change" name="pw" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[._]).{8,16}$" size="16" maxlength="16" placeholder="半角英数字._8～16字" required></label><br>

			<form action="Top" method="get">
				<input type="submit" value="キャンセル" class="m-t30 m-r80">
			</form>

			<form action="PWChange" method="post" id="change">
				<input type="hidden" name="status" value="pwChange">
				<input type="submit" value="変更" class="m-t30">
			</form>
		</div>
	</body>
</html>