<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.Charge_ClassDAO" %>
<%@ page import="dao.PDFDAO" %>
<%@ page import="dao.StudentDAO" %>
<%@ page import="dao.TeacherDAO" %>
<%@ page import="dto.Charge_Class" %>
<%@ page import="dto.Teacher" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>修正指示通知完了 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		int teacher_id = (int)session.getAttribute("teacher_id");
		Teacher teacher = TeacherDAO.getTeacher(teacher_id);

		int count = 0;
		if("e".equals(teacher.getBelongs_id())){	//所属が就職課の場合
			count = PDFDAO.countCreatedReportList();
		}else{	//所属がそれ以外の場合
			ArrayList<Charge_Class> charge_classList = Charge_ClassDAO.getCharge_ClassList(teacher_id);

			ArrayList<Integer> student_idList = new ArrayList<Integer>();
			for(Charge_Class charge_class : charge_classList){
				ArrayList<Integer> list = StudentDAO.getStudent_idList(charge_class.getCourse_id(), charge_class.getSchool_year());
				student_idList.addAll(list);
			}

			for(Integer student_id : student_idList){
				count += PDFDAO.countCreatedReportList(student_id);
			}
		}
	%>
		<div>
			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<h1>修正指示通知完了</h1>

				<p>生徒に通知が完了しました。</p>

			<%	if(count > 0){	%>
					<p>続けて報告書の確認を行う場合は「確認」ボタンをクリックして下さい。</p>
			<%	}	%>

				<form action="Report" method="get">
					<input type="hidden" name="status" value="choice">
					<input type="submit" value="確認<%= count %>件">
				</form>

				<form action="Main" method="get">
						<input type="submit" value="メインページへ">
				</form>
			</div>
		</div>
	</body>
</html>