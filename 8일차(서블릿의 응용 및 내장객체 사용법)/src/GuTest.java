

import java.io.*;
import javax.servlet.*;
//import javax.servlet.annotation.WebServlet;//ȯ�漳���� ���� �ϱ�����
import javax.servlet.http.*;

/**
 * ����)@WebServlet("���󿡼� ��û�ϴ� url��θ� �����ؼ� �������౸��")
 *        /������/������2/,,,/�����ų ����Ŭ������
 */
//@WebServlet("/GuTest")

public class GuTest extends HttpServlet {
	
	/**
	 * ����Ʈ���� �ϰų� �������̵�->Get���
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");//�ѱۼ���
		PrintWriter out=response.getWriter();//Ŭ���̾�Ʈ���� �����͸� �����ϱ�����
		out.println("<html><head></head>");
		out.println("<body>");
		//������ ��ɿ� �ش��ϴ� ������ �ۼ��ϸ� �ȴ�.
		out.println("<h2>�������� ���</h2>");
		//�߰� 
		out.println("<table border=1>");
		for(int i=2;i<=9;i++) {//2��~9��
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




