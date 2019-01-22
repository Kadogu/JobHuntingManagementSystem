<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>エラー | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		String category = (String)session.getAttribute("category");
	%>
		<%	if("s".equals(category) || "t".equals(category)){	%>
				<div class="logout">
					<div class="right m-r30">
						<form action="Main" method="post">
							<input type="submit" value="ログアウト">
						</form>
					</div>
				</div>
		<%	}	%>

		<div class="main">
			<h1 class="m-t50 m-b30">エラー</h1>

			<p>何らかの不具合により正常に処理が完了しませんでした。</p>

			<form action="Main" method="get">
				<input type="submit" value="メインページへ" class="m-t30">
			</form>
		</div>
	</body>
</html>