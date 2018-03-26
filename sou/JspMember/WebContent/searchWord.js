/**
 * searchWord.js ->Register.jsp에서 불러오게
 * xhr객체를 생성->요청->jsp로 요청->응답->콜백함수를 출력
 */

var xhrObject //xhr객체를 전역변수로 선언

//1.xhr객체를 생성시키는 함수 선언
function createXHR(){ //function 호출할 함수명(~){} 
	if(window.XMLHttpRequest){//XMLHttpRequest을 가지고 있다면
		xhrObject=new XMLHttpRequest();//객체를 생성하는 구문(함수)
		alert(xhrObject)
	}
}

//2.중복id를 입력->xhr객체->요청->처리해주는 함수
function idCheck(id){  //Register.jsp
	if(id==""){
		//var mem_id=document.getElementById("ducheck");//<td>정보
		var mem_id=$("ducheck")
		alert(mem_id)//[object~]
	$("ducheck").innerHTML="<font color='red'>아이디를 먼저 입력요망</font>"
		//document.regForm.mem_id.focus() 순수 자바스크립트 구문
		$("mem_id").focus()
		return false;
	}
	//입력했다면->Ajax를 이용해서 IdCheck.jsp를 요청
	//1.xhr객체생성
	createXHR()//xhrObject
	var url="http://localhost:8090/JspMember/IdCheck.jsp?"+getParameterValues()
	alert(url)
	//2.콜백함수 지정
	xhrObject.onreadystatechange=resultProcess
	//3.open함수를 이용 서버에 요청준비
	xhrObject.open("Get",url,true)//비동기
	//4.send()요청
	xhrObject.send(null);
}

//3.새로고침 기능을 구현->파라미터값을 전달해주는 함수
//뒤에 반복적인 요청을 하지 않도록 오늘날짜의 시간을 뒤에 추가->매번 새로운요청
//서버의 메모리에 저장->바로바로 새로고침이 된다.

function getParameterValues(){
	var mem_id=$("mem_id").value  //document.RegForm.mem_id.value
	return "mem_id="+mem_id+"&timestamp="+new Date().getTime()
}

//4.콜백함수->특정위치에 화면에 결과를 출력
function resultProcess(){   //xml,text(html태그 포함한 문자열)
	alert("resultProcess")
	if(xhrObject.readyState==4){//서버에게 요청을 정상적으로 처리했다면
		if(xhrObject.status==200){//서버의 결과를 받았다면
			var result=xhrObject.responseText;//태그+문자열=문자열
			$("ducheck").innerHTML=result;
		}
	}
}










