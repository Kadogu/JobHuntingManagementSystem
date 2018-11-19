<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bin.Cast" %>
<%@ page import="dto.Course" %>
<%@ page import="dto.Department" %>
<%@ page import="dto.Student" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>学生データ登録 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		ArrayList<Department> departmentList = Cast.autoCast(request.getAttribute("departmentList"));
		ArrayList<Course> courseList = Cast.autoCast(request.getAttribute("courseList"));
		Student student = (Student)request.getAttribute("student");
		String department_id = (String)request.getAttribute("department_id");
	%>
		<div>
			<h1>学生データ登録</h1>

			<form action="UserDataRegistration" method="post">
				<label>氏名:<input type="text" name="name"></label><br>

				<label>
					<select name="department">
						<%
							for(Department department : departmentList){
								if(department.getDepartment_id().equals(department_id)){
						%>
									<option value="<%= department.getDepartment_id() %>" selected><%= department.getDepartment_name() %></option>
						<%		}else{	%>
									<option value="<%= department.getDepartment_id() %>"><%= department.getDepartment_name() %></option>
						<%
								}
							}
						%>
					</select>
				科</label>

				<label>
					<select name="course">
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
					<select name="school_year">
						<%
							for(int i = 1; i <= 4; i++){
								if(i == student.getSchool_year()){
						%>
									<option value="<%= i %>" selected><%= i %></option>
						<%		}else{	%>
									<option value="<%= i %>"><%= i %></option>
						<%
								}
							}
						%>
					</select>
				年</label><br>

				<label>メールアドレス:<input type="email" name="mail_address"></label><br>
				<p>※アカウントロックの通知などに使用します</p>

				<input type="hidden" name="status" value="registration">
				<input type="submit" value="登録">
			</form>

			<form action="" method="">
				<input type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>