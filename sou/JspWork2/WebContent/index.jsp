﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index.jsp</title>
</head>
<body>

 <jsp:forward page="/view/indexControl.jsp" >
     <jsp:param name="CONTROL"  value="intro" />
     <jsp:param name="PAGENUM"  value="01" />
 </jsp:forward>
 
</body>
</html>



