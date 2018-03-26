<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>페이지 전환</title>
</head>
<body>
  <%
      //request.setAttribute("result", resultObject);
  %>
  처리결과:<%=request.getAttribute("result") %><br>
  처리결과2:${result}
</body>
</html>






