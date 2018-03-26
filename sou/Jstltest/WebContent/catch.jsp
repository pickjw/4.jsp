<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>액션태그 연습</title>
</head>
<body>
   <fmt:requestEncoding value="utf-8" />
   <hr>
   <c:catch var="ex">
     name의 파라미터변수명:<%=request.getParameter("name") %>
      <%
           if(request.getParameter("name").equals("test")) { %>
              ${param.name}은 test 입니다.
      <% 
           }
      %>
   </c:catch>
   <%-- <c:if test="${ex!=null}"> --%>
   <c:if test="${not empty ex}">
       예외가 발생하였습니다.<br>
       ${ex}
   </c:if>
   
</body>
</html>






