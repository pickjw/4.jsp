

import java.io.IOException;//입출력
import java.io.PrintWriter;

import javax.servlet.ServletConfig;//서블릿을 실행시키기 위한 환경설정에 관한클래스
import javax.servlet.ServletException;//서블릿의 예외처리클래스

import javax.servlet.annotation.WebServlet;//서블릿을 웹상에 실행할때 필요
//한 어노테이션에 관련된 클래스
import javax.servlet.http.HttpServlet;//웹상에서 요청시 처리해주는 클래스
import javax.servlet.http.HttpServletRequest;//서블릿(요청에 관련된 인터페이스)
import javax.servlet.http.HttpServletResponse;//서블릿(응답에 관련된 인터페이스)

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/test/imsi/Hello")
public class HelloServlet extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("서블릿이 실행될때 제일 먼저 호출되는 메서드");
		System.out.println("생성자와 같은 역할(서블릿의 초기값을 설정");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("웹어플리케이션이 종료될때(메모리 해제)");
	}
	/**
	 * 사용자가 외부에서 Get방식으로 요청이 들어온다면 자동으로 호출되는 메서드
	 */
	protected void doGet(HttpServletRequest request, //request객체(내장객체)
			                           HttpServletResponse response) //response객체
			                          throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("웹상에서 get방식으로 접속시 자동호출!!(doGet())");
		//contentType="text/html; charset=UTF-8"
		response.setContentType("text/html; charset=UTF-8");//한글설정
		//PrintWriter out=new PrintWriter();
		PrintWriter out=response.getWriter();//클라이언트에게 데이터를 전송하기위해
		out.println("<html><head></head>");
		out.println("<body>");
		//서블릿의 기능에 해당하는 구문을 작성하면 된다.
		out.println("<h2>Hello Servelt Testing 중입니다.!!!");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * 사용자가 post방식으로 요청을 했을때 자동으로 호출되는 메서드
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
