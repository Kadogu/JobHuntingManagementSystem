<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="bin.Cast" %>
<%@ page import="dao.CourseDAO" %>
<%@ page import="dao.DepartmentDAO" %>
<%@ page import="dao.StudentDAO" %>
<%@ page import="dto.Student" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>生徒削除 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		String search = (String)request.getAttribute("search");

		String delete = (String)request.getAttribute("delete");

		ArrayList<Student> studentList = Cast.autoCast(request.getAttribute("studentList"));

		HashMap<String, String> courseMap = CourseDAO.getCourseMap();
		HashMap<String, String> departmentMap = DepartmentDAO.getDepartmentMap();
	%>
		<div class="logout">
			<div class="right m-r30">
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>
		</div>

		<div class="main">
			<h1 class="m-b10">生徒削除</h1>

			<form action="AccountManagement" method="get" class="search m-b10">
			<%	if(search == null || "".equals(search)){	%>
					<input type="search" name="search" placeholder="学籍番号または氏名を入力">
			<%	}else{	%>
					<input type="search" name="search" placeholder="学籍番号または氏名を入力" value="<%= search %>">
			<%	}%>

				<input type="hidden" name="status" value="student">
				<input type="submit" value="検索">
			</form>

		<%	if(delete != null){	%>
				 <p class="m-b10"><span><%= delete %></span></p>
		<%	}	%>

			<table>
				<tr>
					<th></th>
					<th>学籍番号</th>
					<th>氏名</th>
					<th>学科</th>
					<th>コース</th>
					<th>学年</th>
				</tr>
			<%
				for(Student student : studentList){
					int student_id = student.getStudent_id();
					String course_id = student.getCourse_id();
					String department_id = CourseDAO.getDepartment_Id(course_id);
			%>
					<tr>
						<td><input type="checkbox" form="delete" name="student_id" value="<%= student_id %>" id="<%= student_id %>"></td>
						<td><label class="not" for="<%= student_id %>"><%= student_id %></label></td>
						<td><label class="not" for="<%= student_id %>"><%= student.getName() %></label></td>
						<td><label class="not" for="<%= student_id %>"><%= departmentMap.get(department_id) %></label></td>
						<td><label class="not" for="<%= student_id %>"><%= courseMap.get(course_id) %></label></td>
						<td><label class="not" for="<%= student_id %>"><%= student.getSchool_year() %></label></td>
					</tr>
			<%	}	%>
			</table>

			<form action="Main" method="get">
				<input type="submit" value="キャンセル" class="m-t30 m-b30  m-r80">
			</form>

			<form action="AccountManagement" method="post" id="delete">
				<input type="hidden" name="status" value="student">
				<input type="submit" value="削除" class="m-t30 m-b30">
			</form>
		</div>
	</body>
</html>