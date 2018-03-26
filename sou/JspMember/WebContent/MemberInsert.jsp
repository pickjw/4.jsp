<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="hewon.MemberDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
     request.setCharacterEncoding("utf-8");//Setter Method 호출전에 한글처리
%>

<jsp:useBean id="mem"  class="hewon.MemberDTO"  />
<jsp:setProperty name="mem" property="*" />
<%-- <jsp:useBean id="memMgr" class="hewon.MemberDAO" /> --%>
<%
   MemberDAO memMgr=new MemberDAO();
   boolean check=memMgr.memberInsert(mem);
   System.out.println("memberInsert.jsp의 check=>"+check);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 확인</title>
<link href="style.css" rel="stylesheet"  type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="script.js?ver=2">
</SCRIPT>
</head>
<body bgcolor="#FFFFCC">
<br>
<center>
   <%
       if(check){ //회원가입에 성공했다면=>if(check==true){
    	   out.println("<b>회원가입을 축하드립니다.</b> <p>");
    	   out.println("<a href=Login.jsp>로그인 </a>");
       }else{//회원가입 실패
    	   out.println("<b>다시 확인해서 입력해 주세요!</b><p>");
    	   out.println("<a href=Register.jsp>다시 가입 </a>");
       }
   %>
</center>
</body>
</html>






