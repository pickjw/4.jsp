<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>a.jsp</title>
</head>
<body bgcolor="yellow">
  <h1>a.jsp로 오신것을 환영합니다.!!!!</h1>
  <%
        // request.setAttribute("total", su); <-->request.getAttribute(키명)
        //out.println("su=>"+su); 사용불가(로컬만 가능)
  %>
  <hr>
  move.jsp에서 공유한 su값은? <%=request.getAttribute("total")%>
</body>
</html>





