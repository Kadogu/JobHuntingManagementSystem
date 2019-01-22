<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>届出書作成 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%	String[] documents = (String[])request.getAttribute("documents");	%>
		<div class="logout">
			<div class="right m-r30">
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>
		</div>

		<div class="main">
			<h1 class="m-b30">届出書作成</h1>

			<p><label>提出方法<span>必須</span>:</label>
				<label class="not"><input type="radio" form="create" name="bring_mailing" value="true" required>持参</label>
				<label class="not"><input type="radio" form="create" name="bring_mailing" value="false" required>郵送</label>
			</p><br>

			<p><label>書類送付先:</label>
				<label class="not"><input type="radio" form="create" name="destination" value="0">会社宛</label>
				<label class="not"><input type="radio" form="create" name="destination" value="1">会社＋個人名入</label>
				<label class="not"><input type="radio" form="create" name="destination" value="2">別の場所</label>
			</p><br>

			<label>郵便番号:<input type="text" form="create" class="number" name="postal_code1" size="3" maxlength="3" pattern="^[\d]{3}$">-<input type="text" form="create" class="number" name="postal_code2" size="4" maxlength="4" pattern="^[\d]{4}$"></label><br><br>

			<label>住所:<input type="text" form="create" name="address"></label><br><br>

			<label>個人名:<input type="text" form="create" name="individual" placeholder="敬称略"></label><br><br>

			<label>締切日<span>必須</span>:<input type="date" form="create" name="deadline" required></label><br><br>

			<div class="v-align_top"><label>応募書類<span>必須</span>:</label></div>
			<div class="documents">
			<%
				for(int i = 0; i < documents.length; i++){
					if(i != documents.length - 1){
			%>
						<label class="not m-b30"><input type="checkbox" form="create" name="<%= i %>" value="1"><%= documents[i] %></label><br>
			<% 		}else{ %>
						<label class="not"><input type="checkbox" form="create" name="<%= i %>" value="1"><%= documents[i] %></label><input type="text" form="create" class="number" name="other_contents" size="15" maxlength="15" placeholder="その他の内容を入力">
			<%
					}
				}
			%>
			</div><br>

			<form action="Main" method="get">
				<input type="submit" value="キャンセル" class="m-t30 m-b30 m-r80">
			</form>

			<form action="ApplicationForm" method="post" id="create">
            	<input type="hidden" name="status" value="creating">
            	<input type="submit" value="作成" class="m-t30 m-b30">
            </form>
		</div>
	</body>
</html>