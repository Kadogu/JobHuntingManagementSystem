<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>学生関連 | モリジョビ就活管理システム</title>
	</head>
	<body>
		<div>
			<div>
				<form action="" method="">
					<input type="submit" value="メインページ">
				</form>
			</div>

			<div>
				<form action="" method="">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>学生関連</h1>

				<h2>学籍番号</h2>
				<p><a href="<%= request.getContextPath() %>/StudentRelation?status=student_id_add">追加</a></p>
				<p><a href="<%= request.getContextPath() %>/StudentRelation?status=student_id_confirmation">確認</a></p>

				<h2><a href="<%= request.getContextPath() %>/StudentRelation?status=repetition_list">留年リスト</a></h2>
			</div>
		</div>
	</body>
</html>