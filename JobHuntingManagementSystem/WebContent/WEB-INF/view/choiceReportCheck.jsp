<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.Charge_ClassDAO" %>
<%@ page import="dao.PDFDAO" %>
<%@ page import="dao.StudentDAO" %>
<%@ page import="dao.TeacherDAO" %>
<%@ page import="dto.Charge_Class" %>
<%@ page import="dto.PDF" %>
<%@ page import="dto.Teacher" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>報告書選択 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		int teacher_id = (int)session.getAttribute("teacher_id");
		Teacher teacher = TeacherDAO.getTeacher(teacher_id);

		ArrayList<PDF> createdReportList;

		if("e".equals(teacher.getBelongs_id())){	//所属が就職課の場合
			createdReportList = PDFDAO.getCreatedReportList();
		}else{	//所属がそれ以外の場合
			ArrayList<Charge_Class> charge_classList = Charge_ClassDAO.getCharge_ClassList(teacher_id);

			ArrayList<Integer> student_idList = new ArrayList<Integer>();
			for(Charge_Class charge_class : charge_classList){
				ArrayList<Integer> list = StudentDAO.getStudent_idList(charge_class.getCourse_id(), charge_class.getSchool_year());
				student_idList.addAll(list);
			}

			createdReportList = new ArrayList<PDF>();
			for(Integer student_id : student_idList){
				ArrayList<PDF> list = PDFDAO.getCreatedReportList(student_id);
				createdReportList.addAll(list);
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
				<h1>確認したい報告書を選択</h1>

			<%
				if(createdReportList.size() == 1){
					PDF report = createdReportList.get(0);
			%>
					<form action="Report" method="post" name="report">
						<input type="hidden" name="status" value="choice">
						<input type="hidden" name="pdf_id" value="<%= report.getPdf_id() %>">
						<a href="javascript:report.submit()"><%= report.getFile_name() %></a>
					</form>
			<%
				}else{
					for(int i = 0; i < createdReportList.size(); i++){
						PDF report = createdReportList.get(i);
			%>
						<form action="Report" method="post" name="report">
							<input type="hidden" name="status" value="choice">
							<input type="hidden" name="pdf_id" value="<%= report.getPdf_id() %>">
							<a href="javascript:report[<%= i %>].submit()"><%= report.getFile_name() %></a>
						</form>
			<%
					}
				}
			%>

				<form action="Main" method="get">
					<input type="submit" value="キャンセル">
				</form>
			</div>
		</div>
	</body>
</html>