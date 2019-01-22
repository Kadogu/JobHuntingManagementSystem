<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>報告書作成完了 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<div class="logout">
			<div class="right m-r30">
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>
		</div>

		<div class="main">
			<h1 class="m-b30">報告書作成完了</h1>

			<p>報告書作成完了しました。</p><br>
			<p>報告書のPDFファイルが一覧に追加されるのは<br>
			担任及び就職課の先生の確認が済んでからになります。</p><br>

			<form action="Main" method="get">
				<input type="submit" value="メインページへ" class="m-t30 m-r80">
			</form>

			<form action="Report" method="get">
				<input type="hidden" name="status" value="remake">
				<input type="submit" value="続きから作成" class="m-t30 m-r80">
			</form>

			<form action="Company" method="get">
				<input type="hidden" name="status" value="choice">
				<input type="hidden" name="use" value="report">
				<input type="submit" value="新規作成" class="m-t30">
			</form>
		</div>
	</body>
</html>