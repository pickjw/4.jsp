<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>요청을 판단해서 페이지 전송을 결정2</title>
</head>
<body>
   <h1>페이지를 이동시키는 역할</h1>
   <%
     //추가
     int su=100;//a.jsp->공유->su
     System.out.println("su=>"+su);
     
     java.util.Date d=new java.util.Date();//b.jsp에서 공유->출력
     //페이지를 이동시키기 전에 서버의 메모리(HashMap)에 저장->객체만 저장
     //형식) request|session|application|.setAttribute("키",저장할값)
     request.setAttribute("total", su);//new Integer(su)
     request.setAttribute("cal",d);
     //-------------------------------------------------
     String move=request.getParameter("move");
     System.out.println("이동할 페이지명(move=>)"+move);
     //response.sendRedirect(move);
     if(move.equals("a.jsp")){
    	 //response.sendRedirect("a.jsp");
   %>
   <jsp:forward page='a.jsp' /><br>
   <%  }else if(move.equals("b.jsp"))  {%>
   <jsp:forward page='b.jsp' /><br>
   <% }else { %>
      <script>
        window.alert("당신이 요청하신 페이지는 없습니다.\n다시 확인해 보세요!!")
        //history.back() or history.go(-1);
        location.href="forward.jsp"
      </script>
   <%} %>
</body>
</html>


