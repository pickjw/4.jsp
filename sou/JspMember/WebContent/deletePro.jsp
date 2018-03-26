<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="hewon.MemberDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
   //추가 deletePro.jsp?mem_id='nup'
   String mem_id=request.getParameter("mem_id");
   String passwd=request.getParameter("passwd");
   
   System.out.println("deletePro.jsp의 mem_id=>"+mem_id);
   System.out.println("deletePro.jsp의 passwd=>"+passwd);
   
   MemberDAO memMgr=new MemberDAO();//탈퇴 메서드를 호출하기위해서
   int check=memMgr.deleteMember(mem_id,passwd);
   System.out.println("deletePro.jsp의 회원삭제유무 check=>"+check);
%>
   <%
       if(check==1){  //탈퇴에 성공했다면
    	   session.invalidate();//세션해제->더이상 로그인상태를 유지X
    %>
    	 <script>
    	      alert('<%=mem_id%>님 성공적으로 탈퇴처리 되었습니다.!')
    	      location.href="Login.jsp" //로그인 창으로 전환
    	 </script>
     <%}else { %>
          <script>
             alert("비밀번호가 틀립니다.\n 다시 한번 확인하시기 바랍니다.")
             history.back();//history.go(-1)
          </script>
     <% } %>
 




