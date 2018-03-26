

import java.io.*;
import javax.servlet.*;
//import javax.servlet.annotation.WebServlet;//환경설정을 쉽게 하기위해
import javax.servlet.http.*;

/**
 * 형식)@WebServlet("웹상에서 요청하는 url경로를 지정해서 서블릿실행구문")
 *        /가상경로/가상경로2/,,,/실행시킬 서블릿클래스명
 */
//@WebServlet("/GuTest")

public class GuTest extends HttpServlet {
	
	/**
	 * 사이트접속 하거나 페이지이동->Get방식
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");//한글설정
		PrintWriter out=response.getWriter();//클라이언트에게 데이터를 전송하기위해
		out.println("<html><head></head>");
		out.println("<body>");
		//서블릿의 기능에 해당하는 구문을 작성하면 된다.
		out.println("<h2>구구단을 출력</h2>");
		//추가 
		out.println("<table border=1>");
		for(int i=2;i<=9;i++) {//2단~9단
			out.println("<tr>");
			for(int j=1;j<10;j++) {//2*1~2*9
				out.println("<td>");
				out.println(""+i+"*"+j+"="+(i*j));
				out.println("</td>");
			}
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}




