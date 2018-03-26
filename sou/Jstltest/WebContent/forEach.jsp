<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
        java.util.HashMap mapData=new java.util.HashMap();
        mapData.put("name","테스트");
        mapData.put("today",new java.util.Date());
        //int [] intArray={1,2,3,4,5};
%>
<c:set var="intArray" value="<%=new int[] {1,2,3,4,5} %>"  />
<c:set var="map"  value="<%=mapData %>" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>액션태그 연습</title>
</head>
<body>
  <%
      //int sum=0;
     /*  for(int i=1;i<=100;i+=2){
    	    //if(i%2==1)
    	  sum+=i; //sum=sum+i;
      }
      out.println(sum); */
  %>
  <c:set var="sum" value="0" />
  <c:forEach var="i"  begin="1"  end="100" step="2">
         <c:set var="sum" value="${sum+i}" />
  </c:forEach>
  결과:${sum}
  <hr>
  <%-- <c:set var="intArray" value="<%=new int[] {1,2,3,4,5} %>"  /> 
            <c:set var="map"  value="<%=mapData %>" />
  --%>
  <h4>int배열의 값출력</h4>
   <c:forEach var="i" items="${intArray}" begin="2" end="4">
        [${i}]
   </c:forEach>
   <hr><!-- hashMap객체의 기본속성->key,value  -->
   <c:forEach var="i" items="${map}">
      ${i.key}=${i.value}<br>
   </c:forEach>
</body>
</html>






