package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lys.board.*;

public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//pageNum이 넘어오는지 체크
		   int num=Integer.parseInt(request.getParameter("num")); //삭제할 게시물번호
		   String pageNum=request.getParameter("pageNum");//필드X
		   String passwd=request.getParameter("passwd");//실제 입력을 받아서 넘어옴
		   System.out.println
		                 ("deletePro.do의 num=>"+num+",pageNum=>"+pageNum);
		   BoardDAO dbPro=new BoardDAO();
		   int check=dbPro.deleteArticle(num,passwd);//(passwd,num)
		   
		   request.setAttribute("pageNum", pageNum);
		   request.setAttribute("check", check);
	
		return "/deletePro.jsp";
	}
}


