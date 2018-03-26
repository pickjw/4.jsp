<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>요청을 받아서 처리해주는 프로그램</title>
<%
     //int tr_cnt=Integer.parseInt(request.getParameter("tr_cnt"));
     String tr_cnt=request.getParameter("tr_cnt"); //"2"
     String td_cnt=request.getParameter("td_cnt");//"4"
     System.out.println("tr_cnt=>"+tr_cnt+",td_cnt=>"+td_cnt);
    
%>
</head>
<body>
  <table border="1">
     <%
          for(int i=0;i<Integer.parseInt(tr_cnt);i++){  //행 %>
            <tr>
          <% 
        	  for(int j=0;j<Integer.parseInt(td_cnt);j++){  %>
        		 <td width="50">&nbsp;</td> 
            <%   }  %>
            </tr>
     <% } %>
  </table>
  <!-- <input type="button"  value="다시 입력하기" onclick="history.back()"> -->
  <a href="JavaScript:history.back()">다시 입력하기</a>
</body>
</html>








