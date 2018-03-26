<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="test.BeanTest" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자바빈즈가 나온 배경설명</title>
</head>
<body>

 <%
  //입력->매개변수 전달->저장(필드별로 저장(DTO))->Setter호출=>Table저장
      request.setCharacterEncoding("utf-8");
      String str=request.getParameter("str");
      String addr=request.getParameter("addr");
      System.out.println("str=>"+str+",addr=>"+addr);
      //1.BeanTest객체를 생성
      //BeanTest bt=new BeanTest();//Data d=new Date();
      //2.입력받은 갯수만큼 Setter Method호출
      //bt.setStr(str);//bt.setStr(request.getParameter("str"))
      //bt.setAddr(addr);
      //3.Getter Method로 값을 불러온다.->테이블의 필드로 저장
      //out.println("입력받은 이름은?"+bt.getStr()+"<br>");
      //out.println("입력받은 주소는?"+bt.getAddr()+"<br>");
 %>
 <jsp:useBean id="bt" class="test.BeanTest" scope="page" />
 <%-- 
 <jsp:setProperty name="bt" property="str" value="<%=str%>"  />
 <jsp:setProperty name="bt" property="addr" value="<%=addr%>" />
  --%>
 
 <jsp:setProperty name="bt"  property="*" />
 
 <jsp:getProperty name="bt"  property="str" />
 <jsp:getProperty name="bt"   property="addr" />
 
 <hr>
   <b><%=bt.getStr() %></b>님! 오셨군요!!!<br>
   <b>주소는 <%=bt.getAddr() %></b>입니다.
</body>
</html>






