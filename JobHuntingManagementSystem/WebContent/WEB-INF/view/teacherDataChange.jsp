<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bin.Cast" %>
<%@ page import="dto.Belongs" %>
<%@ page import="dto.Charge_Class" %>
<%@ page import="dto.Course" %>
<%@ page import="dto.Teacher" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>登録内容変更 | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		Teacher teacher = (Teacher)request.getAttribute("teacher");
		ArrayList<Belongs> belongsList = Cast.autoCast(request.getAttribute("belongsList"));
		ArrayList<Course> courseList = Cast.autoCast(request.getAttribute("courseList"));
		ArrayList<String> charge_classList = Cast.autoCast(request.getAttribute("charge_classList"));
	%>
		<div class="main">
			<h1 class="m-t50 m-b30">登録内容変更</h1>

			<label>氏名<span>必須</span>:<input type="text" form="change" name="name" size="16" maxlength="16" value="<%= teacher.getName() %>" required></label><br><br>

			<label>所属<span>必須</span>:
				<select form="change" class="m-l30" name="belongs" required>
					<%
						for(Belongs belongs : belongsList){
							if(belongs.getBelongs_id().equals(teacher.getBelongs_id())){
					%>
								<option value="<%= belongs.getBelongs_id() %>" selected><%= belongs.getBelongs_name() %></option>
					<%		}else{	%>
								<option value="<%= belongs.getBelongs_id() %>"><%= belongs.getBelongs_name() %></option>
					<%
							}
						}
					%>
				</select>
			</label><br><br>

			<p><label class="v-align_top">担当クラス(複数選択可)<span>必須</span>:</label>
				<select form="change" class="m-l30"  name="charge_class" multiple size="5" required>
					<option value="-">担当クラスがない場合はこちら</option>
					<%
						for(Course course : courseList){
							for(int i = 1; i <= course.getYear(); i++){
								String c_c = course.getCourse_id() + i;
								if(charge_classList.indexOf(c_c) != -1){
					%>
									<option value="<%= c_c %>" selected><%= course.getCourse_name() + i + "年" %></option>
					<%			}else{	%>
									<option value="<%= c_c %>"><%= course.getCourse_name() + i + "年" %></option>
					<%
								}
							}
						}
					%>
				</select>
			</p><br><br>

			<label>メールアドレス<span>必須</span>:<input type="email" form="change" name="mail_address" value="<%= teacher.getMail_address() %>" required></label><br>

			<form action="Main" method="get">
				<input type="submit" value="キャンセル" class="m-t30 m-r80">
			</form>

			<form action="RegistrationContentsChange" method="post" id="change">
				<input type="submit" value="変更" class="m-t30">
			</form>
		</div>
	</body>
</html>