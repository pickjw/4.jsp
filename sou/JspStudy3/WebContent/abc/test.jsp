<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.Date"
    buffer="16kb" %>
<%@ page import="java.sql.*,java.io.*,java.net.*" %>
<%@ page import="java.text.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP페이지 3번째(표현식2)</title>
<%!
    //변수(정적멤버변수)와 메서드를 작성->불러다 사용하는 경우
    String name="홍길동";//static 멤버변수

    public String getName(){  //public static String getName() { return name;}
    	return name;
    }
%>
</head>
<body>

 <%
      float f=2.3f;
      int i=Math.round(f);//클래스명.정적메서드명(~)
      //날짜->Date
      //외부의 특정 패키지에 존재하는 클래스를 불러오는 방법->import (O)
      //java.util.Date d=new java.util.Date();
      Date d=new Date();
      out.println("d의 값=>"+d);//d.toString() //오늘날짜 출력
 %>
 <hr><p>
 정수 f의 반올림값은? <%=i%><p>
 현재의 날짜와 시간은? <%=d.toString() %><br>
 선언문의 메서드를 호출?<%=getName()%>
</body>
</html>





