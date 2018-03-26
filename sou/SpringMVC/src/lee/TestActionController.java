package lee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//public class ListAction implements CommandAction {
public class TestActionController implements Controller {

	//public String requestPro(HttpServletRequest request, HttpServletResponse response)
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("TestActionController의 handleRequest()호출됨!!");
		//어떤기능(글쓰기,글수정,,,,)=>결과 존재
		ModelAndView mav=new ModelAndView();
		//mav객체명.setViewName("이동할 파일명");
		mav.setViewName("list3");//경로X ,파일의 확장자X ->이동할 이름만 지정
		//request.setAttribute("greeting","스프링세상!");
		mav.addObject("greeting","스프링세상!");
		return mav; //return "/list.jsp";
	}
}




