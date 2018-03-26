<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="lys.board.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   //pageNum이 넘어오는지 체크
   int num=Integer.parseInt(request.getParameter("num"));
   String pageNum=request.getParameter("pageNum");//필드X
   String passwd=request.getParameter("passwd");//실제 입력을 받아서 넘어옴
   System.out.println
                 ("deletePro.jsp의 num=>"+num+",pageNum=>"+pageNum);
   BoardDAO dbPro=new BoardDAO();
   int check=dbPro.deleteArticle(num,passwd);//(passwd,num)
   if(check==1){//글 삭제이 성공했다면
	  //response.sendRediect("list.jsp?pageNum=?") //표현식으로 전달 
%>
<meta http-equiv="Refresh" 
            content="0;url=list.jsp?pageNum=<%=pageNum %>">
<% }else { %>
     <script>
        alert("비밀번호가 맞지않습니다.\n다시 확인요망!!")
        history.go(-1);//history.back()
     </script>
<% } %>






