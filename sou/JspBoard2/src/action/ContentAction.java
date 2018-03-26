package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//추가
import lys.board.*;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		   //list.jsp에서 링크->content.do?num=3&pageNum=1
		   int num=Integer.parseInt(request.getParameter("num"));//메서드의 매개변수때문에
		   String pageNum=request.getParameter("pageNum");
		   
		   BoardDAO dbPro=new BoardDAO();
		   //조회수 증가,num에 해당되는 레코드한개
		   BoardDTO article=dbPro.getArticle(num);
		   //링크문자열의 길이를 줄이기위해서(댓글때문에)
		   int ref=article.getRef();
		   int re_step=article.getRe_step();
		   int re_level=article.getRe_level();
		   System.out.println("content.do의 매개변수 값");
		   System.out.println("ref->"+ref+",re_step->"+re_step+",re_level=>"+re_level);
		
		   //request에 저장
		   request.setAttribute("num", num);
		   request.setAttribute("pageNum", pageNum);
		   request.setAttribute("article", article);//ref,re_step,re_level이 들어있기때문에
		    //${article.ref}
		   //request.setAttribute("ref","ref");// ${ref} 
		return "/content.jsp";
	}
}




