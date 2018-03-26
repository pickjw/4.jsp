package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//  /writeForm.do
public class WriteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
	      //list.do(글쓰기)->신규글, content.jsp(글상세보기)->답변글쓰기->답변글
	    int num=0,ref=1,re_step=0,re_level=0; //writePro.jsp 전달->신규글
	     //content.do에서 num이하가 넘어오는 경우라면
	    if(request.getParameter("num")!=null){ //1이상
	    	num=Integer.parseInt(request.getParameter("num"));
	    	ref=Integer.parseInt(request.getParameter("ref"));
	    	re_step=Integer.parseInt(request.getParameter("re_step"));
	    	re_level=Integer.parseInt(request.getParameter("re_level"));
	    	System.out.println("content.jsp에서 넘어온 매개변수값을 확인");
	    	System.out.println
	    	("num="+num+",ref="+ref+",re_step="+re_step+",re_level="+re_level);
	    }
	    
	    //2.액션클래스가 처리한 결과->request저장->페이지 이동
	    request.setAttribute("num", num); //${num}
	    request.setAttribute("ref", ref);
	    request.setAttribute("re_step", re_step);
	    request.setAttribute("re_level", re_level);
	    
	    //3.return 경로포함해서 이동할 페이지명
		return "/writeForm.jsp";
	}

}




