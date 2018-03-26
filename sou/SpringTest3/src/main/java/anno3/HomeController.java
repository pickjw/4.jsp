package anno3;

//같은 클래스자료형이 여러개 있을때 어떻게 각각 구분해서
//객체를 가져오는 가?
import javax.annotation.Resource;

public class HomeController {

	@Resource(name="camera2")
	private Camera camera2;//new Camera()->xml에서 설정
	
	private Camera camera3;
	private Camera camera4;
	/*
	 * @Resource(name="빈즈의 구분자id값")->멤버변수에 설정
	 *  ->setXXX메서드 생략이 가능하다.->자동으로 연결할 의존객체를 얻어올때사용
	 * 
	 */
	
	//setCamera2()를 안만든 이유
	@Resource(name="camera3")
	public void setCamera3(Camera camera3) {
		this.camera3 = camera3;
		System.out.println("setCamera3()호출됨!");
	}
	
	@Resource(name="camera4")
	public void setCamera4(Camera camera4) {
		this.camera4 = camera4;
		System.out.println("setCamera4()호출됨!");
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "HomeController[camera2="+camera2+",camera3="+camera3
				    +",camera4="+camera4+"]";
	}
	
}





