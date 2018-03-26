

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class GetData
 */
@WebServlet("/servelt/GetData")
public class GetData extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");//한글설정
		PrintWriter out=response.getWriter();//클라이언트에게 데이터를 전송하기위해
		out.println("<html><head></head>");
		out.println("<body>");
		//서블릿의 기능에 해당하는 구문을 작성하면 된다.
		out.println("<h2>값을 입력을 받아서 재전송 예제</h2>");
		//한글이 깨지지 않도록
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		String addr=request.getParameter("addr");
		//재전송->요청한 쪽 컴퓨터
		out.println("이름=>"+name+"<br>");
		out.println("주소=>"+addr);
		out.println("</body>");
		out.println("</html>");
	}

}



