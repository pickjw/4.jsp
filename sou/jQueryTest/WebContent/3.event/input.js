/**
 *  회원가입시 필수입력을 체크->에러메세지 출력->입력->전송
 */
$(function(){
	//특정태그를 화면에 보여주고,안보여주기(hide()<->show())
	$('#id_error').hide() //형식)$(선택자).hide()<->show()
	$('#age_error1').hide()
	$('#age_error2').hide()
	$('#p_error1').hide()
	$('#p_error2').hide()
	
	//이벤트 처리->회원가입을 눌렀다면
	$('#btnSend').click(function(){
		//1.id입력체크
		var id=$('#userid').val()
		if(id.length < 1){ //id값을 입력하지 않았다면
			$('#id_error').show()//에러메세지 태그 출력
			return false;
		}else{//한글자 이상 입력했다면   }else if(id.length > 4){
			$('#id_error').hide()
		}
		//2.age입력체크
		var age=$('#age').val()
		if(age.length < 1){ //age값을 입력하지 않았다면
			$('#age_error1').show()//에러메세지 태그 출력
			return false;
		}else{//한글자 이상 입력했다면   }else if(id.length > 4){
			$('#age_error1').hide()
		}
		//3.age입력->숫자 입력했는지를 체크->2a300 or a2,a->입력받은 문자열의 길이
		//0(48)~9(57)->아스키코드값을 확인->charCodeAt()를 이용
		for(var i=0;i<age.length;i++){ //aba2
			var data=age.charAt(i).charCodeAt(0)//아스키코드값을 반환
			alert(data)//48~57 범위 벗어나면 문자
			if(data < 48 || data > 57){//문자인 경우
				$('#age_error2').show()
				return false;
				break;//
			}else{//정상적으로 숫자만 입력한 경우
				$('#age_error2').hide()
			}
		}
		//4.pwd1입력체크
		var pwd1=$('#pwd1').val()
		if(pwd1.length < 1){ 
			//$('#p_error1').show()//에러메세지 태그 출력
			$('#pwd1').next().show()
			return false;
		}else{
			$('#pwd1').next().hide()
		}
		//5.pwd2불일치여부 확인
		var pwd2=$('#pwd2').val()
		if(pwd2.length < 1){ 
			//$('#p_error1').show()//에러메세지 태그 출력
			$('#pwd2').next().show()
			return false;
		}else{
			$('#pwd2').next().hide()
		}
		//불일치 체크
		if(pwd1!=pwd2){
			$('#p_error2').show()
		}
		//6.다입력했는지 체크->action='register.jsp'
		//<form id="signup" method="post" action='register.jsp'>
		//document.폼객체명.submit()
		$('#signup').attr('action','register.jsp').submit()
		return true;//전송이 가능한 상태
	})
})










