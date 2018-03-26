<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="memMgr" class="hewon.MemberDAO" />
<%
    //id,passwd를 받아서 인증 성공->LoginSuccess.jsp or LogError.jsp
    String mem_id=request.getParameter("mem_id");
    String mem_passwd=request.getParameter("mem_passwd");
    System.out.println("mem_id=>"+mem_id+",mem_passwd=>"+mem_passwd);
    //loginCheck()
    //MemberDAO memMgr=new MemberDAO();
    boolean check=memMgr.loginCheck(mem_id, mem_passwd);
%>
<%
    if(check){//인증성공
    	session.setAttribute("idKey",mem_id);//키값 idKey,계정값
    	//response.sendRedirect("LoginSuccess.jsp");//My Page  before
    	response.sendRedirect("Login.jsp");
    }else{// false 인증실패인 경우
    	response.sendRedirect("LogError.jsp");
    }
%>


















