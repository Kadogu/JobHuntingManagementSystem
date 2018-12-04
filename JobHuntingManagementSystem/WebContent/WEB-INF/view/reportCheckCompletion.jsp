<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>報告書確認完了 | モリジョビ就活管理システム</title>
	</head>
	<body>
		<div>
			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>報告書確認済</h1>

				<p>報告書を確認済にしました。</p>
				<p>PDFファイル作成はもう一人の確認が済んでからになります。</p>

				<form action="Report" method="get">
					<input type="hidden" name="status" value="choice">
					<input type="submit" value="確認">
				</form>

				<form action="Main" method="get">
					<input type="submit" value="メインページへ">
				</form>
			</div>
		</div>
	</body>
</html>