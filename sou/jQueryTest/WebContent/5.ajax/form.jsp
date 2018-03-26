<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>입력받아서 처리</title>
</head>
<body>
 <%
    request.setCharacterEncoding("utf-8");
   String seq=request.getParameter("seq");
   String username=request.getParameter("username");
   String password=request.getParameter("password");
   //hobby->배열형태의 문자열을 받을 경우
   String [] hobby=request.getParameterValues("hobby");
      for(int i=0;i<hobby.length;i++){
    	  out.println(hobby[i]);
      }
 %>
 <%=seq%>번과 <%=username%>,<%=password %>이 전달되었습니다.!!!
</body>
</html>








