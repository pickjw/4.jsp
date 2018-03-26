package anno2;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

//문자를 전송->기간을 정하기
public class SystemMoniter {

	private long periodTime;//기간
	
	//has a 관계
	//@Autowired(required=false)
	@Inject
	private SmsSender sender;//~sender=new SmsSender()
	
	//Setter Method
	public void setPeriodTime(long periodTime) {
		this.periodTime = periodTime;//this.periodTime=10
		System.out.println("setPeriodTime()호출됨!!!");
	}
	/*
	@Autowired
	public void setSender(SmsSender sender) { //주로 매개변수가 객체형인경우
		this.sender = sender;
		System.out.println("setSender()호출됨!!!"+sender);
	}*/
	
	public String toString() {
		return "SystemMoniter[periodTime="+periodTime+",sender="+sender+"]";
	}
}





