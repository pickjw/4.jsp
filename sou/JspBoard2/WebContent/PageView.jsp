<%@ page contentType="text/html;charset=UTF-8"%>
<%
    int nowPage = 0;
    int nowBlock = 0;
    int totalRecord = Integer.parseInt(request.getParameter("totalRecord"));
    int numPerPage = 10;           
    int pagePerBlock = 10;    
    
	int totalPage =(int)Math.ceil((double)totalRecord / numPerPage);
	int totalBlock =(int)Math.ceil((double)totalPage / pagePerBlock);
	 
	if (request.getParameter("nowPage") != null){ 
		 nowPage= Integer.parseInt(request.getParameter("nowPage")); } 
	if (request.getParameter("nowBlock") != null){
		 nowBlock = Integer.parseInt(request.getParameter("nowBlock"));}
	int beginPerPage =   nowPage * numPerPage;	 
%>
<html>
<head><title>JSPBoard</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script>
function preBlock(nowBlock,nowPage){
	     document.total.nowBlock.value = nowBlock;
		 document.total.nowPage.value = nowPage;
	     total.submit();
	}

function prePage(nowBlock,nowPage){
	     document.total.nowBlock.value = nowBlock;
		 document.total.nowPage.value = nowPage;
	     total.submit();
	}

function afterBlock(nowBlock,nowPage){
	     document.total.nowBlock.value = nowBlock;
		 document.total.nowPage.value = nowPage;
	     total.submit();
	}
</script>
</head>
<body><center><br>
 <h2>페이징 처리 연습중</h2>
 <br>
  <table>
   <tr>
   <td>다음 : &nbsp;</td>
	<%
	for (int i = beginPerPage;i < (beginPerPage+numPerPage); i++) { 
		if (i==totalRecord){ break;}
	%>
	<td align=center><%= totalRecord - i %>&nbsp;</td>
	<%}%>
 </tr>
 </table><p>
 <table>
  <tr>
   <td align="left" > 
	<% if(totalRecord !=0){ %> Go to Page 
	<% if (nowBlock > 0) {%> 
	<a href="javascript:preBlock('<%=nowBlock - 1 %>','<%=((nowBlock - 1) * pagePerBlock)%>')">
	   이전 <%=pagePerBlock %></a>
	<%}%> 

	:::

	<%
	   for (int i = 0; i < pagePerBlock; i++) { %>
	   <a href="javascript:prePage('<%=nowBlock%>','<%=(nowBlock*pagePerBlock) + i %>')">
	<%=(nowBlock * pagePerBlock) + i + 1 %></a>
    <% if ((nowBlock * pagePerBlock) + i + 1 == totalPage) { break; } %>
	<%} %>

	::: 

	<% if (totalBlock > nowBlock + 1) { %> 
	<a href="javascript:afterBlock('<%=nowBlock + 1 %>','<%=((nowBlock +1) * pagePerBlock)%>')">
	다음 <%=pagePerBlock%>媛�</a>
	<%}%>

	<%} else{out.println("�깅��� 寃���臾쇱�� ���듬����."); }%>
    </td>
   </tr>
  </table>
  <form name="total">
   <input type="hidden" name ="totalRecord" value="<%=totalRecord%>">
   <input type="hidden" name ="nowBlock">
   <input type="hidden" name ="nowPage">
  </form>
  </center>
 </body>
</html>