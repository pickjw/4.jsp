package test;  //1.패키지를 설정->기능별로 빈즈파일을 분류하기위해서 

//2.반드시 public class로 시작해야 한다.

public class BeanTest {  //3.클래스명 XXXBean->XXXDTO, or DAO

//4. 멤버변수앞에 private를 써야 된다.
	
	 private String str="선언문";//웹상에서 입력받을값 저장
	 private String addr="";
	  
	  //웹상->Setter 호출->Getter 호출
	  public void setStr(String str){
		  this.str=str;
		  System.out.println("setStr()호출됨!");
	  }
	  
	  public void setAddr(String addr){
		  this.addr=addr;
		  System.out.println("setAddr()호출됨!");
	  }
	 
	  public String getStr(){ return str;}
	  public String getAddr(){ return addr;}
}
