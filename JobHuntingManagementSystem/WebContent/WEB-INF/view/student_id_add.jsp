<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bin.Cast" %>
<%@ page import="dto.Course" %>
<%@ page import="dto.Department" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>学籍番号追加 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		ArrayList<Department> departmentList = Cast.autoCast(request.getAttribute("departmentList"));
		ArrayList<Course> courseList = Cast.autoCast(request.getAttribute("courseList"));
	%>
		<div>
			<div>
				<form action="" method="">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>学籍番号追加</h1>

				<form action="StudentRelation" method="post">
					<label>
						<select name="department" required>
									<option disabled selected></option>
							<%	for(Department department : departmentList){	%>
									<option value="<%= department.getDepartment_id() %>"><%= department.getDepartment_name() %></option>
							<%	}	%>
						</select>
					科</label>

					<label>
						<select name="course" required>
									<option disabled selected></option>
							<%	for(Course course : courseList){	%>
									<option value="<%= course.getCourse_id() %>"><%= course.getCourse_name() %></option>
							<%	}	%>
						</select>
					コース</label>

					<label>
						<select name="school_year" required>
									<option disabled selected></option>
							<%	for(int i = 1; i <= 4; i++){	%>
									<option value="<%= i %>"><%= i %></option>
							<%	}	%>
						</select>
					年</label>

					<label><input type="number" name="number_of_people" required>人</label>

					<input type="submit" value="追加">
				</form>

				<form action="" method="">
					<input type="submit" value="キャンセル">
				</form>
			</div>
		</div>
	</body>
</html>