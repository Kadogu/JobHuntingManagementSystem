<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bin.Cast" %>
<%@ page import="dto.Course" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>学籍番号追加 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		ArrayList<Course> courseList = Cast.autoCast(request.getAttribute("courseList"));
	%>
		<div>
			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>学籍番号追加</h1>

				<form action="StudentRelation" method="post">
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
									<option><%= i %></option>
							<%	}	%>
						</select>
					年</label>

					<label><input type="number" name="number_of_people" min="1" max="99" required>人</label><br>

					<input type="hidden" name="status" value="student_id_add">
					<input type="submit" value="追加">
				</form>

				<form action="Main" method="get">
					<input type="submit" value="キャンセル">
				</form>
			</div>
		</div>
	</body>
</html>