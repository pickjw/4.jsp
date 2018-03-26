package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//추가
import  lys.board.*;

// /updateForm.do?num=11&pageNum=1
public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
	    //updateForm.jsp?num=3&pageNum=1
	    int num=Integer.parseInt(request.getParameter("num"));
	    String pageNum=request.getParameter("pageNum");
	    BoardDAO dbPro=new BoardDAO();
	    //select * from board where num=?
	    BoardDTO article=dbPro.updateGetArticle(num);
	
	    //서버의 메모리에 저장
	    request.setAttribute("pageNum", pageNum);
	    request.setAttribute("article", article);
	    
		return "/updateForm.jsp";  //return  "/member/updateForm.jsp"
	}
}



