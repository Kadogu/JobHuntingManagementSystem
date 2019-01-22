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
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		String search = (String)request.getAttribute("search");

		String delete = (String)request.getAttribute("delete");

		ArrayList<Teacher> teacherList = Cast.autoCast(request.getAttribute("teacherList"));

		HashMap<String, String> belongsMap = BelongsDAO.getBelongsMap();
	%>
		<div class="logout">
			<div class="right m-r30">
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>
		</div>

		<div class="main">
			<h1 class="m-b10">教師削除</h1>

			<form action="AccountManagement" method="get" class="search m-b10">
			<%	if(search == null){	%>
					<input type="search" name="search" placeholder="氏名を入力">
			<%	}else{	%>
					<input type="search" name="search" placeholder="氏名を入力" value="<%= search %>">
			<%	}	%>

				<input type="hidden" name="status" value="teacher">
				<input type="submit" value="検索">
			</form>

		<%	if(delete != null){	%>
				<p class="m-b10"><span><%= delete %></span></p>
		<%	}	%>

			<table>
				<tr>
					<th></th>
					<th>氏名</th>
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
						<td><input type="checkbox" form="delete" name="teacher_id" value="<%= teacher_id %>" id="<%= teacher_id %>"></td>
						<td><label class="not" for="<%= teacher_id %>"><%= teacher.getName() %></label></td>
						<td><label class="not" for="<%= teacher_id %>"><%= belongsMap.get(belongs_id) %></label></td>
						<td><label class="not" for="<%= teacher_id %>"><%= text %></label></td>
					</tr>
			<%	}	%>
			</table>

			<form action="Main" method="get">
				<input type="submit" value="キャンセル" class="m-t30 m-b30 m-r80">
			</form>

			<form action="AccountManagement" method="post" id="delete">
				<input type="hidden" name="status" value="teacher">
				<input type="submit" value="削除" class="m-t30 m-b30">
			</form>
		</div>
	</body>
</html>