<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="bin.Cast" %>
<%@ page import="dao.BelongsDAO" %>
<%@ page import="dao.TeacherDAO" %>
<%@ page import="dto.Teacher" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>教師削除 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		String search = (String)request.getAttribute("search");

		String delete = (String)request.getAttribute("delete");

		ArrayList<Teacher> teacherList = Cast.autoCast(request.getAttribute("teacherList"));

		HashMap<String, String> belongsMap = BelongsDAO.getBelongsMap();
	%>
		<div>
			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>教師削除</h1>

				<form action="AccountManagement" method="get">
				<%	if(search == null){	%>
						<input type="search" name="search" placeholder="氏名を入力">
				<%	}else{	%>
						<input type="search" name="search" placeholder="氏名を入力" value="<%= search %>">
				<%	}	%>

					<input type="hidden" name="status" value="teacher">
					<input type="submit" value="検索">
				</form>

			<%	if(delete != null){	%>
					<p><span style="color:red"><%= delete %></span></p>
			<%	}	%>

				<table>
					<tr>
						<th>/</th>
						<th>学籍番号</th>
						<th>所属</th>
						<th>管理者権限</th>
					</tr>
				<%
					for(Teacher teacher : teacherList){
						int teacher_id = teacher.getTeacher_id();
						String belongs_id = teacher.getBelongs_id();
						boolean admin_flg = teacher.isAdmin_flg();
						String text = "無";
						if(admin_flg){	//システム管理者権限を有する場合
							text = "有";
						}
				%>
						<tr>
							<td><input type="checkbox" name="teacher_id" value="<%= teacher_id %>" id="<%= teacher_id %>" form="delete"></td>
							<td><label for="<%= teacher_id %>"><%= teacher.getName() %></label></td>
							<td><label for="<%= teacher_id %>"><%= belongsMap.get(belongs_id) %></label></td>
							<td><label for="<%= teacher_id %>"><%= text %></label></td>
						</tr>
				<%	}	%>
				</table>

				<form action="AccountManagement" method="post" id="delete">
					<input type="hidden" name="status" value="teacher">
					<input type="submit" value="削除">
				</form>

				<form action="Main" method="get">
					<input type="submit" value="キャンセル">
				</form>
			</div>
		</div>
	</body>
</html>