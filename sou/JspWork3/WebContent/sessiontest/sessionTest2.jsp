<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>실제로 처리</title>
</head>
<body>
<%
       request.setCharacterEncoding("utf-8");
       String sports=request.getParameter("sports");
       String season=request.getParameter("season");
       //서버->접속한 클라이언트(세션id 분배)=>번호표
       String id=(String)session.getAttribute("idKey");//idKey->대소문자 구분
       //서버->세션id부여 확인
       String sessionid=session.getId();//브라우저 확인->클라이언트를 구분
       int interval=session.getMaxInactiveInterval();//<=>setMaxInactiveInterval()
       if(id!=null){//현재 로그인한 계정이 들어 있다면 
    	                  //id=null->로그인한 적이 없는 경우,이미 연결이 끊어진경우
    	                  //로그아웃(logout)
%>                    
     <b><%=id%></b>님이 좋아하시는 스포츠와 계절은<p>
     <b><%=sports %></b>과<b><%=season %></b>입니다.<p>
       세션 id:<%=sessionid %><p>
       세션유지시간:<%=interval %>초<p>
       <%
            session.invalidate();//연결자동 해제하는 구문
       }else{ //id==null(즉 로그인 한적 없거나 연결시간이 초과된 경우)
    	   out.println("세션의 연결시간이 초과되었습니다. 다시 로그인해주세요!!!");
       }
       %>
</body>
</html>



