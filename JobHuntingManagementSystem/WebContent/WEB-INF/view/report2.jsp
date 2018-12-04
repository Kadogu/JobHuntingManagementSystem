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
		<div>
			<h1>報告書2/3</h1>

			<form action="Report" method="post">
			<%
				String[] n = {"一","二","三"};
				for(int i = 1; i <= 3; i++){
			%>
					<h2><%= n[i - 1] %>次試験</h2>

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
						<label>日付:<input type="date" name="start_date<%= i %>" value="<%= date %>"></label><br>
			<%			}else{	%>
							<label>日付:<input type="date" name="start_date<%= i %>"></label><br>
			<%}
					}else{
			%>
						<label>日付:<input type="date" name="start_date<%= i %>"></label><br>
			<%		}	%>

					<p>試験時間:
						<label>
							<select name="start_hour<%= i %>">
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

						<label>
							<select name="start_minute<%= i %>">
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

						<label>
							<select name="last_hour<%= i %>">
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

						<label>
							<select name="last_minute<%= i %>">
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
					</p>

					<p>試験区分:
					<%
						boolean test_categoryFlg = false;
						if("remake".equals(remake) && contents_test != null){
							String test_category = contents_test.getTest_category();
							if("筆記,面接".equals(test_category)){
								test_categoryFlg = true;
					%>
								<label><input type="checkbox" name="test_category<%= i %>" value="writing" checked="checked">筆記</label>
								<label><input type="checkbox" name="test_category<%= i %>" value="interview" checked="checked">面接</label>
					<%		}else if("筆記".equals(test_category)){	%>
								<label><input type="checkbox" name="test_category<%= i %>" value="writing" checked="checked">筆記</label>
								<label><input type="checkbox" name="test_category<%= i %>" value="interview">面接</label>
					<%
							}else if("面接".equals(test_category)){
								test_categoryFlg = true;
					%>
								<label><input type="checkbox" name="test_category<%= i %>" value="writing">筆記</label>
								<label><input type="checkbox" name="test_category<%= i %>" value="interview" checked="checked">面接</label>
					<%		}else{	%>
								<label><input type="checkbox" name="test_category<%= i %>" value="writing">筆記</label>
								<label><input type="checkbox" name="test_category<%= i %>" value="interview">面接</label>
					<%
							}
						}else{
					%>
							<label><input type="checkbox" name="test_category<%= i %>" value="writing">筆記</label>
							<label><input type="checkbox" name="test_category<%= i %>" value="interview">面接</label>
					<%	}%>
					</p>

					<p>筆記試験内容:
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
								<label><input type="checkbox" name="writing<%= i %>" value="<%= writingList[j] %>" checked="checked"><%= writingList[j] %></label>
					<%		}else{	%>
								<label><input type="checkbox" name="writing<%= i %>" value="<%= writingList[j] %>"><%= writingList[j] %></label>
					<%
							}
						}
						if(writing != null && writing.matches(".*その他.*")){
							int start = writing.indexOf("その他(");
							String other = writing.substring(start + 4, writing.length() - 1);
					%>
							<label><input type="checkbox" name="writing<%= i %>" value="other" checked="checked">その他</label>
							<input type="text" name="other<%= i %>" value="<%= other %>">
					<%	}else{	%>
							<label><input type="checkbox" name="writing<%= i %>" value="other">その他</label>
							<input type="text" name="other<%= i %>">
					<%	}	%>
					</p>

					<%
						int view_time = 0;
						if("remake".equals(remake) && contents_test != null && test_categoryFlg){
							view_time = contents_test.getView_time();
					%>
							<label>面接時間:<input type="number" name="view_time<%= i %>" min="5" max="180" step="5" value="<%= view_time %>">分間</label><br>
					<%	}else{	%>
							<label>面接時間:<input type="number" name="view_time<%= i %>" min="5" max="180" step="5">分間</label><br>
					<%	}	%>

					<%
						int viewer_no = 0;
						if("remake".equals(remake) && contents_test != null && test_categoryFlg){
							viewer_no = contents_test.getViewer_no();
					%>
							<label>面接官:<input type="number" name="viewer_no<%= i %>" min="1" max="10" value="<%= viewer_no %>">名</label><br>
					<%	}else{	%>
							<label>面接官:<input type="number" name="viewer_no<%= i %>" min="1" max="10">名</label><br>
					<%	}	%>

					<p>面接試験内容:
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
								<label><input type="radio" name="view_content<%= i %>" value="個人面接" checked="checked">個人面接</label>
								<label><input type="radio" name="view_content<%= i %>" value="groop_interview">集団面接</label>
								<label><input type="number" name="groop_no<%= i %>" min="2" max="10">名</label>
								<label><input type="radio" name="view_content<%= i %>" value="groop_discussion">グループディスカッション</label>
								<label><input type="number" name="discussion_no<%= i %>" min="2" max="15">名</label>
					<%		}else if("集団面接".equals(view_content)){	%>
								<label><input type="radio" name="view_content<%= i %>" value="個人面接">個人面接</label>
								<label><input type="radio" name="view_content<%= i %>" value="groop_interview" checked="checked">集団面接</label>
								<label><input type="number" name="groop_no<%= i %>" min="2" max="10" value="<%= groop_no %>">名</label>
								<label><input type="radio" name="view_content<%= i %>" value="groop_discussion">グループディスカッション</label>
								<label><input type="number" name="discussion_no<%= i %>" min="2" max="15">名</label>
					<%		}else if("グループディスカッション".equals(view_content)){	%>
								<label><input type="radio" name="view_content<%= i %>" value="個人面接">個人面接</label>
								<label><input type="radio" name="view_content<%= i %>" value="groop_interview">集団面接</label>
								<label><input type="number" name="groop_no<%= i %>" min="2" max="10">名</label>
								<label><input type="radio" name="view_content<%= i %>" value="groop_discussion" checked="checked">グループディスカッション</label>
								<label><input type="number" name="discussion_no<%= i %>" min="2" max="15" value="<%= discussion_no %>">名</label>
					<%		}else{	%>
								<label><input type="radio" name="view_content<%= i %>" value="個人面接">個人面接</label>
								<label><input type="radio" name="view_content<%= i %>" value="groop_interview">集団面接</label>
								<label><input type="number" name="groop_no<%= i %>" min="2" max="10">名</label>
								<label><input type="radio" name="view_content<%= i %>" value="groop_discussion">グループディスカッション</label>
								<label><input type="number" name="discussion_no<%= i %>" min="2" max="15">名</label>
					<%
							}
						}else{
					%>
							<label><input type="radio" name="view_content<%= i %>" value="個人面接">個人面接</label>
							<label><input type="radio" name="view_content<%= i %>" value="groop_interview">集団面接</label>
							<label><input type="number" name="groop_no<%= i %>" min="2" max="10">名</label>
							<label><input type="radio" name="view_content<%= i %>" value="groop_discussion">グループディスカッション</label>
							<label><input type="number" name="discussion_no<%= i %>" min="2" max="15">名</label>
					<%	}	%>
					</p>
			<%	}	%>

				<input type="hidden" name="status" value="creating">
				<input type="hidden" name="report" value="2">
				<input type="submit" value="次へ">
			</form>

			<form action="Main" method="get">
					<input type="submit" value="メインページへ">
			</form>
		</div>
	</body>
</html>