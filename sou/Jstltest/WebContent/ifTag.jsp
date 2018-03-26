<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>if태그 연습</title>
</head><!-- String name="bk";  -->
<body>
  <c:set var="age" value="${20}" />
  <c:set var="name" value="${'tk'}" />
<c:if test="true">
    무조건 실행된다.
</c:if>
<hr>
<c:if test="${18 < age}">
   당신의 나이는 18세 이상이군요!
</c:if>
<hr>
<c:if test="${name=='bk'}">
    당신의 이름은 <c:out value="${name}" />
</c:if>
<c:if test="${name!='bk'}">
    당신은 bk가 아니군요!
    그럼 누구 ${name}
</c:if>
</body>
</html>






