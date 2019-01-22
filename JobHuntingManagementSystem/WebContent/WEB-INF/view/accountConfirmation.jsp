<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>アカウント確認 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%	String error = (String)request.getAttribute("error");	%>
		<div class="main">
			<h1 class="m-t50">アカウント確認</h1>

		<%	if(error != null){	%>
				<p><span><%= error %></span></p>
		<%	}	%>

			<p class="m-t30"><label>User種類:</label>
				<label class="not"><input type="radio" form="next" name="category" value="s" required>生徒</label>
				<label class="not"><input type="radio" form="next" name="category" value="t" required>教師</label>
			</p><br>

			<label>氏名<span>必須</span>:<input type="text" form="next" name="name" size="16" maxlength="16" required></label><br><br>

			<label>メールアドレス<span>必須</span>:<input type="email" form="next" name="mail_address" required></label><br><br>

			<form action="Top" method="get">
				<input type="submit" value="キャンセル" class="m-t30 m-r80">
			</form>

			<form action="PWChange" method="post" id="next">
            	<input type="hidden" name="status" value="account_confirmation">
            	<input type="submit" value="次へ" class="m-t30">
            </form>
		</div>
	</body>
</html>