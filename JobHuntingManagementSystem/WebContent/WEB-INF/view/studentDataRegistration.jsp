<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bin.Cast" %>
<%@ page import="dto.Course" %>
<%@ page import="dto.Student" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>学生データ登録 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		ArrayList<Course> courseList = Cast.autoCast(request.getAttribute("courseList"));
		Student student = (Student)request.getAttribute("student");
	%>
		<div class="main">
			<h1 class="m-t50 m-b30">学生データ登録</h1>

			<label>氏名<span>必須</span>:<input type="text" form="registration" name="name" size="16" maxlength="16" required></label><br><br>

			<label>
				<select form="registration" name="course" required>
					<%
						for(Course course : courseList){
							if(course.getCourse_id().equals(student.getCourse_id())){
					%>
								<option value="<%= course.getCourse_id() %>" selected><%= course.getCourse_name() %></option>
					<%		}else{	%>
								<option value="<%= course.getCourse_id() %>"><%= course.getCourse_name() %></option>
					<%
							}
						}
					%>
				</select>
			コース</label>

			<label>
				<select form="registration" class="m-l30" name="school_year" required>
					<%
						for(int i = 1; i <= 4; i++){
							if(i == student.getSchool_year()){
					%>
								<option selected><%= i %></option>
					<%		}else{	%>
								<option><%= i %></option>
					<%
							}
						}
					%>
				</select>
			年</label><br><br>

			<label>メールアドレス<span>必須</span>:<input type="email" form="registration" name="mail_address" required></label><br>
			<p>※PW変更・届出書の通知に使用します</p>

			<form action="Top" method="get">
				<input type="submit" value="キャンセル" class="m-t30 m-r80">
			</form>

			<form action="UserDataRegistration" method="post" id="registration">
				<input type="hidden" name="status" value="registration">
				<input type="submit" value="登録" class="m-t30">
			</form>
		</div>
	</body>
</html>