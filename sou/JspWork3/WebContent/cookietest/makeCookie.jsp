<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>쿠키를 생성하고 전송하는 예제</title>
</head>
<%
   //쿠키를 생성(쿠키이름,저장할값을 지정)
   String cookiename="mycookie";
  //1.쿠키객체 생성
  Cookie c=new Cookie(cookiename,"이연수");//쿠키이름, 저장할값
  //2.쿠키 저장 유지시간->setMaxAge(초단위)
  c.setMaxAge(60*2);//2분->60*60*24*365=1년동안 저장하는 경우
  //3.중간에 값을 변경->setValue(새로 저장할값)
  //c.setValue("Melone");
  //4.접속한 클라이언트에게 전송
  response.addCookie(c);//addCookie(전송할 쿠키객체명)
%>
<body>
   <h1>쿠키를 생성하고 전송하는 예제</h1>
   쿠키가 생성되었습니다.<br>
   쿠키의 내용은 <a href="useCookie.jsp">여기에서 확인</a>
</body>
</html>





