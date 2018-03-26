<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="hewon.MemberDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
     request.setCharacterEncoding("utf-8");//Setter Method 호출전에 한글처리
%>

<jsp:useBean id="mem"  class="hewon.MemberDTO"  />
<jsp:setProperty name="mem" property="*" />

<%
   //추가 hidden객체 확인
   String mem_id=request.getParameter("mem_id");
   System.out.println("MemberUpdateProc.jsp의 mem_id=>"+mem_id);
   
   MemberDAO memMgr=new MemberDAO();//수정 메서드를 호출하기위해서
   boolean check=memMgr.memberUpdate(mem);
   System.out.println("memberUpdate.jsp의 회원수정유무 check=>"+check);
%>
   <%
       if(check){  //수정에 성공했다면
    %>
    	 <script>
    	      alert("성공적으로 수정되었습니다.!")
    	      location.href="Login.jsp" //아직 로그아웃 안된 관계로 그대로 화면유지
    	 </script>
     <%}else { %>
          <script>
             alert("수정 도중에 에러가 발생되었습니다.")
             history.back();
          </script>
     <% } %>
 




