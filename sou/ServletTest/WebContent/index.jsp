<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항창 띄우기</title>
<script>
   function test(){
	   //1.불러올 문서명(->서블릿) 2.타이틀제목 3.창의옵션(left,top,width,height)
	   open('/ServletTest/Notice','w','left=300,top=200,width=400,height=300')
   }
</script>
</head>
<body onload="test()">
   <h2>Welcome To My JspStudy Site!!!</h2>
</body>
</html>

