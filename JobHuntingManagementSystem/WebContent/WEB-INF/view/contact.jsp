<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>お問い合わせ | モリジョビ就活管理システム</title>
	</head>
	<body>
		<div>
			<h1>お問い合わせ</h1>

			<form action="Contact" method="post">
				<label>項目:
					<select name="contact_item" required>
						<option value="1">PW変更失敗</option>
					</select>
				</label><br>

				<label>詳細:
					<textarea name="detail" cols="40" rows="20" maxlength="800" placeholder="できるだけ詳しく書いて下さい。" required></textarea>
				</label><br>

				<input type="submit" value="送信">
			</form>

			<form action="" method="">
				<input type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>