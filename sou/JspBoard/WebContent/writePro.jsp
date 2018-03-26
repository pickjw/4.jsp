<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.Timestamp,lys.board.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
     request.setCharacterEncoding("utf-8");//한글
     //BoardDTO->Setter Method호출(5개)+hidden(4개)->9개
     //BoardDTO article=new BoardDTO();
     //property="*" =>매개변수로 전달받은 경우에만 적용
%>
<jsp:useBean id="article" class="lys.board.BoardDTO" />
<jsp:setProperty name="article" property="*" />
<%
  //BoardDAO 객체->insertArticle(aritcle)=>9개+2개(작성날짜,ip)
   Timestamp temp=new Timestamp(System.currentTimeMillis());//컴퓨터의날짜,시간
   article.setReg_date(temp);//->insert into ~?  =>직접 ?대신에 now() 써준다.
   article.setIp(request.getRemoteAddr());//원격접속 ip주소 출력
   BoardDAO dbPro=new BoardDAO();
   dbPro.insertArticle(article);//num=0
   response.sendRedirect("list.jsp");//1페이지
%>









