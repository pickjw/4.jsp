package spring5;

//문자를 전송 시스템
public class SmsSender {
     
	boolean flag;//문자가 제대로 전달됐는지 체크
	
	//매개변수가 존재하는 생성자가 한개라도 존재하면 기본생성자는 안만들어준다
	//constructor-arg이용
    public SmsSender(boolean flag) {
    	this.flag=flag;
    }
	//-----------------------------------------------
	public String toString() {
		return "SmsSender 호출";
	}
}
