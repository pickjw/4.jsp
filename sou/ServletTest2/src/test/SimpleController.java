package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 웹상에서 요청이 들어왔을때 처리해주는 자바프로그램->서블릿(서버에서 실행)
 */
@WebServlet("/SimpleController")
public class SimpleController extends HttpServlet {
	   
    
	/**
	 *  웹상에서 문자열을 클릭->요청(get방식)->doGet()이 처리
	 */
	protected void doGet(HttpServletRequest request, 
			                           HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   processRequest(request,response);
	}

	/**
	 *  사용자가 회원가입,회원수정,로그인(값을 입력받아서 전송)->post->doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   processRequest(request,response);
	}

	//사용자가 Get or Post->processRequest()를 이용해서 처리
	private void processRequest(HttpServletRequest request,
			                                     HttpServletResponse response) 
			                                   throws ServletException,IOException {
		//요청명령어->처리되는 결과가 다 다르다.(설계)
		//1.요청명령어를 분석
		String type=request.getParameter("type");
		System.out.println("type=>"+type);
		//2.greeting=>안녕하세요(String),  date->오늘날짜 객체를 생성=->출력
		//요청명령어에 따른 서로 다른 요청기능을 구현하기위해서 Object를 선언
		Object resultObject=null; //String or Date(반환 객체)
		//3.요청명령어에 따른 객체를 생성
		if(type==null || type.equals("greeting")) {
			resultObject="안녕하세요!";
		}else if(type.equals("date")) {
			resultObject=new java.util.Date();
		}else {
			resultObject="Invalid Type";//오류문자열 저장
		}
		//4.처리결과->simpleview.jsp로 전송->화면에 출력
		request.setAttribute("result", resultObject);//데이터를 공유->jsp에 전달
		//<c:set var="result" value="${resultObject}" scope="request" />
		//5.forward 액션태그를 사용X->forward시켜주는 객체가 필요(페이지 정보)
		RequestDispatcher dispatcher=
				                      request.getRequestDispatcher("/simpleview.jsp");
		//dispatcher->이동할 페이지정보를 가지게 되는 객체
		//forward->데이터를 공유시키면서 페이지를 이동시키는 메서드
		dispatcher.forward(request, response);
	}
	
	
	
	
	
	
	
	
	
	
}
