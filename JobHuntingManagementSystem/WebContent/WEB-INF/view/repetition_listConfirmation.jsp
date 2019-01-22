<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="dao.CourseDAO" %>
<%@ page import="dao.DepartmentDAO" %>
<%@ page import="dao.Repetition_ListDAO" %>
<%@ page import="dao.StudentDAO" %>
<%@ page import="dto.Student" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>留年リスト確認 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		String delete = (String)request.getAttribute("delete");

		LocalDate now = LocalDate.now();

		LocalDate year = (LocalDate)application.getAttribute("year");

		if(year == null){	//Tomcatサーバー起動後初の処理の場合
			int y = now.getYear();
			int m = now.getMonthValue();

			if(m >= 4){	//4～12月の場合
				y++;
			}

			year = LocalDate.of(y, 3, 1);
		}

		ArrayList<Integer> repetition_list = Repetition_ListDAO.getRepetition_List();

		ArrayList<Student> studentList = new ArrayList<Student>();

		for(int student_id : repetition_list){
			Student student = StudentDAO.searchStudent(student_id);
			student.setStudent_id(student_id);
			studentList.add(student);
		}

		HashMap<String, String> departmentMap = DepartmentDAO.getDepartmentMap();
		HashMap<String, String> courseMap = CourseDAO.getCourseMap();
	%>
		<div class="logout">
		<%	if(now.isAfter(year)){	%>
				<div class="left m-l30">
					<form action="Repetition_List" method="get">
						<input type="hidden" name="status" value="pass">
						<input type="submit" value="進級">
					</form>
				</div>
		<%	}	%>

			<div class="right m-r30">
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>
		</div>

		<div class="main">
			<h1 class="m-b10">留年リスト</h1>

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
						<td><input type="checkbox" name="delete" value="<%= student_id %>" id="<%= student_id %>" form="delete"></td>
						<td><label class="not" for="<%= student_id %>"><%= student_id %></label></td>
						<td><label class="not" for="<%= student_id %>"><%= student.getName() %></label></td>
						<td><label class="not" for="<%= student_id %>"><%= departmentMap.get(department_id) %></label></td>
						<td><label class="not" for="<%= student_id %>"><%= courseMap.get(course_id) %></label></td>
						<td><label class="not" for="<%= student_id %>"><%= student.getSchool_year() %></label></td>
					</tr>
			<%	}	%>
			</table>

			<form action="Main" method="get">
				<input type="submit" value="メインページへ" class="m-t30 m-r80">
			</form>

			<form action="Repetition_List" method="post" id="delete">
				<input type="hidden" name="status" value="delete">
				<input type="submit" value="削除" class="m-t30 m-r80">
			</form>

			<form action="Repetition_List" method="get">
				<input type="hidden" name="status" value="add">
				<input type="submit" value="追加" class="m-t30">
			</form>
		</div>
	</body>
</html>