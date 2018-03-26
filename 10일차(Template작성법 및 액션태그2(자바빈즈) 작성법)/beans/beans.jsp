<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자바빈즈가 나온 배경설명</title>
</head>
<body>
 <%!
  //입력->매개변수 전달->저장(필드별로 저장(DTO))->Setter호출=>Table저장
  String str="선언문";//웹상에서 입력받을값 저장
  String addr="";
  
  //웹상->Setter 호출->Getter 호출
  public void setStr(String str){
	  this.str=str;//this.str="자바빈즈"
  }
  
  public void setAddr(String addr){
	  this.addr=addr;
  }
 
  public String getStr(){ return str;}
  public String getAddr(){ return addr;}
 %>
  웹상에서 메서드호출(Setter Method):<%  setStr("자바빈즈"); %>
  <p>
  저장된 값  출력(Getter Method):<%=getStr() %>
</body>
</html>






