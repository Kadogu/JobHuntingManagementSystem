<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>お問い合わせメール送信失敗 | モリジョビ就活管理システム</title>
	</head>
	<body>
		<div>
			<form action="" method="">
				<input type="submit" value="ログアウト">
			</form>
		</div>

		<div>
			<h1>お問い合わせメール送信失敗</h1>

			<p>お問い合わせメールの送信に失敗しました。</p>

			<p>再送しても失敗する場合はお手数ですが<br>
			担任の先生もしくはシステム管理者の～まで<br>
			その旨を伝えて下さい。</p>

			<form action="Contact" method="post">
				<input type="submit" value="再送">
			</form>

			<form action="" method="">
				<input type="submit" value="メインページへ">
			</form>
		</div>
	</body>
</html>