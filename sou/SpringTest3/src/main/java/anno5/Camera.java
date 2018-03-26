package anno5;


import org.springframework.stereotype.Component;

//특정 패키지(ex  anno5)를 지정->스프링컨테이너가 찾아서  app2Scan.xml
//에서 Camera클래스 객체를 등록(빈즈 등록)

@Component
public class Camera {}  //new Camera()
