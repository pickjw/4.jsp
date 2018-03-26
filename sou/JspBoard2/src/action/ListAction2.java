package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//추가
import lys.board.*;
import java.util.*;

// /list.do=actionListAction(명령처리클래스=스프링(액션)
public class ListAction2 implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		// list.jsp(자바소스 코드+화면출력소스코드)
		
		      int pageSize=5;//numPerPage->페이지당 보여주는 게시물수
		      int blockSize=5;//pagePerBlock->블럭당 보여주는 페이지수
		      // 1 2 3  [이전] 4 5 6  7 8 9  10 11 12
		      
		   //게시판을 맨 처음 실행시키면 무조건 1페이지 부터 출력
		   String pageNum=request.getParameter("pageNum");
		   if(pageNum==null){
			   pageNum="1";//default
		   }
		   int currentPage=Integer.parseInt(pageNum);//현재페이지(정수)->계산
		   //시작레코드번호->limit ?,?
		    //                  (1-1)*10+1=1,(2-1)*10+1=11,(3-1)*10+1=21
		   int startRow=(currentPage-1)*pageSize+1;
		   int endRow=currentPage*pageSize;//1*10,2*10=20,3*10=30(레코드갯수 X)
		   int count=0;//총레코드수
		   int number=0;//beginPerPage->페이지별 시작하는 맨 처음에 나오는 게시물번호
		   List articleList=null;//화면에 출력할 레코드데이터
		   
		   BoardDAO dbPro=new BoardDAO();
		   count=dbPro.getArticleCount();//select count(*) from board
		   System.out.println("현재 레코드수(count)=>"+count);
		   if(count > 0){
			   articleList=dbPro.getArticles(startRow, pageSize);//10개씩 (endRow X)
		   }else {
			   articleList=Collections.EMPTY_LIST;//아무것도 없다는 표시
		   }
		   //            122-(1-1)*10=122,121,120,119
		   //            122-(2-1)*10=110,109,108,,,
		   number=count-(currentPage-1)*pageSize;
		   System.out.println("페이지별 number=>"+number);
		
		   //2.처리결과->request객체에 저장구문
		   request.setAttribute("currentPage", currentPage);//int->Integer
		   request.setAttribute("startRow", startRow);
		   request.setAttribute("count", count);
		   request.setAttribute("pageSize", pageSize);
		   request.setAttribute("blockSize", blockSize);
		   request.setAttribute("number", number);
		   request.setAttribute("articleList", articleList);//${articleList}
		   
		   //3.페이지로 forward로 이동
		return "/list.jsp";  //  /board/list.jsp
	}
}



