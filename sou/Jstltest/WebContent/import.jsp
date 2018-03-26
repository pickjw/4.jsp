<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>외부의 자원을 가져다 주는 액션태그</title>
</head>
<body>
  <%
       //String url="http://www.empas.com";//내부적으로 보안을 한경우 못불러온 경우
      // url="chooseTag.jsp" 로 내용을 변경->수정의 의미가 있다.
   %>
  <c:set var="url"  value="http://www.empas.com" />
  <%-- <c:import url="${url}"  var="u" />
    <c:out value="${url}" /> 가져옵니다.
    <hr>
    <c:out value="${u}"  escapeXml="false" /> --%>
    <hr>
    <h4>내부자원을 가져오기(다른 페이지의 내용도 삽입이 가능)</h4>
    <c:set var="url"   value="chooseTag.jsp" />
    <c:import url="${url}" var="u" >
       <c:param name="name" value="tk" />
    </c:import>
    <c:out value="${u}"  escapeXml="false" />
</body>
</html>










