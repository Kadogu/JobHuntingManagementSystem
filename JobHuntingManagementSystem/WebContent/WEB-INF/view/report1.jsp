<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bin.Cast" %>
<%@ page import="dao.PDFDAO" %>
<%@ page import="dto.Occupations" %>
<%@ page import="dto.PDF" %>
<%@ page import="dto.Type_Of_Industry" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>報告書作成(1/3) | モリジョビ就活管理システム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<%
		ArrayList<Type_Of_Industry> type_of_industryList = Cast.autoCast(request.getAttribute("type_of_industryList"));
		ArrayList<Occupations> occupationsList = Cast.autoCast(request.getAttribute("occupationsList"));
		String remake = (String)session.getAttribute("remake");
		PDF report = new PDF();
		if("remake".equals(remake)){	//続きから作成の場合
			String pdf_id = (String)session.getAttribute("pdf_id");
			report = PDFDAO.getReport1(pdf_id);
		}
	%>
		<div class="main">
			<h1 class="m-t50 m-b30">報告書1/3</h1>

			<label>試験結果<span>必須</span>:
				<select form="next" name="sorf" required>
				<%
					if("remake".equals(remake)){	//続きから作成の場合
						if("合格".equals(report.getSorf())){
				%>
							<option disabled></option>
							<option selected>合格</option>
							<option>不合格</option>
				<%		}else{	%>
							<option disabled></option>
							<option>合格</option>
							<option selected>不合格</option>
				<%
						}
					}else{	//新規作成の場合
				%>
						<option disabled selected></option>
						<option>合格</option>
						<option>不合格</option>
				<%	}	%>
				</select>
			</label><br><br>

			<%
				if("remake".equals(remake)){
					int year = report.getSfdate().getYear();
					int month = report.getSfdate().getMonthValue();
					int day = report.getSfdate().getDayOfMonth();
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
					<label>合否期日<span>必須</span>:<input type="date" form="next" name="sfdate" value="<%= date %>" required></label><br><br>
			<%	}else{	%>
					<label>合否期日<span>必須</span>:<input type="date" form="next" name="sfdate" required></label><br><br>
			<%	}	%>

			<label>業種<span>必須</span>:
				<select form="next" name="type_of_industry">
				<%	if("remake".equals(remake)){	%>
						<option disabled></option>
				<%	}else{	%>
						<option disabled selected></option>
				<%	}

					for(Type_Of_Industry type_of_industry : type_of_industryList){
						if("remake".equals(remake) && type_of_industry.getIndustry_code().equals(report.getType_of_industry())){
				%>
							<option value="<%= type_of_industry.getIndustry_code() %>" selected><%= type_of_industry.getIndustry_name() %></option>
				<%		}else{	%>
							<option value="<%= type_of_industry.getIndustry_code() %>"><%= type_of_industry.getIndustry_name() %></option>
				<%
						}
					}
				%>
				</select>
			</label><br><br>

			<label>職種<span>必須</span>:
				<select form="next" name="occupations">
				<%	if("remake".equals(remake)){	%>
						<option disabled></option>
				<%	}else{	%>
						<option disabled selected></option>
				<%	}

					for(Occupations occupations : occupationsList){
						if("remake".equals(remake) && occupations.getOccupations_code() == report.getOccupations()){
				%>
							<option value="<%= occupations.getOccupations_code() %>" selected><%= occupations.getOccupations_name() %></option>
				<%		}else{	%>
							<option value="<%= occupations.getOccupations_code() %>"><%= occupations.getOccupations_name() %></option>
				<%
						}
					}
				%>
				</select>
			</label><br><br>

			<label>応募形態<span>必須</span>:
				<select form="next" name="application_form">
				<%	if("remake".equals(remake)){	%>
						<option disabled></option>
				<%	}else{	%>
						<option disabled selected></option>
				<%
					}

					String[] application_formList = {"学校","自己開拓","縁故","その他"};
					String application_form = report.getApplication_form();
					for(int i = 0; i < application_formList.length; i++){
						if(application_formList[i].equals(application_form)){
				%>
							<option selected><%= application_formList[i] %></option>
				<%		}else{	%>
							<option><%= application_formList[i] %></option>
				<%
						}
					}
				%>
				</select>
			</label><br>

			<form action="Main" method="get">
					<input type="submit" value="メインページへ" class="m-t30 m-r80">
			</form>

			<form action="Report" method="post" id="next">
				<input type="hidden" name="status" value="creating">
				<input type="hidden" name="report" value="1">
				<input type="submit" value="次へ" class="m-t30">
			</form>
		</div>
	</body>
</html>