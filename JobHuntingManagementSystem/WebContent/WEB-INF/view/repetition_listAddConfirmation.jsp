<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.CourseDAO" %>
<%@ page import="dao.DepartmentDAO" %>
<%@ page import="dto.Student" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>留年リスト追加確認 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		int student_id = (int)session.getAttribute("student_id");
		Student student = (Student)request.getAttribute("student");

		String name = student.getName();
		if(name == null){
			name = "氏名が登録されていません";
		}

		String course_id = student.getCourse_id();

		String department_id = CourseDAO.getDepartment_Id(course_id);
		String department_name = DepartmentDAO.getDepartment_name(department_id);

		String course_name = CourseDAO.getCourse_name(course_id);

		int school_year = student.getSchool_year();

		String message = student_id + "　" + name + "　" + department_name + "科　" + course_name + "コース　" + school_year + "年";
	%>
		<div class="logout">
			<div class="right m-r30">
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>
		</div>

		<div class="main">
			<h1 class="m-b30">留年リスト追加確認</h1>

			<p><%= message %></p><br>

			<p>上記の学生を追加しますがよろしいですか？</p>

			<form action="Repetition_List" method="get">
				<input type="hidden" name="status" value="add">
				<input type="submit" value="キャンセル" class="m-t30 m-r80">
			</form>

			<form action="Repetition_List" method="get">
				<input type="hidden" name="status" value="addConfirmation">
				<input type="submit" value="OK" class="m-t30">
			</form>
		</div>
	</body>
</html>