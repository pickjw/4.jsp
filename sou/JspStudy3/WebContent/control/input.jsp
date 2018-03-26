<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사용자로부터 값을 입력(전송폼)</title>
</head>
<body>
   <h1>이름,색깔을 입력</h1>
   <!--document.form.name.value  -->
   <form method="post"  action="iftest.jsp">
       이름:<input type="text" name="name"><p>
       좋아하는 색깔:
       <select name="color">
           <option value="blue">파란색</option>
           <option value="red">붉은색</option>
           <option value="orange">오렌지색</option>
           <option value="etc">기타</option>
       </select><p>
       <input type="submit" value="보내기">
   </form>
</body>
</html>



