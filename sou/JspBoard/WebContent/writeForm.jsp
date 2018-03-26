<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>
<%
      //list.jsp(글쓰기)->신규글, content.jsp(글상세보기)->답변글쓰기->답변글
    int num=0,ref=1,re_step=0,re_level=0; //writePro.jsp 전달->신규글
     //content.jsp에서 num이하가 넘어오는 경우라면
    if(request.getParameter("num")!=null){ //1이상
    	num=Integer.parseInt(request.getParameter("num"));
    	ref=Integer.parseInt(request.getParameter("ref"));
    	re_step=Integer.parseInt(request.getParameter("re_step"));
    	re_level=Integer.parseInt(request.getParameter("re_level"));
    	System.out.println("content.jsp에서 넘어온 매개변수값을 확인");
    	System.out.println
    	("num="+num+",ref="+ref+",re_step="+re_step+",re_level="+re_level);
    }
%>

<body bgcolor="#e0ffff">  
<center><b>글쓰기</b>
<br>
<!-- button->onclick이벤트를 처리(함수)
       submit->onsubmit이벤트가 발생->함수를 호출 형식) return 호출할 함수명()
       함수를 호출해서 제대로 입력했는지를 체크->입력->return false를 받는다.
       제대로 입력->return true을 받아서 자동적으로 action="writePro.jsp로 전송
  -->
<form method="post" name="writeform" action="writePro.jsp"
          onsubmit="return writeSave()">
  
  <input type="hidden" name="num" value="<%=num %>">
  <input type="hidden" name="ref" value="<%=ref %>">
  <input type="hidden" name="re_step" value="<%=re_step %>">
  <input type="hidden" name="re_level" value="<%=re_level %>">
        
<table width="400" border="1" cellspacing="0" cellpadding="0"  bgcolor="#e0ffff" align="center">
   <tr>
    <td align="right" colspan="2" bgcolor="#b0e0e6">
	    <a href="list.jsp"> 글목록</a> 
   </td>
   </tr>
   <tr>
    <td  width="70"  bgcolor="#b0e0e6" align="center">이 름</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="writer"></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="#b0e0e6" align="center" >제 목</td>
    <td  width="330">
       <input type="text" size="40" maxlength="50" name="subject"></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="#b0e0e6" align="center">Email</td>
    <td  width="330">
       <input type="text" size="40" maxlength="30" name="email" ></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="#b0e0e6" align="center" >내 용</td>
    <td  width="330" >
     <textarea name="content" rows="13" cols="40"></textarea> </td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="#b0e0e6" align="center" >비밀번호</td>
    <td  width="330" >
     <input type="password" size="8" maxlength="12" name="passwd"> 
	 </td>
  </tr>
<tr>      
 <td colspan=2 bgcolor="#b0e0e6" align="center"> 
  <input type="submit" value="글쓰기" >  
  <input type="reset" value="다시작성">
  <input type="button" value="목록보기" OnClick="window.location='list.jsp'">
</td></tr></table>    
</form>      
</body>
</html>      
