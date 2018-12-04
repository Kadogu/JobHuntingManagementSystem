<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>留年リスト追加完了 | モリジョビ就活管理システム</title>
	</head>
	<body>
		<div>
			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>留年リスト追加完了</h1>

				<p>留年リストに追加が完了しました。</p>

				<form action="Repetition_List" method="get">
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