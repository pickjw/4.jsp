<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>쿠키확인</title>
</head>
<body>
  <h1>웹브라우저에 저장된 쿠키를 가져오는 예제</h1>
  <%
     Cookie[] cookies=request.getCookies();//쿠키를 전부 배열형태로 가져온다.
       if(cookies!=null){//검색할 쿠키의 정보가 들어있다면
    	   for(int i=0;i<cookies.length;i++){
    		   if(cookies[i].getName().equals("mycookie")){//mycookie가 존재한다면
    %>
              쿠키이름:<%=cookies[i].getName() %><br>
              쿠키값:<%=cookies[i].getValue() %><br>
    <% 			   
    		   }//inner if
    	   }//for
       }//outer if
  %>
</body>
</html>





