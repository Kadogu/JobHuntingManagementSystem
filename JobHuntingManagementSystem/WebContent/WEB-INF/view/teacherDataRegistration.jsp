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
	</head>
	<body>
	<%
		ArrayList<Belongs> belongsList = Cast.autoCast(request.getAttribute("belongsList"));
		ArrayList<Course> courseList = Cast.autoCast(request.getAttribute("courseList"));
	%>
		<div>
			<h1>教師データ登録</h1>

			<form action="UserDataRegistration" method="post">
				<label>氏名<span style="color:red">必須</span>:<input type="text" name="name" size="16" maxlength="16" required></label><br>

				<label>所属:
					<select name="belongs" required>
						<option disabled selected></option>
						<%	for(Belongs belongs : belongsList){	%>
								<option value="<%= belongs.getBelongs_id() %>"><%= belongs.getBelongs_name() %></option>
						<%	}	%>
					</select>
				</label><br>

				<p>担当クラス(複数選択可):
					<select name="charge_class" multiple size="5" required>
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

				<label>メールアドレス<span style="color:red">必須</span>:<input type="email" name="mail_address" required></label><br>
				<p>※PW変更の際に使用します</p>

				<input type="submit" value="登録">
			</form>

			<form action="Top" method="get">
				<input type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>