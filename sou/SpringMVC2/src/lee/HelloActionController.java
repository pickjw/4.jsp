package lee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class HelloActionController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("HelloActionController의 handleRequest()호출됨!!");
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("hello");//
		mav.addObject("message","클릭 하나!");//request.setAttribute~
		mav.addObject("message2","클릭 둘!");
		return mav; 
	}
}




