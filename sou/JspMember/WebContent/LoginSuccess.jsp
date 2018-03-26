<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   //회원인 사람만 보여주는 페이지->세션->session.setAttribute("idKey",mem_id);
   String mem_id=(String)session.getAttribute("idKey");
   System.out.println("mem_id=>"+mem_id);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Page(인증 페이지)</title>
</head>
<body bgcolor="#FFFFCC">
   <center>
       <%
           if(mem_id!=null){ //인증된 계정을 가진 사람이라면 
        %>
        	   <b><%=mem_id%></b>님 환영합니다. <p>
        	   <%-- <%=memMgr.getPoint() %> 마일리지 값 출력--%>
        	   당신은 제한된 기능을 사용할 수 가 있습니다.<p>
          <a href="Logout.jsp">로그아웃</a>	   
       <%   } %>
   </center>
</body>
</html>




