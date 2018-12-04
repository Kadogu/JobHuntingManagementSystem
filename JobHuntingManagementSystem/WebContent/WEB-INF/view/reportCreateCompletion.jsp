<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>報告書作成完了 | モリジョビ就活管理システム</title>
	</head>
	<body>
		<div>
			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>報告書作成完了</h1>

				<p>報告書作成完了しました。</p>
				<p>報告書がPDFファイルになるのは担任及び就職課の先生の確認が済んでからになります。</p>

				<form action="Company" method="get">
					<input type="hidden" name="status" value="choice">
					<input type="hidden" name="use" value="report">
					<input type="submit" value="新規作成">
				</form>

				<form action="Report" method="get">
					<input type="hidden" name="status" value="remake">
					<input type="submit" value="続きから作成">
				</form>

				<form action="Main" method="get">
					<input type="submit" value="メインページへ">
				</form>
			</div>
		</div>
	</body>
</html>