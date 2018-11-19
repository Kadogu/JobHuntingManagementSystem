<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.Document_ApplicationDAO" %>
<%@ page import="dao.TeacherDAO" %>
<%@ page import="dto.Document_Application" %>
<%@ page import="dto.Teacher" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		int teacher_id = (int)session.getAttribute("teacher_id");
		Teacher teacher = TeacherDAO.getTeacher(teacher_id);

		ArrayList<Document_Application> bringDocument_applicationList = Document_ApplicationDAO.getDocument_ApplicationList(true, teacher_id);
		session.setAttribute("bringDocument_applicationList", bringDocument_applicationList);

		ArrayList<Document_Application> mailingDocument_applicationList = Document_ApplicationDAO.getDocument_ApplicationList(false, teacher_id);
		session.setAttribute("mailingDocument_applicationList", mailingDocument_applicationList);
	%>
		<div>
			<% if(teacher.isAdmin_flg()){ %>
				<p>管理者:<%= teacher.getName() %></p>
			<% }else{ %>
				<p><%= teacher.getName() %>先生</p>
			<% } %>
		</div>

		<div>
			<form action="" method="">
				<input type="submit" value="登録内容変更">
			</form>

			<form action="" method="">
				<input type="submit" value="ログアウト">
			</form>
		</div>

		<div>
			<div>
				<h1>就活報告書</h1>
				<p><a href="<%= request.getContextPath() %>/">確認○件</a></p>
				<p><a href="<%= request.getContextPath() %>/">閲覧</a></p>
			</div>

			<div>
				<h1>カレンダー</h1>
				<% if("e".equals(teacher.getBelongs_id())){ %>
					<p><a href="<%= request.getContextPath() %>/">学校案内</a></p>
				<% }else{ %>
					<p><a href="<%= request.getContextPath() %>/">公欠届承認○件</a></p>
				<% } %>
				<p><a href="<%= request.getContextPath() %>/">予定確認</a></p>
			</div>

			<div>
				<h1>応募書類申請届出書</h1>
				<p><a href="<%= request.getContextPath() %>/ApplicationForm?status=confirmation&bring=true">確認(持参)<%= bringDocument_applicationList.size() %>件</a></p>
				<p><a href="<%= request.getContextPath() %>/ApplicationForm?status=confirmation&bring=false">確認(郵送)<%= mailingDocument_applicationList.size() %>件</a></p>
			</div>

			<div>
				<h1>就活リスト</h1>
				<p><a href="<%= request.getContextPath() %>/">閲覧</a></p>
			</div>

			<div>
				<% if(teacher.isAdmin_flg()){ %>
					<a href="<%= request.getContextPath() %>/StudentRelation?status=student_relation">学生関連</a>&nbsp;
					<a href="<%= request.getContextPath() %>/AccountManagement?status=account_management">アカウント管理</a>&nbsp;
					<a href="<%= request.getContextPath() %>/">システム管理</a>
				<% }else{ %>
					<a href="<%= request.getContextPath() %>/Contact">お問い合わせ</a>
				<% } %>
			</div>
		</div>
	</body>
</html>