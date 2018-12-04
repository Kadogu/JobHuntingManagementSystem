<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bin.Cast" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>報告書一覧 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		String search = (String)request.getParameter("search");

		String myreport = (String)request.getParameter("myreport");

		int fy = 0;
		String company_name = null;

		if("search".equals(search)){	//検索された場合
			fy = (int)request.getAttribute("fy");
			company_name = (String)request.getAttribute("company_name");
		}

		ArrayList<String> file_nameList = Cast.autoCast(request.getAttribute("file_nameList"));
	%>
		<div>
		<%	if(!"myreport".equals(myreport)){	%>
				<div>
					<form action="Report" method="get">
						<input type="hidden" name="status" value="reading">
						<input type="hidden" name="myreport" value="myreport">
						<input type="submit" value="自分のを見る">
					</form>
				</div>
		<%	}	%>

			<div>
				<form action="Main" method="post">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<div>
			<%	if("search".equals(search)){	%>
					<h1>検索結果</h1>
			<%	}else if("myreport".equals(myreport)){	%>
					<h1>あなたの報告書</h1>
			<%	}else{	%>
					<h1>報告書一覧</h1>
			<%	}	%>

			<%	if(!"myreport".equals(myreport)){	%>
					<form action="Report" method="get">
						<label>
							<select name="year">
							<%	if("search".equals(search)){	%>
									<option></option>
							<%	}else{	%>
									<option selected></option>
							<%
								}

								LocalDate today = LocalDate.now();
								int year = today.getYear();
								int month = today.getMonthValue();

								if(month <= 3){	//1～3月の場合
									year--;
								}

								for(int i = year; i >= 2014; i--){
									if(fy == i){
							%>
										<option selected><%= i %></option>
							<%		}else{	%>
										<option><%= i %></option>
							<%
									}
								}
							%>
							</select>
						年度</label>

						<%	if("search".equals(search) && company_name != null){	%>
								<input type="search" name="company_name" value="<%= company_name %>">
						<%	}else{	%>
								<input type="search" name="company_name" placeholder="会社名">
						<%	}	%>

						<input type="hidden" name="status" value="reading">
						<input type="hidden" name="search" value="search">
						<input type="submit" value="検索">
					</form>
			<%	}	%>

				<div>
					<p>新しい順</p>
				</div>

				<div>
				<%	if(file_nameList.size() == 1){	%>
						<form action="Report" method="post" name="report" target="_blank">
							<input type="hidden" name="status" value="reading">
							<input type="hidden" name="filename" value="<%= file_nameList.get(0) %>">
							<a href="javascript:report.submit()"><%= file_nameList.get(0) %></a>
						</form>
				<%	}else{
						for(int i = 0; i < file_nameList.size(); i++){
				%>
							<form action="Report" method="post" name="report" target="_blank">
								<input type="hidden" name="status" value="reading">
								<input type="hidden" name="filename" value="<%= file_nameList.get(i) %>">
								<a href="javascript:report[<%= i %>].submit()"><%= file_nameList.get(i) %></a>
							</form>
				<%
						}
					}
				%>
				</div>

			<%	if("search".equals(search) || "myreport".equals(myreport)){	%>
					<form action="Report" method="get">
						<input type="hidden" name="status" value="reading">
						<input type="submit" value="一覧へ">
					</form>
			<%	}	%>

				<form action="Main" method="get">
					<input type="submit" value="メインページへ">
				</form>
			</div>
		</div>
	</body>
</html>