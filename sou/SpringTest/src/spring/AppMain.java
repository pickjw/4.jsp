package spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

//xml 설정->지정한 객체를 가져와서 메서드 호출해보자
public class AppMain {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       //1.xml파일의 위치를 지정해서 불러온다.->Resource
		/*Resource resource=new FileSystemResource
				("C:/webtest/4.jsp/sou/SpringTest/src/spring/applicationContext.xml");*/
		
		//2.상대경로->applicationContext.xml=>src폴더에 저장
		Resource resource=new ClassPathResource("/spring/applicationContext.xml");
	    //2.xml파일을 읽어들여서 메모리에 로드->빈즈공장(=컨테이너)
		BeanFactory factory=new XmlBeanFactory(resource);
		
		//3.BeanFactory를 통해서 내가 원하는 객체를 꺼내올 수가 있다.
		//Message1 me=new Message1(); 직접 생성
		//<bean id="test"     class="spring.Message1" />
		//Message2 me=new Message2();
		//Message1 me=(Message1)factory.getBean("test");
		MessageInter me=(MessageInter)factory.getBean("test");//getBean("id값")
		me.sayHello("테스트");
	}
}



