package com.simple.ex;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");					
		response.setContentType("text/html; charset=UTF-8");	//문서가 뭔
		response.setCharacterEncoding("UTF-8");

		//response니까 화면에 출력한다.
		String value_ = request.getParameter("value");//클라이언트에서 날라오는 모든건 request에서 처리.
		
		String op = request.getParameter("operator");
		
		int value = 0;
		int result = 0;
		double div =0.0;
		
		if(!value_.equals("")) {//값이 있으면, 값이 없는걸 받을 순 없으니까
			value = Integer.parseInt(value_);
		}
		
		/*
		서블렛은 특별히 서블렛을 죽이는 명령을 하지 않는 한, 계속 살아있음.
		고로, 세션이냐 어플리케이션이냐의 차이는 있지만 서블렛에 담게된다.
		*/
		
		// Application 저장소를 획득(객체 획득. 객체 주소를 알아야한다~)
		//ServletContext application = request.getServletContext();	// 저장소 위치를 알려달라~
		HttpSession session = request.getSession();	// 저장소 위치를 알려달라~(Session)
		
		
		//Cookie 선언. 저장되어 있는 값을 불러온다? resp... :쿠키 저장. requ...쿠키 정보를 가져옴.
		Cookie[] cookies = request.getCookies();
		
		
		if(op.equals("=")) {
			//저장소에서 연산자와 값을 꺼낸다.
//			int x = (Integer)session.getAttribute("value");	//value가 들어올떄 Integer(객체)로 들어오기떄문에 받을때, 캐스팅 해줘야한다.
//			int y = value;
//			String operator = (String) session.getAttribute("op");
			
			//쿠키 배열에서 값을 구하기
			int x = 0;
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("value")) {
					x = Integer.parseInt(cookie.getValue());	//map에 key는 1:1구조로 찾지만, 얘는 어디에 있는지 모르는 값을 찾는...?
					break;
				}
			}
			
	
			
			//쿠키 배열에서 연산자 구하기
			int y = value;
			String operator="";
			
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("op")) {
					operator = cookie.getValue();	//map에 key는 1:1구조로 찾지만, 얘는 어디에 있는지 모르는 값을 찾는...?
					break;
				}
			}
			
			if(operator.equals("+")) {
				result = x + y;	
			}else if(operator.equals("-")){
				result = x - y;
			}else if(operator.equals("x")){
				result = x * y;
			}else if(operator.equals("/")){
				double result_=0.0;
				if(y!= 0) {
					result_ = (double)x/y;
				}
				response.getWriter().printf("결과 값 : %.2f\n", result_);
				return;
			}
			
			response.getWriter().printf("결과 값 : %d\n", result);
			
		}else {
			//서버에 application저장소에 연산자와 값을 저장한다. "="아니면 무조건 저장. 얘는 생성되어잇는걸 쓰는거고
//			session.setAttribute("value", value);	//값
//			session.setAttribute("op", op);		//연산자
			
			//얘는 생성자를 생성해서 사용.
			Cookie valueCookie = new Cookie("value", String.valueOf(value));  // String.valueOf(value)); 정수를 문자열로 바꾸는 기능.
			Cookie opCookie = new Cookie("op", op);  // 
			
			//Cookie valueCookie = new Cookie("value", value+"");  
			//중요한 정보는 res,req로 저장.
			response.addCookie(opCookie);
			response.addCookie(valueCookie);
			
			
			//쿠키 유효기간 설정
			opCookie.setMaxAge(24*60*60);		//시*분*초. 하루 설정
			valueCookie.setMaxAge(24*60*60);	//시*분*초. 하루 설정
			
			//쿠키 유효범위 설정(path) 		
			opCookie.setPath("/calc2");			//이 공간 안에서만 볼 수 있게... 약간의 보안
			valueCookie.setPath("/calc2");		//"
			
			response.addCookie(opCookie);
			response.addCookie(valueCookie);
			
			//아주 중요한 redirect. 이전버튼을 대신하는 기능.
			response.sendRedirect("calc2.html"); //calc2.html 이페이지를 호출한다 == directing한다~
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST Called!!");
		doGet(request, response);
	}

}