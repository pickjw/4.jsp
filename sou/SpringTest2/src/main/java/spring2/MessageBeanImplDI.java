package spring2;

//DI->필요로하는 위치에 객체를 불러와서 넣어주는것(주입)
public class MessageBeanImplDI  implements MessageBeanDI{
  
	private String name1,name2;//생성자를 통해서 값을 저장
	private String greeting;//Setter Method를 통해서 값을 저장
	//--------------상대방의 클래스의 객체선언-----------------------------------
	private OutFile outF;//인터페이스형으로 객체를 받아올려고 한다.

	public void setOutF(OutFile outF) {
		this.outF = outF;
		System.out.println("setOutF()호출됨!"+outF);//주소값이 출력확인
	}
	//--------------------------------------------------
	//1.멤버변수의 값을 초기화->생성자-><constrcutor-arg>태그이용
	//public MessageBeanImplDI() {}
	public MessageBeanImplDI(String name1, String name2) {
		this.name1 = name1;//this.name1="대한민국"
		this.name2 = name2;//this.name2="서울"
		System.out.println("MessageBeanImplDI 생성자 호출됨!");
	}
    //2.<property>태그
	public void setGreeting(String greeting) {
		this.greeting = greeting;
		System.out.println("setGreeting() 호출됨!");
	}

	@Override
    public void sayHello() { //out()
	// TODO Auto-generated method stub
	String message=greeting+name1+","+name2+"!";
	System.out.println("message=>"+message);
	//파일에 내용을 출력
	try {
		outF.out(message);
	}catch(Exception e) {
		e.printStackTrace();
	}
  }
}









