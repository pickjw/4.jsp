<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   String mem_id=request.getParameter("mem_id");
   System.out.println("DelCheckForm.jsp의 mem_id=>"+mem_id);
%>
<HTML>
 <HEAD>
  <TITLE>회원탈퇴 확인</TITLE>
<link href="style.css" rel="stylesheet"  type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="script.js?ver=2">
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
	   function delCheck(){
        if(document.del.passwd.value==""){
           alert("비밀번호를 입력해주세요!");
		   document.del.passwd.focus();
		   return;
		}
	   //예정대로 action값이 지정한 페이지로 이동
        document.del.submit();//전송
	  }
</SCRIPT>
 </HEAD>

 <BODY onload="document.del.passwd.focus()" bgcolor="#FFFFCC">
  <center>
     <TABLE>
    <form name="del" method="post" 
              action="deletePro.jsp?mem_id=<%=mem_id%>">
     <TR>
		<TD align="center" colspan="2">
	<b><%=mem_id%>님 비밀번호를 입력해주세요</b></TD>
     </TR>

     <TR>
		<TD>비밀번호</TD>
		<TD><INPUT TYPE="password" NAME="passwd"></TD>
     </TR>
     
     <TR>
	    <TD>
		<INPUT TYPE="button" value="탈퇴" onclick="delCheck()">&nbsp;&nbsp;&nbsp;
		<INPUT TYPE="button" value="취소"
        onclick="document.location.href='Login.jsp?mem_id=<%=mem_id%>'">
		</TD>
     </TR>
	 <!-- hidden값 전달 -->
	 <%-- <input type="hidden" name="mem_id" value="<%=mem_id%>"> --%>
	 <!--  -->
	 </form>
     </TABLE>
  </center>
 </BODY>
</HTML>
