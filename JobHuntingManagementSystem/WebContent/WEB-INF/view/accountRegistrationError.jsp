<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>アカウント登録失敗 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<div class="main">
			<h1 class="m-t50 m-b30">アカウント登録失敗</h1>
			<p class="m-b30">アカウント登録に失敗しました。</p>
			<p><a href="<%= request.getContextPath()%>/AccountCreate">初めから</a>やり直して下さい。</p>
		</div>
	</body>
</html>