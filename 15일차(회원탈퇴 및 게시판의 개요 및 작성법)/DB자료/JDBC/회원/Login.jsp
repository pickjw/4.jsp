<%@page contentType="text/html;charset=euc-kr"%>


<HTML>
 <HEAD>
  <TITLE> �α��� </TITLE>
<link href="style.css" rel="stylesheet"
      type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="script.js">
</SCRIPT>
 </HEAD>

 <BODY onload="document.login.mem_id.focus()" bgcolor="#FFFFCC">
  <center>
  <!-- mem_id�� ���¿����� �α��� ó�� -->
  <br><br><br>
 

  <!-- �α��� �ȵ� ���� -->
     <TABLE>
    <form name="login" method="post" 
	                   action="LoginProc.jsp">
     <TR>
		<TD align="center" colspan="2">
		<h4>�α���</h4></TD>
     </TR>

     <TR>
		<TD>���̵�</TD>
		<TD><INPUT TYPE="text" NAME="mem_id"></TD>
     </TR>
     <TR>
		<TD>��й�ȣ</TD>
		<TD><INPUT TYPE="password" NAME="mem_passwd"></TD>
     </TR>
     <TR>
		<TD colspan="2"><div align="center">
		<INPUT TYPE="button" value="�α���" onclick="loginCheck()">&nbsp;
		<INPUT TYPE="button" value="ȸ������"
		onclick="memberReg()">
		</div>
		</TD>
     </TR>
	 </form>
     </TABLE>
 
  </center>
 </BODY>
</HTML>
