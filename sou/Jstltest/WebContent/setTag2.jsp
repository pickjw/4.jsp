<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:requestEncoding  value="utf-8" />
<%
       //java.util.HashMap map=new java.util.HashMap(); //key,value
       //map.put("name","홍길동");//객체명.put(키명,저장할값(객체))
       //map.get("name(키명)") =>키값의 값을 가져올때(화면에 출력할때)
        //request.setCharacterEncoding("utf-8");
            String name=request.getParameter("name");
%>

<c:set var="map"  value="<%=new java.util.HashMap() %>"  />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>set태그의 2번째 예제</title>
</head>
<body>
   <c:set target="${map}"  property="name"   value="홍길동" />
   변수 map에 저장된 name키값의 출력:${map.name}<br>
   변수 map에 저장된 name키값의 출력2:${map['name']}<br>
   변수 map에 저장된 name키값의 출력3:${map["name"]}<br>
   <hr>
   <form method="post" action="setTag2.jsp">
       이름:<input type="text" name="name">
       <input type="submit" value="전송">
   </form>
   <hr>
   입력받은 이름은 <%=name%><br>
   입력받은 이름은 <%=request.getParameter("name")%><br>
   입력받은 이름은 ${param.name}<br>
</body>
</html>







