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
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		ArrayList<Course> courseList = Cast.autoCast(request.getAttribute("courseList"));
	%>
		<div class="logout">
			<div class="right m-r30">
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>
		</div>

		<div class="main">
			<h1 class="m-b30">学籍番号追加</h1>

			<label><span>必須</span>
				<select form="add" name="course" required>
							<option disabled selected></option>
					<%	for(Course course : courseList){	%>
							<option value="<%= course.getCourse_id() %>"><%= course.getCourse_name() %></option>
					<%	}	%>
				</select>
			コース</label>&nbsp;

			<label>
				<select form="add" name="school_year" required>
					<option disabled selected></option>
					<%	for(int i = 1; i <= 4; i++){	%>
							<option><%= i %></option>
					<%	}	%>
				</select>
			年</label>&nbsp;

			<label><input type="number" form="add" name="number_of_people" min="1" max="99" required>人</label><br>

			<form action="Main" method="get">
				<input type="submit" value="キャンセル" class="m-t30 m-r80">
			</form>

			<form action="StudentRelation" method="post" id="add">
				<input type="hidden" name="status" value="student_id_add">
				<input type="submit" value="追加" class="m-t30">
			</form>
		</div>
	</body>
</html>