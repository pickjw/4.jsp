package lee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class WorldActionController implements Controller {
   //하나의 컨트롤러에 여러개의 요청명령어를 사용할 수가 있다.
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("WorldActionController의 handleRequest()호출됨!!");
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("list2");//
		mav.addObject("message2","클릭 둘!");
		return mav; 
	}
}




