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
    <title>jQueryMoble과 DB연동방법2</title>

    <!-- 모바일에서 환경설정(cdn->jquerymobile.com) -->
    <link href="../mobile/jquery.mobile-1.3.2.min.css" rel="stylesheet">
    <style type="text/css">
       .image_phone{
           width:100;
           margin:0 15px 0 0;
           float:left; /* 이미지 왼쪽 정렬 */
       }
      .content_phone{
           font-size:13pt;
      }
    </style>
      <script src="../mobile/jquery-1.9.1.min.js"></script>
      <script src="../mobile/jquery.mobile-1.3.2.min.js"></script>
  </head>
  <body>
    <%
        //phonedetail.jsp?pno=2
        int pno=Integer.parseInt(request.getParameter("pno"));
        System.out.println("pno->"+pno);
    
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        DBConnectionMgr pool=null;//DB접속때문에->JNDI(xml)->MyBatis
        
        try{
           pool=DBConnectionMgr.getInstance();
           con=pool.getConnection();
           String sql="select * from phone where productnum=?";
           pstmt=con.prepareStatement(sql);
           pstmt.setInt(1,pno);
           rs=pstmt.executeQuery();
    %>
    <div data-role="page">
     <div data-role="header">
       <h1>휴대폰 상세</h1>
     </div>
    <div data-role="content">
            <%
                 if(rs.next()){
                	int proudctnum=rs.getInt("productnum");//폰번호
                	String model=rs.getString("model");//모델명
                	String company=rs.getString("company");//제조사
                	int price=rs.getInt("price");//가격
                	String color=rs.getString("color");//색깔
                	String image=rs.getString("image");//폰이미지
            %>
             <!-- 폰이미지와 상품정보출력 -->
             <div class="image_phone">
                <img src="../image/<%=image%>" >
             </div>
             <div class="content_phone">
                <p>모델명: <%=model%></p>
                <p>제조사: <%=company%></p>
                <p>상품가격: <%=price%></p>
                <p>색상: <%=color%></p>
             </div>
             <% } %>
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






