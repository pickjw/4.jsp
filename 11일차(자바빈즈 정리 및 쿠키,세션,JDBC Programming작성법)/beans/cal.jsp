<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>요청을 하는 페이지(빈즈이용)</title>
</head>
<body>
<center>
      <h3>계산기</h3>
      <form name="form1"  method="post" action="calResult.jsp">
       <input type="text" name="num1" width="200" size="5">
       <select name="operator">
          <option selected>+</option>
          <option>-</option>
          <option>*</option>
          <option>/</option>
       </select>
       <input type="text" name="num2" width="200" size="5"><p>
       <input type="submit" value="계산"  name="b1" id="b1">
       <input type="reset" value="다시입력"  name="b2">
      </form>
</center>
</body>
</html>







