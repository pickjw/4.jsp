package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lys.board.*;

//   /updatePro.do?num=?&,,,,,
public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
	     request.setCharacterEncoding("utf-8");//한글
	     String pageNum=request.getParameter("pageNum");
	     
         BoardDTO article=new BoardDTO();
	     article.setNum(Integer.parseInt(request.getParameter("num")));//hidden
	     article.setWriter(request.getParameter("writer"));
	     article.setEmail(request.getParameter("email"));
	     article.setSubject(request.getParameter("subject"));
	     article.setContent(request.getParameter("content"));
	     article.setPasswd(request.getParameter("passwd"));
	     
	   BoardDAO dbPro=new BoardDAO();
	   int check=dbPro.updateArticle(article);//num=0
		
	   //updatePro.jsp에서 어느 페이지에서 게시물이 수정됐는지를 보여주기위해서
	   request.setAttribute("pageNum", pageNum);
	   request.setAttribute("check", check);
	   
		return "/updatePro.jsp";
	}
}





