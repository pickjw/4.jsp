package lee;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.Controller;

  //   /list.do->자동적으로 호출되는 메서드(handleRequest() 호출)
@Controller
public class DeleteActionController {

	@Autowired
	BoardDAO dao;//BoardDAO dao=new BoardDAO() (X)
    /*
	public void setDao(BoardDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao)->"+dao);
	}*/

	@RequestMapping("/delete.do")
	public ModelAndView handleRequest(HttpServletRequest request, 
			                                                HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("DeleteActionController 실행됨!");
		//BoardDAO dao=new BoardDAO() (X)
		String num=request.getParameter("num");
		dao.delete(num);
		//실행결과를 ModelAndView객체에 담아서 컨트럴러에 전달->페이지로 이동
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/list.do");//요청명령어->처리 페이지이동
		//mav.setViewName("list");//새로고침 기능X->단순히 페이지이동
		return mav;
	}
}



