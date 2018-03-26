<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>문자열,숫자처리</title>
</head>
<body>
  <%
         request.setCharacterEncoding("utf-8");
         String msg=request.getParameter("msg");
         //"3"->3, "43.2"->43.2
         int number=Integer.parseInt(request.getParameter("number"));
         System.out.println("msg=>"+msg+",number=>"+number);
  
         int count=0;//반복할 횟수를 저장  //3>0->1->2->3
         while(number>count){  %>
             <b><%=msg%></b><br>
 <%
             count++;
         }
  %>
</body>
</html>



