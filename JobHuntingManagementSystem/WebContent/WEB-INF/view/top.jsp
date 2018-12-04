<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>モリジョビ就活管理システム</title>
	</head>
	<body>
	<%	String error = (String)request.getAttribute("error");	%>
		<div>
			<p>盛岡情報ビジネス専門学校</p>
			<img src="<%= request.getContextPath() %>/images/JyobiJyobi.gif" width="100" height="100" alt="ジョビジョビ君">
		</div>

		<div>
			<h1>モリジョビ就活管理システム</h1>
			<%	if(error != null){	%>
					<p><span style="color:red"><%= error %></span></p>
			<%	}	%>

			<form action="Top" method="post">
				<label>ID:<input type="text" name="user_id" pattern="^[a-zA-Z\d._]{1,16}$" size="16" maxlength="16" required></label><br>
				<label>PW:<input type="password" name="pw" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[._]).{8,16}$" size="16" maxlength="16" required></label><br>
				<input type="submit" value="ログイン">
			</form>
			<p>新規アカウント作成は<a href="<%= request.getContextPath() %>/AccountCreate">こちら</a></p>
			<p>ID・PWを忘れた方は<a href="<%= request.getContextPath() %>/PWChange">こちら</a></p>
		</div>
	</body>
</html>