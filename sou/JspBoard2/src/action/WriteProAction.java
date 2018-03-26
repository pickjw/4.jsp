package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lys.board.*;
import java.sql.Timestamp;//시간(추가)

//   /writePro.do?num=?&,,,,,
public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
	     request.setCharacterEncoding("utf-8");//한글
	     //BoardDTO->Setter Method호출(5개)+hidden(4개)->9개
	     //BoardDTO article=new BoardDTO();
	     //property="*" =>매개변수로 전달받은 경우에만 적용
         BoardDTO article=new BoardDTO();
	     article.setNum(Integer.parseInt(request.getParameter("num")));
	     article.setWriter(request.getParameter("writer"));
	     article.setEmail(request.getParameter("email"));
	     article.setSubject(request.getParameter("subject"));
	     article.setPasswd(request.getParameter("passwd"));
	     //추가
	     article.setReg_date(new Timestamp(System.currentTimeMillis()));
	     article.setRef(Integer.parseInt(request.getParameter("ref")));
	     article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
	     article.setRe_level(Integer.parseInt(request.getParameter("re_level")));
	     article.setContent(request.getParameter("content"));
	     article.setIp(request.getRemoteAddr());//원격 ip주소
	    
	   BoardDAO dbPro=new BoardDAO();
	   dbPro.insertArticle(article);//num=0
		//request.setAttribute()왜 사용->페이지 이동=>전달할 매개변수가 필요한 경우
	    //에만 코딩
		return "/writePro.jsp";
	}
}





