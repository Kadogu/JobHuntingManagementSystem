<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>パスコード入力 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		String error = (String)request.getAttribute("error");

		String passcode = (String)session.getAttribute("passcode");
	%>
		<div class="main">
			<h1 class="m-t50">パスコード入力</h1>

			<p class="m-t30">登録されたメールアドレスに記載された<br>
			パスコードを入力して下さい。</p><br>

			<p>メールが届かない場合は「再送」ボタンをクリック</p><br>

		<%	if(error != null){	%>
				<p class="m-b10"><span><%= error %></span></p>
		<%	}	%>

			<label>パスコード<span>必須</span>:<input type="text"  form="next" name="passcode" size="8" maxlength="8" pattern="^[\d]{8}$" required></label><br>

			<form action="Top" method="get">
				<input type="submit" value="キャンセル" class="m-t30 m-r80">
			</form>

			<form action="PWChange" method="post">
				<input type="hidden" name="status" value="idConfirmation">
				<input type="submit" value="再送" class="m-t30 m-r80">
			</form>

			<form action="PWChange" method="post" id="next">
				<input type="hidden" name="status" value="passcodeInput">
				<input type="submit" value="次へ" class="m-t30">
			</form>
		</div>
	</body>
</html>