package spring2;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

//xml 설정->지정한 객체를 가져와서 메서드 호출해보자
public class HelloMain {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//2.상대경로->applicationContext.xml=>src폴더에 저장
		Resource resource=new ClassPathResource("/spring2/initapp.xml");
	    //2.xml파일을 읽어들여서 메모리에 로드->빈즈공장(=컨테이너)
		BeanFactory factory=new XmlBeanFactory(resource);
		
		//3.BeanFactory를 통해서 내가 원하는 객체를 꺼내올 수가 있다.
	    //<bean id="mBean" class="spring2.MessageBeanImplDI">
		MessageBeanDI bean=(MessageBeanDI)factory.getBean("mBean");//getBean("id값")
		MessageBeanDI bean2=(MessageBeanDI)factory.getBean("mBean");
		MessageBeanDI bean3=(MessageBeanDI)factory.getBean("mBean");
		
		System.out.println("bean=>"+bean);
		System.out.println("bean2=>"+bean2);
		System.out.println("bean3=>"+bean3);
		
		bean.sayHello();
	}
}



