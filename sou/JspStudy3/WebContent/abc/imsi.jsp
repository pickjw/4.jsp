<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP 2번째 예제</title>
</head>
<body>
<%  //int count=3; //지역변수 %>

 <%
   // int count=3; //지역변수
    for(int i=0;i<count;i++){
    	out.println("<h1>JSP테스트"+i+"!!<br>");
    	//document.write("<h1>JSP테스트"+i+"!!<br>") 자바스크립트인 경우
    }
    //out.println("count=>"+count);
 %>
 for문안에 사용된 count변수의 값 출력:<%=count%>
 <%!
       //먼저 선언하기전에 사용이 가능하다(불러다 사용이 가능하다.)
       //현재 페이지의 위치에 상관없이 언제든지 불러다 사용이 가능
       int count=3; //지역변수
 %>
</body>
</html>





