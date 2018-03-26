package lee;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.Controller;

  //   /list.do->자동적으로 호출되는 메서드(handleRequest() 호출)

//public class ListActionController implements Controller {

@Controller
public class ListActionController {
	
	BoardDAO dao;//BoardDAO dao=new SqlMapBoardDao()

	@Autowired
	public void setDao(BoardDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao)->"+dao);
	}
    
	@RequestMapping("/list.do")
	public ModelAndView handleRequest(HttpServletRequest request, 
			                                                HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("handleRequest()가 실행됨!");
		//BoardDAO dao=new BoardDAO() (X)
		//ArrayList list=dao.list();
		List list=dao.list();
		ModelAndView mav=new ModelAndView();
		mav.setViewName("list");//list.jsp  이동할 페이지 지정
		//request.setAttribute("list",list);
		mav.addObject("list",list); //${list} <%=request.getAttribute("list")%>
		return mav;
	}
}



