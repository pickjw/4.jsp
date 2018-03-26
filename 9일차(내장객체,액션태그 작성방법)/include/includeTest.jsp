<%@ page  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>현재페이지에 다른 페이지의 내용을 포함</title>
</head>
<body>
 이 페이지는 include지시어를 연습하기위해서 만들어진 페이지입니다.
 테스트중입니다.<br>
 테스트중입니다.<br>
 테스트중입니다.<br>
 <%@include file="../JspModule.java" %>
 <%-- <%!
      public String getString() {
	  return "including Testing,,,";
}
%> --%>
 테스트중입니다.<br>
 테스트중입니다.<br>
 메서드호출:<%-- <%=getString() %> --%><br>
 테스트중입니다.<br>
 테스트중입니다.<br>
 테스트중입니다.<br>
 메서드호출2:<%-- <%=getString() %> --%><br>
 테스트중입니다.<br>
 테스트중입니다.<br>
 테스트중입니다.<br>
 테스트중입니다.<br>
 <!-- <div>메인페이지의 bottom부분입니다. 관리자의 메일,약관표시출력</div> -->
 <%@include file="./sub/bottom.html" %>
 테스트중입니다.<br>
 
</body>
</html>


