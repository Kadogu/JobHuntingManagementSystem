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
				<label>会社名:<input type="text" name="company_name"></label><br>
				<label>郵便番号:<input type="text" name="postal_code1">-<input type="text" name="postal_code2"></label><br>
				<label>住所:<input type="text" name="address"></label><br>
				<label>電話番号:<input type="text" name="phone_number1">-<input type="text" name="phone_number2">-<input type="text" name="phone_number3"></label><br>

				<input type="hidden" name="status" value="add">
				<input type="submit" value="追加">
			</form>

			<form action="" method="">
				<input type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>