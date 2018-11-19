<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>届出書作成 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<% String[] documents = (String[])request.getAttribute("documents"); %>
		<div>
			<form action="" method="">
				<input type="submit" value="ログアウト">
			</form>
		</div>

		<div>
			<h1>届出書作成</h1>

			<form action="ApplicationForm" method="post">
				<p>提出方法:
					<label><input type="radio" name="bring_mailing" value="true" required>持参</label>
					<label><input type="radio" name="bring_mailing" value="false" required>郵送</label>
				</p>
				<p>書類送付先:
					<label><input type="radio" name="destination" value="0">会社宛</label>
					<label><input type="radio" name="destination" value="1">会社＋個人名入</label>
					<label><input type="radio" name="destination" value="2">別の場所</label>
				</p>
				<label>郵便番号:<input type="text" name="postal_code1">-<input type="text" name="postal_code2"></label><br>
				<label>住所:<input type="text" name="address"></label><br>
				<label>個人名:<input type="text" name="individual"></label><br>
				<label>締切日:<input type="date" name="deadline" required></label><br>
				<p>応募書類:
					<%
						for(int i = 0; i < documents.length; i++){
							if(i != documents.length - 1){
					%>
								<label><input type="checkbox" name="<%= i %>" value="1"><%= documents[i] %></label><br>
					<% 		}else{ %>
								<label><input type="checkbox" name="<%= i %>" value="1"><%= documents[i] %></label><input type="text" name="other_contents">
					<%
							}
						}
					%>
				</p>
				<input type="hidden" name="status" value="creating">
				<input type="submit" value="作成">
			</form>

			<form action="" method="">
				<input type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>