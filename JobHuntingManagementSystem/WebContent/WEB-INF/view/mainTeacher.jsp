<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.Charge_ClassDAO" %>
<%@ page import="dao.Document_ApplicationDAO" %>
<%@ page import="dao.PDFDAO" %>
<%@ page import="dao.StudentDAO" %>
<%@ page import="dao.TeacherDAO" %>
<%@ page import="dto.Charge_Class" %>
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

		int count = 0;
		int bring_count = 0;
		int mailing_count = 0;
		if("e".equals(teacher.getBelongs_id())){	//所属が就職課の場合
			count = PDFDAO.countCreatedReportList();
			bring_count = Document_ApplicationDAO.countDocument_ApplicationList(true);
			mailing_count = Document_ApplicationDAO.countDocument_ApplicationList(false);
		}else{	//所属がそれ以外の場合
			ArrayList<Charge_Class> charge_classList = Charge_ClassDAO.getCharge_ClassList(teacher_id);

			ArrayList<Integer> student_idList = new ArrayList<Integer>();
			for(Charge_Class charge_class : charge_classList){
				ArrayList<Integer> list = StudentDAO.getStudent_idList(charge_class.getCourse_id(), charge_class.getSchool_year());
				student_idList.addAll(list);
			}

			for(Integer student_id : student_idList){
				count += PDFDAO.countCreatedReportList(student_id);
				bring_count += Document_ApplicationDAO.countDocument_ApplicationList(true, student_id);
				mailing_count += Document_ApplicationDAO.countDocument_ApplicationList(false, student_id);
			}
		}
	%>
		<div>
			<div>
				<%	if(teacher.isAdmin_flg()){	%>
						<p>管理者:<%= teacher.getName() %></p>
				<%	}else{	%>
						<p><%= teacher.getName() %>先生</p>
				<%	}	%>
			</div>

			<div>
				<form action="RegistrationContentsChange" method="get">
					<input type="submit" value="登録内容変更">
				</form>

				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
				<div>
					<h1>就活報告書</h1>
					<p><a href="<%= request.getContextPath() %>/Report?status=choice">確認<%= count %>件</a></p>
					<p><a href="<%= request.getContextPath() %>/Report?status=reading">閲覧</a></p>
				</div>

				<div>
					<h1>応募書類申請届出書</h1>
					<p><a href="<%= request.getContextPath() %>/ApplicationForm?status=confirmation&bring=true">確認(持参)<%= bring_count %>件</a></p>
					<p><a href="<%= request.getContextPath() %>/ApplicationForm?status=confirmation&bring=false">確認(郵送)<%= mailing_count %>件</a></p>
				</div>

			<%	if(teacher.isAdmin_flg()){	%>
					<div>
						<h1>学籍番号</h1>
						<p><a href="<%= request.getContextPath() %>/StudentRelation?status=student_id_add">追加</a></p>
						<p><a href="<%= request.getContextPath() %>/StudentRelation?status=student_id_confirmation">確認</a></p>
					</div>

					<div>
						<h1>留年リスト</h1>
						<p><a href="<%= request.getContextPath() %>/Repetition_List?status=add">追加</a></p>
						<p><a href="<%= request.getContextPath() %>/Repetition_List?status=confirmation">確認</a></p>
					</div>

					<div>
						<h1>アカウント削除</h1>
						<p><a href="<%= request.getContextPath() %>/AccountManagement?status=student">生徒</a></p>
						<p><a href="<%= request.getContextPath() %>/AccountManagement?status=teacher">教師</a></p>
					</div>

					<div>
						<h1>システム管理者</h1>
						<p><a href="<%= request.getContextPath() %>/AccountManagement?status=authority_change">権限変更</a></p>
					</div>

					<p><a href="<%= request.getContextPath() %>/AccountManagement?status=log">ログ</a></p>
			<%	}	%>
			</div>
		</div>
	</body>
</html>