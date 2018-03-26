package anno4;

import javax.annotation.*;

public class HomeController2 {

	 private Camera camera;
	 
	 @Resource(name="camera5")
	 public void setCamera(Camera camera) {
		 this.camera=camera;
		 System.out.println("camera5이름을 가진 setCamera()호출됨");
	 }
	 //추가
	 /*public static void test() {
		 System.out.println("정적메서드 호출(test())");
		 반환형값이 존재(객체형)
	 }*/
	 
	 public static HomeController2 test() {
		 return new HomeController2();
	 }
	 
	 @PostConstruct
	 public void init() {
		 System.out.println("빈즈객체 생성되기전에 초기화 작업(init)호출됨!");
	 }
	 
	 @PreDestroy
	 public void close() {
		 System.out.println("빈즈객체 생성후에 메모리해제(close)호출됨!");
	 }	 
}



