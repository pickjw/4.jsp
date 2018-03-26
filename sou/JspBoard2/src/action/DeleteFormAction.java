package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//  /detelForm.do?num=?&pageNum
public class DeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		 //deleteForm.do?num=3&pageNum=3
	    int num=Integer.parseInt(request.getParameter("num"));//삭제할 게시물번호
	    String pageNum=request.getParameter("pageNum");
	    System.out.println("deleteForm.do의 num=>"+num
	    		                                               +",pageNum="+pageNum);
	    request.setAttribute("num", num);
	    request.setAttribute("pageNum", pageNum);
	    
		return "/deleteForm.jsp" ;
	}
}
