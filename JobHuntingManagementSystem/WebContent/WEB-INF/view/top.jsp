<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%	String error = (String)request.getAttribute("error");	%>
		<div class="headder">
			<div class="left m-t10 m-l10 bold">
            	<p>盛岡情報ビジネス専門学校</p>
            </div>

            <div class="right m-t10">
				<img src="<%= request.getContextPath() %>/images/JyobiJyobi.gif" width="130" height="130" alt="ジョビジョビ君">
			</div>
		</div>

		<div class="main">
			<h1 class="m-b10">モリジョビ就活管理システム</h1>
			<%	if(error != null){	%>
					<p class="m-b10"><span><%= error %></span></p>
			<%	}	%>

			<form action="Top" method="post">
				<label>ID:<input type="text" name="user_id" pattern="^[a-zA-Z\d._]{1,16}$" size="16" maxlength="16" required></label><br>
				<label>PW:<input type="password" name="pw" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[._]).{8,16}$" size="16" maxlength="16" required></label><br>
				<input type="submit" value="ログイン" class="m-t10 m-b10">
			</form>

			<p class="m-b10">新規アカウント作成は<a href="<%= request.getContextPath() %>/AccountCreate">こちら</a></p>
			<p class="m-b10">ID・PWを忘れた方は<a href="<%= request.getContextPath() %>/PWChange">こちら</a></p>
		</div>
	</body>
</html>