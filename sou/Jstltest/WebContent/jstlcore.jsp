<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>변수(객체)의 사용범위와 out 태그</title>
</head>
<body>
  <h3>출력문</h3>
   <h2>
   <pre>
   <c:out value="${1+2}" />
   ${1+2}
   <%
      out.println(1+2);
   %>
   <%=1+2 %>
   ${1 >3 } <c:out value="${1 >3 }" />
   ${1 gt 3 } <c:out value="${1 gt 3 }" />
   <hr>
     <%
       //pageScope(현재 디폴트) < requestScope(공유) <sessonScope(로그인)<applicationScope(전부 공유)
       
       //session.setAttribute("name","하늘"); scope="session"
       //<-->session.getAttribute("name")
      //String name="하늘"; scope="page"
      //request.setAttribute("name","하늘")  scope="request"
     %>
     <c:set var="name" value="하늘"  scope="session" />
     c:out name:<c:out value="${name}" />
     expression name:<%=session.getAttribute("name") %>
     <hr>
     set page scope var "name":
     <%-- <c:set var="name" value="hello"  /> --%>
     <c:set var="name">
         hello
     </c:set>
     <!-- 같은 변수의 값을 출력->변수값출력(page) 
            저장영역을 표시.출력변수명
       -->
     c:out name=> <c:out value="${name}" />
     2번째 c:out name=> <c:out value="${pageScope.name}" />
     session의 값 name=><c:out value="${sessionScope.name}" />
     <hr>
     remove session scope var "name":<c:remove var="name"  scope="session"/>
      session의 값 name2=><c:out value="${sessionScope.name}" />
   </pre>
   </h2>
</body>
</html>






