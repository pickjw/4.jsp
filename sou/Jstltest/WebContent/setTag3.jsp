<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>한글처리</title>
</head>
<body>
<%
            request.setCharacterEncoding("utf-8");
            String name=request.getParameter("name");
%>
 입력받은 이름은 <%=name%><br>
 입력받은 이름은 <%=request.getParameter("name")%><br>
 입력받은 이름은 ${param.name}<br>
</body>
</html>





