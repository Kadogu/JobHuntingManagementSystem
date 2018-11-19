<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bin.Cast" %>
<%@ page import="dto.Belongs" %>
<%@ page import="dto.Course" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>教師データ登録 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		ArrayList<Belongs> belongsList = Cast.autoCast(request.getAttribute("belongsList"));
		ArrayList<Course> courseList = Cast.autoCast(request.getAttribute("courseList"));
	%>
		<div>
			<h1>教師データ登録</h1>

			<form action="UserDataRegistration" method="post">
				<label>氏名:<input type="text" name="name" required></label><br>

				<label>所属:
					<select name="belongs" required>
						<option disabled selected></option>
						<%	for(Belongs belongs : belongsList){	%>
								<option value="<%= belongs.getBelongs_id() %>"><%= belongs.getBelongs_name() %></option>
						<%	}	%>
					</select>
				</label><br>

				<p>担当クラス:
					<select name="charge_class">
						<option disabled selected></option>
						<%
							for(Course course : courseList){
								for(int i = 1; i <= course.getYear(); i++){
						%>
									<option value="<%= course.getCourse_id() + i %>"><%= course.getCourse_name() + i + "年" %></option>
						<%
								}
							}
						%>
					</select>
				</p>
				<input type="submit" value="追加"><br>

				<label>メールアドレス:<input type="email" name="mail_address" required></label><br>
				<p>※アカウントロックの通知などに使用します</p>

				<label>ハンコ画像:<input type="file" name="image_filename" required></label><br>

				<input type="submit" value="登録">
			</form>

			<form action="" method="">
				<input type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>