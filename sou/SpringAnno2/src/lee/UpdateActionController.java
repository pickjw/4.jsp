package lee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
//2.5대->3.X에서 없어짐
import org.springframework.web.servlet.mvc.AbstractCommandController;

//사용자로부터 값을 주로 입력을 받아서 처리해주는 컨트롤러클래스
public class UpdateActionController extends AbstractCommandController {
	
	BoardDAO dao;//BoardDAO dao=new BoardDAO() (X)->write()

	public void setDao(BoardDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao)->"+dao);
	}

	//1.request(요청객체) 2.response(응답객체) 
	//3.입력받은값을 따로 저장한객체( Object command(어떠한 자료형도 다저장)->Model)
    // <jsp:setProperoty property="*" />역할
	//4.BindException->사용자로부터 값을 입력시 에러발생->처리해주는 객체
	@Override
protected ModelAndView handle(HttpServletRequest request, 
		           HttpServletResponse response, Object command, 
		           BindException error)  throws Exception {
	// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		BoardCommand data=(BoardCommand)command;
		//추가
		/* before
		String num=request.getParameter("num");//기존의 num값이 전달
		//-----------------------------------------------
		String author=data.getAuthor();
		String content=data.getContent();
	    String title=data.getTitle();
	    dao.update(num,author, title, content);//dao.write(data);
	    */
		//after
		dao.update(data);
	   
	    return new ModelAndView("redirect:/list.do");
  }
}


