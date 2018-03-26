<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*,phone.*" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,
                                                        maximum-scale=1.0,minimum-scale=1.0,
                                                        user-scalable=no">                                                 
    <title>jQueryMoble과 DB연동방법</title>

    <!-- 모바일에서 환경설정(cdn->jquerymobile.com) -->
    <link href="../mobile/jquery.mobile-1.3.2.min.css" rel="stylesheet">
      <script src="../mobile/jquery-1.9.1.min.js"></script>
      <script src="../mobile/jquery.mobile-1.3.2.min.js"></script>
  </head>
  <body>
    <%
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        DBConnectionMgr pool=null;
        
        try{
           pool=DBConnectionMgr.getInstance();
           con=pool.getConnection();
           System.out.println("접속확인 con=>"+con);
           String sql="select * from phone order by productnum desc";
           pstmt=con.prepareStatement(sql);
           rs=pstmt.executeQuery();
    %>
    <div data-role="page">
    <!--1.header 지정  -->
     <div data-role="header">
       <h1>휴대폰 목록</h1>
     </div>
    <!--2.content 지정  -->
    <div data-role="content">
         <ul data-role="listview" data-filter="true"><!-- 실시간 검색 -->
            <%
                while(rs.next()){
                	int proudctnum=rs.getInt("productnum");//폰번호
                	String company=rs.getString("company");//제조사
                	int price=rs.getInt("price");//가격
                	String image=rs.getString("image");//폰이미지
            %>
             <li><a href="phonedetail.jsp?pno=<%=proudctnum%>">
                <img src="../image/<%=image%>"  width="80" height="80">
                <h1><%=company%></h1>
             </a></li>
             <% } %>
         </ul>
     </div>
    <!--3.footer 지정  -->
    <div data-role="footer">
        <h1>footer</h1>
     </div>
    </div>
    <%
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	pool.freeConnection(con,pstmt,rs);
        }
    %>
  </body>
</html>






