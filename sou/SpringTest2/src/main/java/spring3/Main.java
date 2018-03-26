package spring3;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring2.MessageBeanDI;

//xml 설정->지정한 객체를 가져와서 메서드 호출해보자
public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1.xml파일이 한개이상이면 배열로 관리->불러오기
		String[] configLocation=new String[] {"app.xml"};//~,"abc.xml","ddd.xml"};
	    //2.xml파일을 읽어들여서 메모리에 로드->빈즈공장(=컨테이너)
		AbstractApplicationContext context=
				new ClassPathXmlApplicationContext(configLocation);
		
		//3.JVM(프로그램)이 종료될때 context객체도 같이 종료할 수 있도록 처리
		context.registerShutdownHook();
		
		//3.BeanFactory를 통해서 내가 원하는 객체를 꺼내올 수가 있다.
	    //<bean id="mBean" class="spring2.MessageBeanImplDI">
		SystemMoniter moniter=(SystemMoniter)context.getBean("moniter");//getBean("id값")
		System.out.println("moniter=>"+moniter);//moniter.toString()
		//4.프로그램이 종료
		context.close();//JVM이 종료시->컨테이너에 존재하는 모든빈(객체) 종료
	}
}



