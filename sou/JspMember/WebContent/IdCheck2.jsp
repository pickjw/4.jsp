<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="hewon.MemberDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   //script.js->idCheck(id)->IdCheck.jsp?mem_id='nup'
   String mem_id=request.getParameter("mem_id");
   System.out.println("IdCheck.jsp에 전달된 mem_id=>"+mem_id);//null
   //DB접속 
   MemberDAO memMgr=new MemberDAO();
   boolean check=memMgr.checkId(mem_id);
   System.out.println("IdCheck.jsp의 check=>"+check);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Id중복 체크</title>
<link href="style.css" rel="stylesheet"  type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="script.js">
</SCRIPT>
</head>
<body bgcolor="#FFFFCC">
<br>
<center>
   <b><%=mem_id %></b>
   <%
       if(check){ //이미 당신이 입력한 아이디가 존재한다면
    	   out.println("는 이미 존재하는 아이디 입니다.<p>");
       }else{//그런 아이디가 없다는 경우-> 이 아이디 사용가능
    	   out.println("는 사용 가능한 아이디 입니다.<p>");
       }
   %><!-- 자바스크립트에서 부모창(opener), 자식창(self)(현재 열려있는 창)-->
 <a href="#" onclick="self.close()">닫기</a>
</center>
</body>
</html>






