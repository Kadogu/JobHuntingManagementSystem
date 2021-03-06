<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.PDFDAO" %>
<%@ page import="dao.StudentDAO" %>
<%@ page import="dto.Student" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		int student_id = (int)session.getAttribute("student_id");
		String name = StudentDAO.getName(student_id);

		int count = PDFDAO.countReportList(student_id);
	%>
		<div class="m-b80">
			<div class="left m-t10 m-l10">
				<p><%= name %>さん</p>
			</div>

			<div class="right m-t10 m-r10">
				<form action="RegistrationContentsChange" method="get">
					<input type="submit" value="登録内容変更">
				</form>

				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>
		</div>

		<div class="main">
			<div class="main_page">
				<h1 class="m-b10">就活報告書</h1>
				<p class="m-b10"><a href="<%= request.getContextPath() %>/Company?status=choice&use=report">新規作成</a></p>
				<p class="m-b10"><a href="<%= request.getContextPath() %>/Report?status=remake">続きから作成<%= count %>件</a></p>
				<p><a href="<%= request.getContextPath() %>/Report?status=reading">閲覧</a></p>
			</div>

			<div class="main_page">
				<div>
					<h1 class="m-b10">応募書類申請届出書</h1>
					<p class="m-b10"><a href="<%= request.getContextPath() %>/Company?status=choice&use=application">作成</a></p>
					<p class="m-b30"><a href="<%= request.getContextPath() %>/ApplicationForm?status=reading">閲覧</a></p>
				</div>

				<div>
					<h1 class="m-b10">会社</h1>
					<p class="m-b10"><a href="<%= request.getContextPath() %>/Company?status=add">追加</a></p>
					<p class="m-b10"><a href="<%= request.getContextPath() %>/Company?status=edit">編集</a></p>
					<p><a href="<%= request.getContextPath() %>/Company?status=confirmation">確認</a></p>
				</div>
			</div>
		</div>
	</body>
</html>