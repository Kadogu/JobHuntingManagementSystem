<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bin.Cast" %>
<%@ page import="dto.Course" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>学籍番号確認 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		ArrayList<Integer> student_idList = Cast.autoCast(request.getAttribute("student_idList"));
		ArrayList<Course> courseList = Cast.autoCast(request.getAttribute("courseList"));
		String course_id = (String)request.getAttribute("course_id");
		int school_year = (int)request.getAttribute("school_year");
	%>
		<div>
			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>学籍番号確認</h1>

				<form action="StudentRelation" method="get">
					<label>
						<select name="course">
							<option></option>
							<%
								for(Course course : courseList){
									if(course.getCourse_id().equals(course_id)){
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
							<option></option>
							<%
								for(int i = 1; i <= 4; i++){
									if(school_year == i){
							%>
										<option selected><%= i %></option>
							<%		}else{	%>
										<option><%= i %></option>
							<%
									}
								}
							%>
						</select>
					年</label>

					<input type="hidden" name="status" value="student_id_confirmation">
					<input type="submit" value="絞込">
				</form>

				<table>
					<%
						if(!student_idList.isEmpty()){
							int col = 10;	//1行に表示する学籍番号の数
							for(int i = 0; i < student_idList.size(); i += col){
					%>
								<tr>
								<%	for(int j = i; j < i + col && j < student_idList.size(); j++){	%>
										<td><%= student_idList.get(j) %></td>
								<%	}	%>
								</tr>
					<%
							}
						}else{
					%>
							<tr>
								<td>該当者なし</td>
							</tr>
					<%	}	%>
				</table>

				<form action="StudentRelation" method="get">
					<input type="hidden" name="status" value="student_id_add">
					<input type="submit" value="追加">
				</form>

				<form action="Main" method="get">
					<input type="submit" value="メインページへ">
				</form>
			</div>
		</div>
	</body>
</html>