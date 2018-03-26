<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>응답객체(response객체)</title>
</head>
<body>
<%
    //response.sendRedirect("http://www.empas.com");//완전히 외부사이트->http://를 줘야 이동
    response.sendRedirect("../include/includeTest.jsp");//자기의 현재 프로젝트를 기준->상대경로
%>
</body>
</html>