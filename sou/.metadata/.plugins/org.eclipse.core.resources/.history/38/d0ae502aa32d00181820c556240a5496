package lee;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

  //   /list.do->자동적으로 호출되는 메서드(handleRequest() 호출)
/*
 * <bean name="/retrieve.do" class="lee.RetrieveActionController">
     <property name="dao">
         <ref bean="boardDAO" />
     </property>
   </bean>
 */
//Controller인터페이스를 상속->handleRequest()를 사용이 가능->요청->처리역할
public class RetrieveActionController implements Controller {

	BoardDAO dao;//BoardDAO dao=new BoardDAO() (X)

	public void setDao(BoardDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao)->"+dao);
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, 
			                                                HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("RetrieveActionController 실행됨!");
		//BoardDAO dao=new BoardDAO() (X)
		String num=request.getParameter("num");
		//
		//BoardDTO data=dao.retrieve(num); before
		//after
		dao.updateReadcnt(num);//readcnt=readcnt+1
		BoardCommand data=dao.retrieve(num);
		//--------------------------------------------------------
		//실행결과를 ModelAndView객체에 담아서 컨트럴러에 전달->페이지로 이동
		ModelAndView mav=new ModelAndView("retrieve");
		//mav.setViewName("retrieve");//retrieve.jsp  이동할 페이지 지정
		//request.setAttribute("data",data);
		mav.addObject("data",data); //${data} <%=request.getAttribute("data")%>
		return mav;
	}
}



