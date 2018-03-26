package lee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
//2.5대->3.X에서 없어짐
//import org.springframework.web.servlet.mvc.AbstractCommandController;

//사용자로부터 값을 주로 입력을 받아서 처리해주는 컨트롤러클래스
//public class WriteActionController extends AbstractCommandController {

@Controller
public class WriteActionController {
	
	@Autowired
	BoardDAO dao;//BoardDAO dao=new BoardDAO() (X)->write()

	public void setDao(BoardDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao)->"+dao);
	}

	@RequestMapping("/write.do")
    protected ModelAndView test(@RequestParam("title") String title,
    		@RequestParam("author") String author,
    		@RequestParam("content") String content)  throws Exception {
		
	// TODO Auto-generated method stub
		//request.setCharacterEncoding("utf-8");
		//BoardCommand data=(BoardCommand)command;
		/*
		String author=request.getParameter("author");
		String content=request.getParameter("content");
	    String title=request.getParameter("title");*/
	    //최대값을 구해서 num(5)+1->data.setNum(6)
	    int newNum=dao.getNewNum()+1;
	    BoardCommand data=new BoardCommand();
	    data.setNum(newNum);//계산해서 개발자가 입력을 해주는경우
	    data.setTitle(title);
	    data.setAuthor(author);
	    data.setContent(content);
	    //----------------------------------------------------
	    dao.write(data);
	    //-----------------------------------------------------------
	    ModelAndView mav=new ModelAndView("redirect:/list.do");
	    //mav.setViewName("list");
	return mav;
  }
}
