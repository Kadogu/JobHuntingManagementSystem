<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.io.BufferedInputStream" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.File" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>報告書閲覧 | モリジョビ就活管理システム</title>
	</head>
	<body>
	<%
		String filename = (String)request.getAttribute("filename");

		response.setHeader("Content-Disposition", "inline; filename=" + URLEncoder.encode(filename + ".pdf", "UTF-8"));
		response.setContentType("application/pdf");
		BufferedInputStream in = null;
		byte[] buf;
		in = new BufferedInputStream(new FileInputStream(request.getServletContext().getRealPath("pdf/") + filename + ".pdf"));
		buf = new byte[in.available()];
		in.read(buf);
		OutputStream os = response.getOutputStream();
		os.write(buf);
		os.flush();

		in.close();
		os.close();
	%>
	</body>
</html>