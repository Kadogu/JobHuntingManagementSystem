<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>会社追加 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<div class="main">
			<h1 class="m-t50 m-b30">会社追加</h1>

			<label>会社名<span>必須</span>:<input type="text" form="add" name="company_name" required></label><br><br>
			<label>郵便番号<span>必須</span>:<input type="text" form="add" class="number" name="postal_code1" size="3" maxlength="3" pattern="^[\d]{3}$" required>-<input type="text" form="add" class="number" name="postal_code2" size="4" maxlength="4" pattern="^[\d]{4}$" required></label><br><br>
			<label>住所<span>必須</span>:<input type="text" form="add" name="address" required></label><br><br>
			<label>電話番号<span>必須</span>:<input type="text" form="add" class="number" name="phone_number1" size="4" maxlength="4" pattern="^[\d]{2,4}$" required>-<input type="text" form="add" class="number" name="phone_number2" size="4" maxlength="4" pattern="^[\d]{2,4}$" required>-<input type="text" form="add" class="number" name="phone_number3" size="4" maxlength="4" pattern="^[\d]{2,4}$" required></label><br>

			<form action="Main" method="get">
				<input type="submit" value="キャンセル" class="m-t30 m-r80">
			</form>

			<form action="Company" method="post" id="add">
            	<input type="hidden" name="status" value="add">
            	<input type="submit" value="追加" class="m-t30">
            </form>
		</div>
	</body>
</html>