

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
		response.setContentType("text/html; charset=UTF-8");//�ѱۼ���
		PrintWriter out=response.getWriter();//Ŭ���̾�Ʈ���� �����͸� �����ϱ�����
		out.println("<html><head></head>");
		out.println("<body>");
		//������ ��ɿ� �ش��ϴ� ������ �ۼ��ϸ� �ȴ�.
		out.println("<h2>���� �Է��� �޾Ƽ� ������ ����</h2>");
		//�ѱ��� ������ �ʵ���
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		String addr=request.getParameter("addr");
		//������->��û�� �� ��ǻ��
		out.println("�̸�=>"+name+"<br>");
		out.println("�ּ�=>"+addr);
		out.println("</body>");
		out.println("</html>");
	}

}



