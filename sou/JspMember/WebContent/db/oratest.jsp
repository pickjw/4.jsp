<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JDBC Programming</title>
</head>
<body>
   <h3>JDBC_Oracle 접속방법</h3>
    <%
       String url="jdbc:oracle:thin:@localhost:1521:orcl";
       Connection con=null;//연결객체
       //sql구문 3개
       Statement stmt=null;
       PreparedStatement pstmt=null;
       Statement stmt2=null;
       ResultSet rs=null;//select
       String sql="";//sql구문을 저장
    
       try{
         //1.JDBC Driver를 메모리에 로드(상위패키지명.하위패키지명...드라이버클래스명)
         Class.forName("oracle.jdbc.driver.OracleDriver");//mysql
         //2.Connection 객체 얻어오기
         con=DriverManager.getConnection(url, "test1", "t1234");
         out.println("con=>"+con);
         //3.테이블생성->
         stmt=con.createStatement();//stmt->모두 인터페이스 객체로 구성
         sql="create table MyTest2(name varchar2(20),age number)";
         //구조변경->executeUpdate
         stmt.executeUpdate(sql);
         out.println("MyTest2 테이블 생성 OK!!!<br>");
         //4.insert->executeUpdate
         pstmt=con.prepareStatement("insert into MyTest2 values(?,?)");
         pstmt.setString(1,"Test1");
         pstmt.setInt(2,34);
         //1(sql구문이 성공적으로 실행이 되는 경우),0(sql구문이 실패한 경우)
         int insert=pstmt.executeUpdate();//pstmt.execute()
         out.println("insert(성공유무)=>"+insert+"<br>");
         //5.select=>테이블태그에 출력
         stmt2=con.createStatement();
         rs=stmt2.executeQuery("select * from MyTest2");
 %>
         <table border="1" cellspacing="0" cellpadding="0">
         <tr bgcolor="pink">
            <th>name</th>
            <th>age</th>
         </tr>
         <%
             //rs.first()->맨 처음 레코드로 이동 ,rs.next()->다음 레코드로
             //rs.previous()->이전 레코드로  rs.last()->맨 마지막레코드로 이동
            while(rs.next()){
         %>
         <tr><!-- select name,age from mytest2 -->
             <td><%=rs.getString(1) %></td>
             <td><%=rs.getInt("age") %></td>
         </tr>
 <% 
            }//while
           //6.DB객체 메모리 해제->생성된 객체 순서의 역순으로 코딩
           rs.close(); stmt2.close(); pstmt.close(); stmt.close();
           con.close();
       }catch(Exception e){
    	   out.println("DB접속오류=>"+e);
       }
    %>
        </table>
</body>
</html>






