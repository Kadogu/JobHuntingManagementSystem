<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>PW変更 | モリジョビ就活管理システム</title>
	</head>
	<body>
		<div>
			<h1>PW変更</h1>

			<p>新しいPWを設定して下さい。</p>

			<form action="PWChange" method="post">
				<label>PW<span style="color:red">必須</span>:<input type="password" name="pw" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[._]).{8,16}$" size="16" maxlength="16" placeholder="半角英数字._8～16字" required></label><br>

				<input type="hidden" name="status" value="pwChange">
				<input type="submit" value="変更">
			</form>

			<form action="Top" method="get">
				<input type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>