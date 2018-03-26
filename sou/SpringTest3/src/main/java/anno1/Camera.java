package anno1;

import org.springframework.beans.factory.annotation.Required;

//cctv 관리
public class Camera {
	
	private int number;//카메라수
	
	@Required
	public void setNumber(int number) {
		this.number=number;
		System.out.println("setNumber() 호출");
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "설치된 카메라(Camera)수[numaber="+number+"]";
	}
}
