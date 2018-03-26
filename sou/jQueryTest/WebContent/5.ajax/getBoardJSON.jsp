<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.*, board.*"%>
 [
   <%
      Connection con=null;
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      DBConnectionMgr pool=null;
      String sql="";
      
      try{
    	  pool=DBConnectionMgr.getInstance();
    	  con=pool.getConnection();
    	  System.out.println("con=>"+con);
    	  sql="select * from board order by num";
    	  pstmt=con.prepareStatement(sql);
    	  rs=pstmt.executeQuery();
    	  while(rs.next()){
    		  int num=rs.getInt("num");
    		  String writer=rs.getString("writer");
    		  String subject=rs.getString("subject");
    		  String content=rs.getString("content");
    		  //[{num:1,writer:홍길동,~},{num:2~}]
    		  if(rs.getRow() >1){//한개이상이라면
    			  out.print(",");
    		  }%>
    		  {
    			 "num":<%=num%>,
    			 "writer":<%=writer%>,
    			 "subject":<%=subject%>,
    			 "content":<%=content%>
    		  }
    <% 
    	  }
      }catch(Exception e){
    	  System.out.println("getBoardJSON.jsp파일 오류="+e);
      }finally{
    	  pool.freeConnection(con,pstmt,rs);
      }
   %>
 ]
 