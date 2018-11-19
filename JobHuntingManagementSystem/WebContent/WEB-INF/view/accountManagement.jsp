<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>アカウント管理 | モリジョビ就活管理システム</title>
	</head>
	<body>
		<div>
			<div>
				<form action="" method="">
					<input type="submit" value="メインページ">
				</form>
			</div>

			<div>
				<form action="" method="">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>アカウント管理</h1>

				<div>
					<h2>アカウント</h2>
					<p><a href="<%= request.getContextPath() %>/AccountManagement?status=">生徒削除</a></p>
					<p><a href="<%= request.getContextPath() %>/AccountManagement?status=">教師削除</a></p>
				</div>

				<div>
					<h2>システム管理者</h2>
					<p><a href="<%= request.getContextPath() %>/AccountManagement?status=authority_change">権限変更</a></p>
				</div>
			</div>
		</div>
	</body>
</html>