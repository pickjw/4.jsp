<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
     //response.sendRedirect("http://www.naver.com");//단순히 외부 페이지 이동
     //response.sendRedirect("chooseTag.jsp?name=tk");//내부 페이지 이동
     //형식) <c:redirect url="이동할 페이지명"  />
     /* 형식2)<c:redirect url="이동할 페이지명" >
                      <c:param  name="전달할 매개변수명" value="전달할값">
                      <c:param  name="전달할 매개변수명" value="전달할값">
                       ,,,
                   </c:redirect>
     */
%>
<%-- <c:redirect url="http://www.naver.com" /> --%>
<c:redirect url="chooseTag.jsp">
   <c:param name="name"  value="tk" />
</c:redirect>





