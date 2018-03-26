function loginCheck(){ //Login.jsp
	//document.폼객체명.입력양식객체명.속성명
	if(document.login.mem_id.value==""){
		alert("아이디를를 입력해 주세요.");
		document.login.mem_id.focus();
		return; //return false;
	}
	if(document.login.mem_passwd.value==""){
		alert("비밀번호를 입력해 주세요.");
		document.login.mem_passwd.focus();
		return;
	}
	//document.폼객체명.submit()->전송하라
	document.login.submit();
}
/* Login.jsp
 * <INPUT TYPE="button" value="회원가입" onclick="memberReg()">
 * <INPUT TYPE="button" value="회원가입" 
 *    onclick="document.location='Register.jsp' ">
 */
function memberReg(){  //버튼클릭->특정페이지로 이동
	//response.sendRedirect(~)->자바
	//document.location.href="경로포함해서 이동할 페이지명" (자바스크립트)
	//document.location="Register.jsp"
	document.location="agreement.jsp" //Register.jsp로 이동(동의하면)
}
//회원가입 버튼을 클릭시 호출되는 함수
function inputCheck(){
	if(document.regForm.mem_id.value==""){
		alert("아이디를 입력해 주세요.");
		document.regForm.mem_id.focus();
		return;//return false;
	}
	if(document.regForm.mem_passwd.value==""){
		alert("비밀번호를 입력해 주세요.");
		document.regForm.mem_passwd.focus();
		return;
	}
	if(document.regForm.mem_repasswd.value==""){
		alert("비밀번호를 확인해 주세요");
		document.regForm.mem_repasswd.focus();
		return;
	}
	if(document.regForm.mem_name.value==""){
		alert("이름을 입력해 주세요.");
		document.regForm.mem_name.focus();
		return;
	}
	if(document.regForm.mem_email.value==""){
		alert("이메일을 입력해 주세요.");
		document.regForm.mem_email.focus();
		return;
	}
	if(document.regForm.mem_phone.value==""){
		alert("연락처를 입력해 주세요.");
		document.regForm.mem_phone.focus();
		return;
	}
	if(document.regForm.mem_job.value=="0"){
		alert("직업을 선택해 주세요.");
		document.regForm.mem_job.focus();
		return;
	}
	
	if(document.regForm.mem_passwd.value != document.regForm.mem_repasswd.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.regForm.mem_repasswd.focus();
		return;
	}
	document.regForm.submit(); //action="RegisterProc.jsp"
}

/*//중복ID체크 해주는 자바스크립트함수 선언
function idCheck(id){ //mem_id.value
   if(id==""){
	   alert("아이디를 먼저 입력하세요")
	   document.regForm.mem_id.focus()//document.폼객체명.입력양식.함수명(~)
   }else{ //IdCheck.jsp?매개변수명=전달할값->IdCheck.jsp->checkId(id)
	   //IdCheck.jsp를 요청->매개변수를 전달하는 이유->DB에 접근=>id확인
	   url="IdCheck.jsp?mem_id="+id
	   window.open(url,"post","left=450,top=150,width=300,height=150")
   }
}*/

//우편번호를 검색해주는 함수선언->ZipCheck.jsp?check=y
//check=y=> 검색전의 창의 모습을 구분하는 인자(매개변수로 지정하기위해서)
//scrollbars=yes|no(스크롤바), status=yes|no(상태바)
function zipCheck(){
    url="ZipCheck.jsp?check=y"
    	window.open(url,"post","left=400,top=220,width=500,height=300," +
    			"toolbar=no,menubar=no,status=yes,scrollbars=yes")	
}





