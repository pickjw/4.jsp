package lee;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class SearchActionController implements Controller {

	BoardDAO dao;//BoardDAO dao=new BoardDAO() (X)

	public void setDao(BoardDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao)->"+dao);
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, 
			                                                HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SearchActionController 실행됨!");
		//2개의 매개변수(검색분야,검색어)
		String searchName=request.getParameter("searchName");
		String searchValue=request.getParameter("searchValue");
		//ArrayList list=dao.search(searchName,searchValue); before
		//----------------HashMap객체를 이용----------------------------------------------
		BoardCommand data=new BoardCommand();
		data.setSearchName(searchName);
		data.setSearchValue(searchValue);
		List list=dao.search(data);
		//--------------------------------------------------------------
		ModelAndView mav=new ModelAndView();
		mav.setViewName("list");//list.jsp  이동할 페이지 지정
		//request.setAttribute("list",list);
		mav.addObject("list",list); //${list} <%=request.getAttribute("list")%>
		return mav;
	}
}



