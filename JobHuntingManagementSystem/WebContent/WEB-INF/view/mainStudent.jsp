<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="dao.StudentDAO" %>
<%@ page import="dto.Student" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		int student_id = (int)session.getAttribute("student_id");
		String name = StudentDAO.getName(student_id);
	%>
		<div>
			<p><%= name %>さん</p>
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
				<h2>就活報告書</h2>
				<p><a href="<%= request.getContextPath() %>/Company?status=choice&use=report">新規作成</a></p>
				<p><a href="<%= request.getContextPath() %>">続きから作成</a></p>
				<p><a href="<%= request.getContextPath() %>">閲覧</a></p>
			</div>

			<div>
				<h2>カレンダー</h2>
				<p><a href="<%= request.getContextPath() %>">予定確認</a></p>
				<p><a href="<%= request.getContextPath() %>">公欠届作成</a></p>
			</div>

			<div>
				<h2>応募書類申請届出書</h2>
				<p><a href="<%= request.getContextPath() %>/Company?status=choice&use=application">作成</a></p>
				<p><a href="<%= request.getContextPath() %>/ApplicationForm?status=reading">閲覧</a></p>
			</div>

			<div>
				<h2>会社</h2>
				<p><a href="<%= request.getContextPath() %>/Company?status=add">追加</a></p>
				<p><a href="<%= request.getContextPath() %>/Company?status=edit">編集</a></p>
				<p><a href="<%= request.getContextPath() %>/Company?status=confirmation">確認</a></p>
			</div>

			<a href="<%= request.getContextPath() %>/Contact">お問い合わせ</a>
		</div>
	</body>
</html>