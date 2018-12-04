<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="bin.Cast" %>
<%@ page import="dao.BelongsDAO" %>
<%@ page import="dto.Teacher" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>権限変更 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		ArrayList<Teacher> teachers = Cast.autoCast(session.getAttribute("teachers"));
		HashMap<String, String> map = BelongsDAO.getBelongsMap();
	%>
		<div>
			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>システム管理者権限</h1>

				<form action="AccountManagement" method="post">
					<table>
						<tr>
							<th>氏名</th>
							<th>所属</th>
							<th>権限</th>
						</tr>

					<%	for(Teacher teacher : teachers){	%>
							<tr>
								<td><%= teacher.getName() %></td>

								<td><%= map.get(teacher.getBelongs_id()) %></td>

								<td>
								<%	if(teacher.isAdmin_flg()){	%>
										<label><input type="radio" name="<%= teacher.getTeacher_id() %>" value="true" checked="checked">有</label>
										<label><input type="radio" name="<%= teacher.getTeacher_id() %>" value="false">無</label>
								<%	}else{	%>
										<label><input type="radio" name="<%= teacher.getTeacher_id() %>" value="true">有</label>
										<label><input type="radio" name="<%= teacher.getTeacher_id() %>" value="false" checked="checked">無</label>
								<%	}	%>
								</td>
							</tr>
					<%	}	%>
					</table>

					<input type="hidden" name="status" value="authority_change">
					<input type="submit" value="確定">
				</form>

				<form action="Main" method="get">
					<input type="submit" value="キャンセル">
				</form>
			</div>
		</div>
	</body>
</html>