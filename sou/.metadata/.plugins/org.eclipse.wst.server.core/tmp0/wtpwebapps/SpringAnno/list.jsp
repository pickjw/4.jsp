<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,lee.*" %>
<html><body>
<table border="1">
	<tr>
	  <td align="center" colspan="5">
		*** 게시판 목록 ***&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="writeui.do">글쓰기</a>
	  </td>
	</tr>
	<tr>
<th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th>
</tr>
<%
    ArrayList list=(ArrayList)request.getAttribute("list"); //${list}
    if(list!=null){  //꺼내온 데이터가 들어가 있다면
    	Iterator iter=list.iterator();
        while(iter.hasNext()){ //꺼내올 데이터가 들어가 있다면
        	//BoardDTO data=(BoardDTO)iter.next();
            BoardCommand data=(BoardCommand)iter.next();
            //---------------------------------------------------------
            int num=data.getNum();
            String title=data.getTitle();
            String author=data.getAuthor();
            String content=data.getContent();
            //----------------------------------------------------------
            String writeday=data.getWriteday();//날짜 출력시->외국스타일
            //-----------------------------------------------------------
            int readcnt=data.getReadcnt();
%>
	<tr>
		<td align="center"><%= num %></td>
		<td><a href="retrieve.do?num=<%= num %>"><%= title %></a></td>
		<td><%= author %></td>
		<td><%= writeday.substring(0,10)%></td><!--10글자만 출력 -->
		<td><%= readcnt%></td>
	</tr>
<%
	}//end while
}//end if
%>
<!-- 검색 기능 시작 -->
	<tr><td colspan="5" align="center">
		<form action="search.do">
		   <select name="searchName" size="1">
	          <option value="author">작성자</option>
    	          <option value="title">제목</option>
                 </select>
		   <input type="text" name="searchValue"><input type="submit" value="검색">
		</form>
	</td></tr>
</table>
</body></html>


