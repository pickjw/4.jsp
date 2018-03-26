<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>날짜,시간,숫자출력(양식)</title>
</head>
<body>
<!-- 다국어 지원해주는 홈페이지(ja-JP,en-US(언어-국가명) -->
 <fmt:setLocale value="ko-KR" />
 <h1>
 <pre>
 <!-- value(출력할값), type(숫자,통화,퍼센트 단위)지정  -->
 number:<fmt:formatNumber  value="9876543.61" type="number" />
 currency:<fmt:formatNumber  value="9876543.61" type="currency" />
 percent:<fmt:formatNumber  type="percent" >
                    9876543.61
              </fmt:formatNumber>
 <p>
 pattern=".000" :<fmt:formatNumber value="9876543.61"  pattern=".000" />
 pattern="#,#00.0#" :<fmt:formatNumber value="9876543.61" 
                                    pattern="#,#00.0#" />
 <hr>
  <jsp:useBean id="now"  class="java.util.Date" />
  <c:out value="${now}" />
  
  <!-- 날짜(formatDate) value="출력날짜,시간" type="date|time|both" -->
  date:<fmt:formatDate value="${now}" type="date" />
  time:<fmt:formatDate value="${now}" type="time" />
  both:<fmt:formatDate value="${now}" type="both" />
  <hr>
  <!-- dateStyle(날짜만 세부적), timeStyle(시간을 세부적) -->
  default:<fmt:formatDate value="${now}" type="both" 
                            dateStyle="default"  timeStyle="default"/>
                            
  short: <fmt:formatDate value="${now}" type="both" 
                            dateStyle="short"  timeStyle="short"/>
  
  medium:<fmt:formatDate value="${now}" type="both" 
                            dateStyle="medium"  timeStyle="medium"/>
  
  long:<fmt:formatDate value="${now}" type="both" 
                            dateStyle="long"  timeStyle="long"/>
  
  full:<fmt:formatDate value="${now}" type="both" 
                            dateStyle="full"  timeStyle="full"/>
                            
   <!--시간대를 설정(timezone)  -->
   <fmt:timeZone value="US/Eastern">
       <fmt:formatDate value="${now}" type="both" 
                            dateStyle="full"  timeStyle="full"/>
   </fmt:timeZone>
 </pre>
 </h1> 
</body>
</html>






