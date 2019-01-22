<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bin.Cast" %>
<%@ page import="dao.Contents_TestDAO" %>
<%@ page import="dto.Contents_Test" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>報告書作成(2/3) | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		String remake = (String)session.getAttribute("remake");
		ArrayList<Contents_Test> contents_testList = new ArrayList<Contents_Test>();
		if("remake".equals(remake)){	//続きから作成の場合
			String pdf_id = (String)session.getAttribute("pdf_id");
			contents_testList = Contents_TestDAO.getContents_TestList(pdf_id);
		}
		Contents_Test contents_test = null;
	%>
		<h1 class="main m-t50 m-b30">報告書2/3</h1>

		<%
			String[] n = {"一","二","三"};
			for(int i = 1; i <= 3; i++){
		%>
				<h2 class="m-b30"><%= n[i - 1] %>次試験</h2>

				<div class="main">
				<%
					if("remake".equals(remake) && contents_testList.size() >= 1){
						contents_test = contents_testList.get(i - 1);
						LocalDate start_date = contents_test.getStart_date();
						if(start_date != null){
							int year = start_date.getYear();
							int month = start_date.getMonthValue();
							int day = start_date.getDayOfMonth();
							String date = year + "-";
							if(month < 10){
								date += "0";
							}
							date += month + "-";
							if(day < 10){
								date += "0";
							}
							date += day;
				%>
							<label>日付:<input type="date" form="next" name="start_date<%= i %>" value="<%= date %>"></label><br><br>
				<%		}else{	%>
							<label>日付:<input type="date" form="next" name="start_date<%= i %>"></label><br><br>
				<%		}
					}else{
				%>
						<label>日付:<input type="date" form="next" name="start_date<%= i %>"></label><br><br>
				<%	}	%>

				<p><label>試験時間:</label>
					<label class="not">
						<select form="next" name="start_hour<%= i %>">
						<option></option>
						<%
							int start_hour = -1;
							if("remake".equals(remake) && contents_test != null){
								start_hour = contents_test.getStart_hour();
							}
							for(int h = 8; h <= 18; h++){
								if(start_hour == h){
						%>
									<option selected><%= h %></option>
						<%		}else{	%>
									<option><%= h %></option>
						<%
								}
							}
						%>
						</select>
					時</label>

					<label class="not">
						<select form="next" name="start_minute<%= i %>">
						<option></option>
						<%
							int start_minute = -1;
							if("remake".equals(remake) && contents_test != null){
								start_minute = contents_test.getStart_minute();
							}
							for(int m = 0; m < 60; m += 5){
								if(start_minute == m){
						%>
									<option selected><%= m %></option>
						<%		}else{	%>
									<option><%= m %></option>
						<%
								}
							}
						%>
						</select>
					分～</label>

					<label class="not">
						<select form="next" name="last_hour<%= i %>">
						<option></option>
						<%
							int last_hour = -1;
							if("remake".equals(remake) && contents_test != null){
								last_hour = contents_test.getLast_hour();
							}
							for(int h = 8; h <= 18; h++){
								if(last_hour == h){
						%>
									<option selected><%= h %></option>
						<%		}else{	%>
									<option><%= h %></option>
						<%
								}
							}
						%>
						</select>
					時</label>

					<label class="not">
						<select form="next" name="last_minute<%= i %>">
						<option></option>
						<%
							int last_minute = -1;
							if("remake".equals(remake) && contents_test != null){
								last_minute = contents_test.getLast_minute();
							}
							for(int m = 0; m < 60; m += 5){
								if(last_minute == m){
						%>
									<option selected><%= m %></option>
						<%		}else{	%>
									<option><%= m %></option>
						<%
								}
							}
						%>
						</select>
					分</label>
				</p><br>

				<p><label>試験区分:</label>
				<%
					boolean test_categoryFlg = false;
					if("remake".equals(remake) && contents_test != null){
						String test_category = contents_test.getTest_category();
						if("筆記,面接".equals(test_category)){
							test_categoryFlg = true;
				%>
							<label class="not"><input type="checkbox" form="next" name="test_category<%= i %>" value="writing" checked="checked">筆記</label>
							<label class="not"><input type="checkbox" form="next" name="test_category<%= i %>" value="interview" checked="checked">面接</label>
				<%		}else if("筆記".equals(test_category)){	%>
							<label class="not"><input type="checkbox" form="next" name="test_category<%= i %>" value="writing" checked="checked">筆記</label>
							<label class="not"><input type="checkbox" form="next" name="test_category<%= i %>" value="interview">面接</label>
				<%
						}else if("面接".equals(test_category)){
							test_categoryFlg = true;
				%>
							<label class="not"><input type="checkbox" form="next" name="test_category<%= i %>" value="writing">筆記</label>
							<label class="not"><input type="checkbox" form="next" name="test_category<%= i %>" value="interview" checked="checked">面接</label>
				<%		}else{	%>
							<label class="not"><input type="checkbox" form="next" name="test_category<%= i %>" value="writing">筆記</label>
							<label class="not"><input type="checkbox" form="next" name="test_category<%= i %>" value="interview">面接</label>
				<%
						}
					}else{
				%>
						<label class="not"><input type="checkbox" form="next" name="test_category<%= i %>" value="writing">筆記</label>
						<label class="not"><input type="checkbox" form="next" name="test_category<%= i %>" value="interview">面接</label>
				<%	}%>
				</p><br>

				<p><label>筆記試験内容:</label>
				<%
					String[] writingList = {"一般常識","SPI","適性(性格)","作文"};
					String writing = null;
					int[] writingFlg = new int[writingList.length];
					if("remake".equals(remake) && contents_test != null){
						writing = contents_test.getWriting();
						if(writing != null){
							if(writing.matches("一般常識.*")){
								writingFlg[0] = 1;
							}
							if(writing.matches(".*SPI.*")){
								writingFlg[1] = 1;
							}
							if(writing.matches(".*適性\\(性格\\).*")){
								writingFlg[2] = 1;
							}
							if(writing.matches(".*作文.*")){
								writingFlg[3] = 1;
							}
						}
					}
					for(int j = 0; j < writingList.length; j++){
						if(writingFlg[j] == 1){
				%>
							<label class="not"><input type="checkbox" form="next" name="writing<%= i %>" value="<%= writingList[j] %>" checked="checked"><%= writingList[j] %></label>
				<%		}else{	%>
							<label class="not"><input type="checkbox" form="next" name="writing<%= i %>" value="<%= writingList[j] %>"><%= writingList[j] %></label>
				<%
						}
					}
					if(writing != null && writing.matches(".*その他.*")){
						int start = writing.indexOf("その他(");
						String other = writing.substring(start + 4, writing.length() - 1);
				%>
						<label class="not"><input type="checkbox" form="next" name="writing<%= i %>" value="other" checked="checked">その他</label>
						<input type="text" form="next" class="number" name="other<%= i %>" value="<%= other %>">
				<%	}else{	%>
						<label class="not"><input type="checkbox" form="next" name="writing<%= i %>" value="other">その他</label>
						<input type="text" form="next" class="number" name="other<%= i %>">
				<%	}	%>
				</p><br>

				<%
					int view_time = 0;
					if("remake".equals(remake) && contents_test != null && test_categoryFlg){
						view_time = contents_test.getView_time();
				%>
						<label for="time">面接時間:</label><input type="number" form="next" id="time" name="view_time<%= i %>" min="5" max="180" step="5" value="<%= view_time %>"><label class="not" for="time">分間</label><br><br>
				<%	}else{	%>
						<label for="time">面接時間:</label><input type="number" form="next" id="time" name="view_time<%= i %>" min="5" max="180" step="5"><label class="not" for="time">分間</label><br><br>
				<%	}	%>

				<%
					int viewer_no = 0;
					if("remake".equals(remake) && contents_test != null && test_categoryFlg){
						viewer_no = contents_test.getViewer_no();
				%>
						<label for="number">面接官:</label><input type="number" form="next" id="number" name="viewer_no<%= i %>" min="1" max="10" value="<%= viewer_no %>"><label class="not" for="number">名</label><br><br>
				<%	}else{	%>
						<label for="number">面接官:</label><input type="number" form="next" id="number" name="viewer_no<%= i %>" min="1" max="10"><label class="not" for="number">名</label><br><br>
				<%	}	%>

				<p><label>面接試験内容:</label>
				<%
					String view_content = null;
					int groop_no = 0;
					int discussion_no = 0;
					if("remake".equals(remake) && contents_test != null){
						view_content = contents_test.getView_content();
						groop_no = contents_test.getGroop_no();
						discussion_no = contents_test.getDiscussion_no();
						if("個人面接".equals(view_content)){
				%>
							<label class="not"><input type="radio" form="next" name="view_content<%= i %>" value="個人面接" checked="checked">個人面接</label>
							<label class="not"><input type="radio" form="next" name="view_content<%= i %>" value="groop_interview">>集団面接</label>
							<label class="not"><input type="number" form="next" class="number" name="groop_no<%= i %>" min="2" max="10">名</label>
							<label class="not"><input type="radio" form="next" name="view_content<%= i %>" value="groop_discussion">グループディスカッション</label>
							<label class="not"><input type="number" form="next" class="number" name="discussion_no<%= i %>" min="2" max="15">名</label>
				<%		}else if("集団面接".equals(view_content)){	%>
							<label class="not"><input type="radio" form="next" name="view_content<%= i %>" value="個人面接">個人面接</label>
							<label class="not"><input type="radio" form="next" name="view_content<%= i %>" value="groop_interview" checked="checked">集団面接</label>
							<label class="not"><input type="number" form="next" class="number" name="groop_no<%= i %>" min="2" max="10" value="<%= groop_no %>">名</label>
							<label class="not"><input type="radio" form="next" name="view_content<%= i %>" value="groop_discussion">グループディスカッション</label>
							<label class="not"><input type="number" form="next" class="number" name="discussion_no<%= i %>" min="2" max="15">名</label>
				<%		}else if("グループディスカッション".equals(view_content)){	%>
							<label class="not"><input type="radio" form="next" name="view_content<%= i %>" value="個人面接">個人面接</label>
							<label class="not"><input type="radio" form="next" name="view_content<%= i %>" value="groop_interview">>集団面接</label>
							<label class="not"><input type="number" form="next" class="number" name="groop_no<%= i %>" min="2" max="10">名</label>
							<label class="not"><input type="radio" form="next" name="view_content<%= i %>" value="groop_discussion" checked="checked">グループディスカッション</label>
							<label class="not"><input type="number" form="next" class="number" name="discussion_no<%= i %>" min="2" max="15" value="<%= discussion_no %>">名</label>
				<%		}else{	%>
							<label class="not"><input type="radio" form="next" name="view_content<%= i %>" value="個人面接">個人面接</label>
							<label class="not"><input type="radio" form="next" name="view_content<%= i %>" value="groop_interview">>集団面接</label>
							<label class="not"><input type="number" form="next" class="number" name="groop_no<%= i %>" min="2" max="10">名</label>
							<label class="not"><input type="radio" form="next" name="view_content<%= i %>" value="groop_discussion">グループディスカッション</label>
							<label class="not"><input type="number" form="next" class="number" name="discussion_no<%= i %>" min="2" max="15">名</label>
				<%
						}
					}else{
				%>
						<label class="not"><input type="radio" form="next" name="view_content<%= i %>" value="個人面接">個人面接</label>
						<label class="not"><input type="radio" form="next" name="view_content<%= i %>" value="groop_interview">集団面接</label>
						<label class="not"><input type="number" form="next" class="number" name="groop_no<%= i %>" min="2" max="10">名</label>
						<label class="not"><input type="radio" form="next" name="view_content<%= i %>" value="groop_discussion">グループディスカッション</label>
						<label class="not"><input type="number" form="next" class="number" name="discussion_no<%= i %>" min="2" max="15">名</label>
				<%	}	%>
				</p><br>
				</div>
		<%	}	%>

		<div class="main">
			<form action="Main" method="get">
					<input type="submit" value="メインページへ" class="m-t30 m-b30 m-r80">
			</form>

			<form action="Report" method="post" id="next">
				<input type="hidden" name="status" value="creating">
				<input type="hidden" name="report" value="2">
				<input type="submit" value="次へ" class="m-t30 m-b30">
			</form>
		</div>
	</body>
</html>