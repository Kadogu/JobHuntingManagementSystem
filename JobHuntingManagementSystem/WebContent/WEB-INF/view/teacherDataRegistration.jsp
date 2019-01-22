<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bin.Cast" %>
<%@ page import="dto.Belongs" %>
<%@ page import="dto.Course" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>教師データ登録 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		ArrayList<Belongs> belongsList = Cast.autoCast(request.getAttribute("belongsList"));
		ArrayList<Course> courseList = Cast.autoCast(request.getAttribute("courseList"));
	%>
		<div class="main">
			<h1 class="m-t50 m-b30">教師データ登録</h1>

			<label>氏名<span>必須</span>:<input type="text" form="registration" name="name" size="16" maxlength="16" required></label><br><br>

			<label>所属<span>必須</span>:
				<select form="registration" class="m-l30" name="belongs" required>
					<option disabled selected></option>
					<%	for(Belongs belongs : belongsList){	%>
							<option value="<%= belongs.getBelongs_id() %>"><%= belongs.getBelongs_name() %></option>
					<%	}	%>
				</select>
			</label><br><br>

			<p><label class="v-align_top">担当クラス(複数選択可)<span>必須</span>:</label>
				<select form="registration" class="m-l30" name="charge_class" multiple size="5" required>
					<option value="-">担当クラスがない場合はこちら</option>
					<%
						for(Course course : courseList){
							for(int i = 1; i <= course.getYear(); i++){
					%>
								<option value="<%= course.getCourse_id() + i %>"><%= course.getCourse_name() + i + "年" %></option>
					<%
							}
						}
					%>
				</select>
			</p>

			<label>メールアドレス<span>必須</span>:<input type="email" form="registration" name="mail_address" required></label><br>
			<p>※PW変更の際に使用します</p>

			<form action="Top" method="get">
				<input type="submit" value="キャンセル" class="m-t30 m-r80">
			</form>

			<form action="UserDataRegistration" method="post" id="registration">
				<input type="submit" value="登録" class="m-t30">
			</form>
		</div>
	</body>
</html>