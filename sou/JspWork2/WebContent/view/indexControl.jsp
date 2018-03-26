<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   String CONTROL=null; //불러올 파일명
   String PAGENUM=null; //불러올 파일번호
   
   try{
	   //매개변수를 전달X ->디폴트(기본)페이지를 보여줄 수 있도록 설정
	   //매개변수를 전달O ->전달받은 매개변수에 따른 페이지를 보여주면 된다.
	   CONTROL=request.getParameter("CONTROL");
	   PAGENUM=request.getParameter("PAGENUM");
       System.out.println("CONTROL="+CONTROL+",PAGENUM="+PAGENUM);
       
       //만약에 중간에 전달 못 받으면 기본적으로 디폴트 파일 불러오게 하기위해서
       if(CONTROL.equals(null)){
    	   CONTROL="intro";
       }
       
       if(PAGENUM.equals(null)){
    	   PAGENUM="01";
       }
       
   }catch(Exception e){
	   e.printStackTrace();
   }
   //매개변수전달 X  intro,01  매개변수가 전달O ->research, 02
%>
 <jsp:forward page="/template/template.jsp" >
     <jsp:param name="CONTROL"  value="<%=CONTROL %>" />
     <jsp:param name="PAGENUM"  value="<%=PAGENUM %>" />
 </jsp:forward>
 
 
 
 
 
 
 
 