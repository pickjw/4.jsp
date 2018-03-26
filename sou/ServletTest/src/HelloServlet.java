

import java.io.IOException;//�����
import java.io.PrintWriter;

import javax.servlet.ServletConfig;//������ �����Ű�� ���� ȯ�漳���� ����Ŭ����
import javax.servlet.ServletException;//������ ����ó��Ŭ����

import javax.servlet.annotation.WebServlet;//������ ���� �����Ҷ� �ʿ�
//�� ������̼ǿ� ���õ� Ŭ����
import javax.servlet.http.HttpServlet;//���󿡼� ��û�� ó�����ִ� Ŭ����
import javax.servlet.http.HttpServletRequest;//����(��û�� ���õ� �������̽�)
import javax.servlet.http.HttpServletResponse;//����(���信 ���õ� �������̽�)

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/test/imsi/Hello")
public class HelloServlet extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("������ ����ɶ� ���� ���� ȣ��Ǵ� �޼���");
		System.out.println("�����ڿ� ���� ����(������ �ʱⰪ�� ����");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("�����ø����̼��� ����ɶ�(�޸� ����)");
	}
	/**
	 * ����ڰ� �ܺο��� Get������� ��û�� ���´ٸ� �ڵ����� ȣ��Ǵ� �޼���
	 */
	protected void doGet(HttpServletRequest request, //request��ü(���尴ü)
			                           HttpServletResponse response) //response��ü
			                          throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("���󿡼� get������� ���ӽ� �ڵ�ȣ��!!(doGet())");
		//contentType="text/html; charset=UTF-8"
		response.setContentType("text/html; charset=UTF-8");//�ѱۼ���
		//PrintWriter out=new PrintWriter();
		PrintWriter out=response.getWriter();//Ŭ���̾�Ʈ���� �����͸� �����ϱ�����
		out.println("<html><head></head>");
		out.println("<body>");
		//������ ��ɿ� �ش��ϴ� ������ �ۼ��ϸ� �ȴ�.
		out.println("<h2>Hello Servelt Testing ���Դϴ�.!!!");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * ����ڰ� post������� ��û�� ������ �ڵ����� ȣ��Ǵ� �޼���
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
