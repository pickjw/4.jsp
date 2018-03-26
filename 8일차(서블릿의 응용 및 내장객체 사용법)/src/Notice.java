

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Notice
 */
@WebServlet("/Notice")
public class Notice extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");//�ѱۼ���
		PrintWriter out=response.getWriter();//Ŭ���̾�Ʈ���� �����͸� �����ϱ�����
		out.println("<html><head><title>��������</title></head>");
		out.println("<body>");
		//������ ��ɿ� �ش��ϴ� ������ �ۼ��ϸ� �ȴ�.
		out.println("<h2>������ ��������</h2>");
		//->/notice/2018119.txt
		String fileName="";//�ҷ��� ���ϸ��� ����
		//1.��¥���ϱ�->Date,Calendar(�뼼)
		Calendar cal=Calendar.getInstance(); //Date cal=new Date();
		fileName+=cal.get(Calendar.YEAR);//2018
		fileName+=cal.get(Calendar.MONTH)+1; //0~11->+1->�������� ��ǥ��
		fileName+=cal.get(Calendar.DATE);//1~31 (2������ �ڵ����)//2018119
		fileName+=".txt";//2018119.txt
		//��θ�+���ϸ�
		String realPath="C:/webtest/4.jsp/sou/ServletTest/WebContent/notice/"+fileName;
		System.out.println("realPath=>"+realPath);
		try {
			//FileInputSteam(����) ,FileReader(�ѱ�)<->FileWriter
			BufferedReader br=new BufferedReader(new FileReader(realPath));
			String line="";//���پ� �о�鿩�� ������ ����
			while((line=br.readLine())!=null) {
				out.println(line+"<br>");//Ŭ���̾�Ʈ�� �������� ����(�˾�â)
			}
			br.close();
		}catch(IOException e) {
			System.out.println("�ҷ��� ������ ��ο� ���ϸ��� Ȯ�ο��!"+e);
		}catch(Exception e) {
			System.out.println("���� ���������� �����ϴ�."+e);
		}
		//�ݱ��ư
		out.println("<p align=center>");
		out.println("<hr>");
		out.println("<input type='submit' value='â�ݱ�' onclick='window.close()'>");
		out.println("</body>");
		out.println("</html>");
	}
}




