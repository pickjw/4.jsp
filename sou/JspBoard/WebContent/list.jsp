<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page  import="lys.board.*,java.util.*,java.text.SimpleDateFormat"%> 
<%!
    //글목록보기에 해당 용어를 정리
    int pageSize=10;//int numPerPage->페이지당 보여주는 게시물수
    int blockSize=10;//int pagePerBlock->블럭당 보여주는 페이지수
    //날짜,시간의 양식을 지정해주는 클래스
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>

<%
   //게시판을 맨 처음 실행시킬 경우 초기화 설정부분->1페이지(pageNum)
   String pageNum=request.getParameter("pageNum");
   if(pageNum==null){//맨 처음에는 선택한 적이 없기때문에 null
	   pageNum="1";//1
   }
   //1.nowPage=>currentPage
   int currentPage=Integer.parseInt(pageNum);//"1"->1
   //2.DB상의 레코드시작 번호~몇개?  limit ?,?
                       //(1-1)*10+1=1, (2-1)*10+1=11,(3-1)*10+1=21		   
   int startRow=(currentPage-1)*pageSize+1;
   int endRow=currentPage*pageSize;//1*10=10,2*10=20,3*10=30
   //화면에 출력시켜주는 레코드갯수가 아니다.X
   int count=0;//3.총레코드수->totalRecord
   int number=0;//4.beginPerPage->페이지별로 맨 처음에 나오는 시작 게시물번호
   List<BoardDTO> articleList=null;//5.화면에 실제로 출력할 레코드객체
   //6.총레코드수 구하기
   BoardDAO dbPro=new BoardDAO();
   //<jsp:useBean id="dbPro" class="lys.board.BoardDAO" />
   count=dbPro.getArticleCount();
   System.out.println("현재 레코드수(count=>)"+count);
   //6.검색된 레코드가 존재->articleList에 담기
   if(count > 0){ //한개이상이라면    ------>endRow(X)
	   articleList=dbPro.getArticles(startRow, pageSize);//int pageSize=10;
   }
   //7.beginPerPage을 계산
   //            122-(1-1)*10=122, 122-(2-1)*10=112
   number=count-(currentPage-1)*pageSize;
   System.out.println("페이지별 number 확인=>"+number);
%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#e0ffff">
<center><b>글목록(전체 글:<%=count%>)</b>
<table width="700">
<tr>
    <td align="right" bgcolor="#b0e0e6">
    <a href="writeForm.jsp">글쓰기</a>
    </td>
</table>
<%
    //count에 따라서 데이터를 보여줄 수 있는 코드 작성
    if(count == 0){// 레코드가 없는 경우
%>
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center">
 <tr>
    <td align="center">게시판에 저장된 글이 없습니다.</td>
 </tr>
</table>
<% } else { %>
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
    <tr height="30" bgcolor="#b0e0e6"> 
      <td align="center"  width="50"  >번 호</td> 
      <td align="center"  width="250" >제   목</td> 
      <td align="center"  width="100" >작성자</td>
      <td align="center"  width="150" >작성일</td> 
      <td align="center"  width="50" >조 회</td> 
      <td align="center"  width="100" >IP</td>    
    </tr>
    <!-- 실제적으로 레코드를 출력시켜주는 부분 -->
    <%
         for(int i=0;i<articleList.size();i++){
        	//BoardDTO->Getter호출 
        	BoardDTO article=articleList.get(i);//vecList.elementAt(i);벡터인 경우
    %>
    
   <tr height="30"><!--하나씩 감소하면서 출력시켜주는 게시물번호 -->
    <td align="center"  width="50" ><%=number--%></td>
    <td  width="250" >
	 <!-- 글제목(답변글 이라면) 먼저 답변이미지를 부착시키는 부분 -->
	 <%
	      int wid=0;//공백(들여쓰기)을 계산하기위한 변수 선언
	      if(article.getRe_level() > 0){ //답변글이라면
	    	  wid=7*(article.getRe_level());//7,14,21,,,
	 %>
	  <img src="images/level.gif" width="<%=wid%>" height="16">
	  <img src="images/re.gif">
	  <%}else { %>
	  <img src="images/level.gif" width="0" height="16">
	  <% } %>       
	     <!-- pageNum(페이지번호), num(게시물번호) -->
      <a href="content.jsp?num=<%=article.getNum() %>&pageNum=<%=currentPage%>">
           <%=article.getSubject() %></a> 
           
         <% if (article.getReadcount() >=20) { %>
             <img src="images/hot.gif" border="0"  height="16"> 
         <% } %>
         </td>
    <td align="center"  width="100"> 
       <a href="mailto:<%=article.getEmail()%>"><%=article.getWriter()%></a></td>
    <td align="center"  width="150"><%=sdf.format(article.getReg_date())%></td>
    <td align="center"  width="50"><%=article.getReadcount()%></td>
    <td align="center" width="100" ><%=article.getIp()%></td>
  </tr>
       <%  } //for %>
</table>
<% } //else %>
<%
     //페이징 처리->이전,현재,다음블럭을 링크걸기->검색(모델1)
   if(count > 0){ //레코드가 한개이상이라면
      //1.총페이지수 구하기
      //int totalPage =(int)Math.ceil((double)totalRecord / numPerPage);
      //                       122/10=12.2+1=>12.2+1.0=13.2=13페이지
      int pageCount=count/pageSize+(count%pageSize==0?0:1);
      //2.시작페이지,끝페이지
      int startPage=0;
      //10의 배수인지 아닌지(경계선)
      if(currentPage%blockSize!=0){//1~9,11~19,21~22
           startPage=currentPage/blockSize*blockSize+1;
      }else{ //10,20,30,40
                            //((10/10)-1)*10+1=1
           startPage=((currentPage/blockSize)-1)*blockSize+1;
      }
       int endPage=startPage+blockSize-1;//1+10-1=10
       System.out.println("list.jsp의 startPage="+startPage+",endPage="+endPage);
       //블럭별로 구분해서 링크걸어서 출력
       if(endPage > pageCount) endPage=pageCount;//마지막페이지=총페이지수
       //1) 이전블럭 if(11>10)
       if(startPage > blockSize) { %>
        <a href="list.jsp?pageNum=<%=startPage-blockSize %>">[이전]</a>
      <%  }
       //2) 현재블럭(1,2,3,4,5,,,10)
        for(int i=startPage;i<=endPage;i++) { %>
       <a href="list.jsp?pageNum=<%=i%>">[<%=i %>]</a>
       <% } 
       //3) 다음블럭 -> 이전 11 ~ 20(endPage=pageCount) [다음] 21~25
       if(endPage < pageCount) {%>
       <a href="list.jsp?pageNum=<%=startPage+blockSize %>">[다음]</a>
 <%       
           }//
      }//if(count > 0) {
  %>
</center>
</body>
</html>




