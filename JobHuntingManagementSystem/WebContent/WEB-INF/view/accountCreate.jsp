<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>新規アカウント作成 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<% 	String error = (String)request.getAttribute("error");	%>
		<div class="main">
			<h1 class="m-t50">新規アカウント作成</h1>
			<%	if(error != null){	%>
					<p><span><%= error %></span></p>
			<%	}	%>

			<p class="m-t30"><label>ID<span>必須</span>:<input type="text" form="next" name="user_id" pattern="^[a-zA-Z\d._]{1,16}$" size="16" maxlength="16" placeholder="半角英数字._16字以内" required></label><p><br>
			<label>PW<span>必須</span>:<input type="password" form="next" name="pw" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[._]).{8,16}$" size="16" maxlength="16" placeholder="半角英数字._8～16字" required></label><br><br>
			<p><label>User種類:</label>
				<label class="not"><input type="radio" form="next" name="user" value="s" checked=checked required>生徒</label>
				<label class="not"><input type="radio" form="next" name="user" value="t" required>教師</label>
			</p>

			<form action="Top" method="get">
				<input type="submit" value="キャンセル" class="m-t30 m-r80">
			</form>

			<form action="AccountCreate" method="post" id="next">
            	<input type="submit" value="次へ" class="m-t30">
            </form>
		</div>
	</body>
</html>