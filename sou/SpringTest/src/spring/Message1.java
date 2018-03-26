package spring;
//사용자로부터 값을 입력->출력만 메서드 작성
public class Message1 implements MessageInter{
	 @Override
	public void sayHello(String name) {
		// TODO Auto-generated method stub
		 System.out.println("안녕!"+name+"씨!");
	}
}
