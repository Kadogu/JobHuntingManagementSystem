<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>会社修正完了 | モリジョビ就活管理システム</title>
	</head>
	<body>
		<div>
			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>会社修正完了</h1>
				<p>会社の修正を完了しました。</p>

				<form action="Company" method="get">
					<input type="hidden" name="status" value="edit">
					<input type="submit" value="編集">
				</form>

				<form action="Company" method="get">
					<input type="hidden" name="status" value="confirmation">
					<input type="submit" value="確認">
				</form>

				<form action="Main" method="get">
					<input type="submit" value="メインページへ">
				</form>
			</div>
		</div>
	</body>
</html>