<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>届出書作成失敗 | モリジョビ就活管理システム</title>
	</head>
	<body>
		<div>
			<form action="" method="">
				<input type="submit" value="ログアウト">
			</form>
		</div>

		<div>
			<h1>届出書作成失敗</h1>

			<p>届出書の作成に失敗しました。</p>

			<p>お手数ですがもう一度作成した下さい。</p>

			<p>それでも作成できない場合は
			<a href="<%= request.getContextPath()%>/">こちら</a>
			からお問い合わせ下さい。</p>

			<form action="Company" method="get">
				<input type="hidden" name="status" value="choice">
				<input type="submit" value="届出書作成">
			</form>

			<form action="" method="">
				<input type="submit" value="メインページへ">
			</form>
		</div>
	</body>
</html>