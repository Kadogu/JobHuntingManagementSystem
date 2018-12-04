<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>エラー | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		String category = (String)session.getAttribute("category");
	%>
		<div>
		<%	if("s".equals(category) || "t".equals(category)){	%>
				<div>
					<form action="Main" method="post">
						<input type="submit" value="ログアウト">
					</form>
				</div>
		<%	}	%>

			<div>
				<h1>エラー</h1>

				<p>何らかの不具合により正常に処理が完了しませんでした。</p>

				<form action="Main" method="get">
					<input type="submit" value="メインページへ">
				</form>
			</div>
		</div>
	</body>
</html>