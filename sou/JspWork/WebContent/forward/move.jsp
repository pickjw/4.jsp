<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>요청을 판단해서 페이지 전송을 결정</title>
</head>
<body>
   <h1>페이지를 이동시키는 역할</h1>
   <%
     String move=request.getParameter("move");
     System.out.println("이동할 페이지명(move=>)"+move);
     //response.sendRedirect(move);
   %>
   <jsp:forward page='<%=move%>' />
</body>
</html>


