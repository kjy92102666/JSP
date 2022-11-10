package com.simple.ex;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/calc3")
public class Calc3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//쿠키 선언, 준비
		Cookie[] cookies = request.getCookies();
		
		String value = request.getParameter("value");			//value는 숫자.
		String operator = request.getParameter("operator");		//operator는 연산자
		String dot = request.getParameter("dot");				//실수 표현 .
		//이 3개중에 하나씩 날라온다. 하나만 값이 있고 나머진 null (버튼 하나씩 값이 날라감을 생각하기)
		
		String exp="";			//누적하는건 값이 변하는게 아니라 객체가 새로이 생성되는것.
		// 
		//변수 : 다양한(변한다x) 값을 갖는 것. 
		//값이 변하다(실체가 변하다) ==> mutable ←ㅡ→ immutable(바꿀 수 없는 것)
		
		
		//cookies객체에 getCookies한 값이 제대로 된 값인지 확인!. 
		//쿠키 저장소에서 수식을 꺼낸다.
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("exp")) {
					exp = cookie.getValue();	//저장소에서 꺼내온 걸 exp에 대입.
				}
			}
		}
		
		// 수식을 계산
		if(operator != null && operator.equals("=")) { //opertor가 값이 있고, 연산자가 =을 만나면,
			// 수식처리 엔진을 사용한다.
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			
			
			//engine.eval(new FileReader("example.js"));
			
			try {
			 exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				//스크립트 예외처리.
				e.printStackTrace();
			}
			
		}else {
			// 수식을 쿠키에 저장. 
			exp += (value == null)	? 	"" : value;
			exp += (operator== null)? 	"" : operator;
			exp += (dot == null)	? 	"" : dot;
		}
		
		Cookie expCookie = new Cookie("exp", exp);
		
		// 수식 쿠키 값을 클라이언트 pc에 저장
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");
		
		
		
	}

}
