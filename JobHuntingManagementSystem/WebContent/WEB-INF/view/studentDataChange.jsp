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
	<title>登録内容変更 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		ArrayList<Course> courseList = Cast.autoCast(request.getAttribute("courseList"));
		Student student = (Student)request.getAttribute("student");
	%>
		<div>
			<h1>登録内容変更</h1>

			<form action="RegistrationContentsChange" method="post">
				<label>氏名<span style="color:red">必須</span>:<input type="text" name="name" size="16" maxlength="16" value="<%= student.getName() %>" required></label><br>

				<label>
					<select name="course" required>
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
					<select name="school_year" required>
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
				年</label><br>

				<label>メールアドレス<span style="color:red">必須</span>:<input type="email" name="mail_address" value="<%= student.getMail_address() %>" required></label><br>

				<input type="submit" value="変更">
			</form>

			<form action="Main" method="get">
				<input type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>