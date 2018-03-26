<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>포함을 하는 페이지</title>
</head>
<body>
 <h1>포함을 하는 페이지</h1>
 <!-- include액션태그를 설명-->
 <%
    request.setCharacterEncoding("utf-8");
 %>
 <hr>
 <jsp:include page="sub.jsp"  flush="false" />
  <%-- <%
    String name=request.getParameter("name");
  %>
  <b><%=name %>님! 오셨군요!</b> --%>
  <hr>
  나머지는 sub.jsp가 알아서 처리해줄 거예요!!!!
</body>
</html>


