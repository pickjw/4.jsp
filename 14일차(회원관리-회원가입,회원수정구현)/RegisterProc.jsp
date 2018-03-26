<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>회원가입 확인</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js?ver=1"></script>
</head>
<body bgcolor="#996600">
<br><br>
<table align="center" border="0" cellspacing="0" cellpadding="5" >
  <tr> 
    <td align="center" valign="middle" bgcolor="#FFFFCC"> 
      <table border="1" cellspacing="0" cellpadding="2"  align="center">
        <form name="regForm" method="post" action="MemberInsert.jsp">
          <tr align="center" bgcolor="#996600"> 
            <td colspan="3"><font color="#FFFFFF">
            <b>
              홍길동 회원님이 작성하신 내용입니다. 확인해주세요
            </b></font></td>
          </tr>
          <tr> 
            <td width="16%">아이디</td>
            <td width="57%"> 
            <input type="text" name="mem_id"  value="nup">
		    </td>
          </tr>
          <tr> 
            <td>패스워드</td>
            <td> <input type="password" name="mem_passwd"  value="1234">
         </td>
            
          </tr>
          <tr> 
            <td>이름</td>
            <td> <input type="text" name="mem_name" value="홍길동"> </td>
            
          </tr>
          <tr> 
            <td>이메일</td>
            <td> <input type="text" name="mem_email" value="test@daum.net"> </td>
            
          </tr>
          <tr>  
            <td>전화번호</td>
            <td> <input type="text" name="mem_phone" value="01-123-0987"> </td>
            
          </tr>
		  <tr>  
            <td>우편번호</td>
            <td> <input type="text" name="mem_zipcode" 
           value="<jsp:getProperty name="mem" property="mem_zipcode" />">
                 <input type="button" value="우편번호찾기" onClick="zipCheck()"></td>
            
          </tr>
		  <tr>  
            <td>주소</td>
            <td><input type="text" name="mem_address" value="서울시 강남구 대현빌딩"></td>
           
          </tr>
		  <tr>  
            <td>직업</td>
            <td><input type="text" name="mem_job" value="웹프로그래머">
            </td>
          </tr>
          <tr> 
            <td colspan="3" align="center"> 
             <input type="submit" value="확인완료" > 
              &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
             <input type="button" value="다시쓰기" onclick="history.back()"> 
            </td>
          </tr>
        </form>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
