<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
     //세션연결상태->로그아웃->메모리해제
     session.invalidate();
%>
<script>
     alert("정상적으로 로그아웃되었습니다.!!!")
     location.href="Login.jsp"
</script>