<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>PDF作成完了 | モリジョビ就活管理システム</title>
	</head>
	<body>
		<div>
			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>PDF作成完了</h1>

				<p>報告書を確認済にしてPDFファイルを作成しました。</p>

				<form action="Report" method="get">
					<input type="hidden" name="status" value="choice">
					<input type="submit" value="確認">
				</form>

				<form action="Report" method="get">
					<input type="hidden" name="status" value="reading">
					<input type="submit" value="閲覧">
				</form>

				<form action="Main" method="get">
					<input type="submit" value="メインページへ">
				</form>
			</div>
		</div>
	</body>
</html>