

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
		response.setContentType("text/html; charset=UTF-8");//한글설정
		PrintWriter out=response.getWriter();//클라이언트에게 데이터를 전송하기위해
		out.println("<html><head><title>공지사항</title></head>");
		out.println("<body>");
		//서블릿의 기능에 해당하는 구문을 작성하면 된다.
		out.println("<h2>오늘의 공지사항</h2>");
		//->/notice/2018119.txt
		String fileName="";//불러올 파일명을 저장
		//1.날짜구하기->Date,Calendar(대세)
		Calendar cal=Calendar.getInstance(); //Date cal=new Date();
		fileName+=cal.get(Calendar.YEAR);//2018
		fileName+=cal.get(Calendar.MONTH)+1; //0~11->+1->정상적인 월표시
		fileName+=cal.get(Calendar.DATE);//1~31 (2월달은 자동계산)//2018119
		fileName+=".txt";//2018119.txt
		//경로명+파일명
		String realPath="C:/webtest/4.jsp/sou/ServletTest/WebContent/notice/"+fileName;
		System.out.println("realPath=>"+realPath);
		try {
			//FileInputSteam(영문) ,FileReader(한글)<->FileWriter
			BufferedReader br=new BufferedReader(new FileReader(realPath));
			String line="";//한줄씩 읽어들여서 저장할 변수
			while((line=br.readLine())!=null) {
				out.println(line+"<br>");//클라이언트의 브라우저로 전송(팝업창)
			}
			br.close();
		}catch(IOException e) {
			System.out.println("불러올 파일의 경로와 파일명을 확인요망!"+e);
		}catch(Exception e) {
			System.out.println("오늘 공지사항이 없습니다."+e);
		}
		//닫기버튼
		out.println("<p align=center>");
		out.println("<hr>");
		out.println("<input type='submit' value='창닫기' onclick='window.close()'>");
		out.println("</body>");
		out.println("</html>");
	}
}




