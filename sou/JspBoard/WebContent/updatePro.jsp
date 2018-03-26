<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.Timestamp,lys.board.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
     request.setCharacterEncoding("utf-8");//한글
     //BoardDTO->Setter Method호출(5개)+hidden(2개)->7개
%>
<jsp:useBean id="article" class="lys.board.BoardDTO" />
<jsp:setProperty name="article" property="*" />
<%
   //pageNum이 넘어오는지 체크
   String pageNum=request.getParameter("pageNum");//필드X
   BoardDAO dbPro=new BoardDAO();
   int check=dbPro.updateArticle(article);//num=0
   if(check==1){//글 수정이 성공했다면
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






