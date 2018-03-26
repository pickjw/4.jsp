package spring3;

//문자를 전송->기간을 정하기
public class SystemMoniter {

	private long periodTime;//기간
	//has a 관계
	private SmsSender sender;//~sender=new SmsSender()
	
	//Setter Method
	public void setPeriodTime(long periodTime) {
		this.periodTime = periodTime;//this.periodTime=10
		System.out.println("setPeriodTime()호출됨!!!");
	}
	public void setSender(SmsSender sender) {
		this.sender = sender;
		System.out.println("setSender()호출됨!!!"+sender);
	}
	public String toString() {
		return "SystemMoniter[periodTime="+periodTime+",sender="+sender+"]";
	}
}





