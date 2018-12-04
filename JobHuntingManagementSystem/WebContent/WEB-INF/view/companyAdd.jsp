<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>会社追加 | モリジョビ就活管理システム</title>
	</head>
	<body>
		<div>
			<h1>会社追加</h1>

			<form action="Company" method="post">
				<label>会社名<span style="color:red">必須</span>:<input type="text" name="company_name" required></label><br>
				<label>郵便番号<span style="color:red">必須</span>:<input type="text" name="postal_code1" size="3" maxlength="3" pattern="^[\d]{3}$" required>-<input type="text" name="postal_code2" size="4" maxlength="4" pattern="^[\d]{4}$" required></label><br>
				<label>住所<span style="color:red">必須</span>:<input type="text" name="address" required></label><br>
				<label>電話番号<span style="color:red">必須</span>:<input type="text" name="phone_number1" size="4" maxlength="4" pattern="^[\d]{2,4}$" required>-<input type="text" name="phone_number2" size="4" maxlength="4" pattern="^[\d]{2,4}$" required>-<input type="text" name="phone_number3" size="4" maxlength="4" pattern="^[\d]{2,4}$" required></label><br>

				<input type="hidden" name="status" value="add">
				<input type="submit" value="追加">
			</form>

			<form action="Main" method="get">
				<input type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>