<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.PDFDAO" %>
<%@ page import="dto.PDF" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>報告書作成(3/3) | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		String remake = (String)session.getAttribute("remake");
		PDF report = new PDF();
		if("remake".equals(remake)){	//続きから作成の場合
			String pdf_id = (String)session.getAttribute("pdf_id");
			report = PDFDAO.getReport3(pdf_id);
		}
	%>
		<div class="main">
			<h1 class="m-t50 m-b30">報告書3/3</h1>

			<label for="contents" class="v-align_top">試験の内容:</label>
			<%
				String content_of_test = report.getContent_of_test();
				if("remake".equals(remake) && content_of_test != null){	//続きから作成の場合
			%>
					<textarea form="next" id="contents" name="content_of_test" cols="40" rows="20" maxlength="800" wrap="soft" placeholder="改行が反映されるので適宜改行を入れて下さい。"><%= report.getContent_of_test() %></textarea>
			<%
				}else{	//新規作成の場合
			%>
					<textarea form="next" id="contents" name="content_of_test" cols="40" rows="20" maxlength="800" wrap="soft" placeholder="改行が反映されるので適宜改行を入れて下さい。"></textarea>
			<%	}	%>
			<br><br>

			<label for="advice" class="v-align_top">後輩へのアドバイス:</label>
			<%
				String advice_to_junior = report.getAdvice_to_junior();
				if("remake".equals(remake) && advice_to_junior != null){	//続きから作成の場合
			%>
					<textarea form="next" id="advice" name="advice_to_junior" cols="40" rows="3" maxlength="120"  wrap="soft" placeholder="3行までにして下さい。&#13;&#10;3行以上は表示されません。"><%= report.getAdvice_to_junior() %></textarea>
			<%
				}else{	//新規作成の場合
			%>
					<textarea form="next" id="advice" name="advice_to_junior" cols="40" rows="3" maxlength="120"  wrap="soft" placeholder="3行までにして下さい。&#13;&#10;3行以上は表示されません。"></textarea>
			<%	}	%>
			<br>

			<form action="Main" method="get">
					<input type="submit" value="メインページへ" class="m-t30 m-r80">
			</form>

			<form action="Report" method="post" id="next">
				<input type="hidden" name="status" value="creating">
				<input type="hidden" name="report" value="3">
				<input type="submit" value="次へ" class="m-t30">
			</form>
		</div>
	</body>
</html>