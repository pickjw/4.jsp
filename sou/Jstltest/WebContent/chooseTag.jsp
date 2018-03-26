<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>choose태그</title>
</head>
<body>
  <c:set var="age" value="${20}" />
  <c:set var="name" value="${'tk'}" />
  
  <c:choose>
     <c:when test="${param.name=='tk'}">
       name 파라미터값은 ${param.name} 입니다.<br>
     </c:when>
     <c:when test="${param.age > 18}">
        당신의 나이는 18세 이상이군요!
     </c:when>
     <c:otherwise>
        <li>당신은 'tk' 도 아니고 나이도 18세 이상도 아니다.</li>
     </c:otherwise>
  </c:choose>
  
</body>
</html>






