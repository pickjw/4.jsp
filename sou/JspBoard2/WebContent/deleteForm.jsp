<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript">          
  function deleteSave(){	
	if(document.delForm.passwd.value==''){
	alert("비밀번호를 입력하십시요.");
	document.delForm.passwd.focus();
	return false;
 }
	//document.delForm.submit();//->링크문자열 클릭 또는 input type=button
}        
</script>
</head>

<body bgcolor="#e0ffff">
<center><b>글삭제</b>
<br>
<form method="POST" name="delForm"  
          action="/JspBoard2/deletePro.do?pageNum=${pageNum}&num=${num}" 
          
   onsubmit="return deleteSave()"> 
 <table border="1" align="center" cellspacing="0" cellpadding="0" width="360">
  <tr height="30">
     <td align=center  bgcolor="#b0e0e6">
       <b>비밀번호를 입력해 주세요.</b></td>
  </tr>
  <tr height="30">
     <td align=center >비밀번호 :   
       <input type="password" name="passwd" size="8" maxlength="12">
	    <%-- 응용
	    <input type="hidden" name="num" value="<%=num%>">
	    <input type="hidden" name="pageNum" value="<%=pageNum%>">
	     --%> 
	    </td>
 </tr>
 <tr height="30">
    <td align=center bgcolor="#b0e0e6">
      <input type="submit" value="글삭제" >
      <input type="button" value="글목록" 
       onclick="document.location.href='/JspBoard2/list.do?pageNum=${pageNum}'">     
   </td>
 </tr>  
</table> 
</form>
</body>
</html> 
