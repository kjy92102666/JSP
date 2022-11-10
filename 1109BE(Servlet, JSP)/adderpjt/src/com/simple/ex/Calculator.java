package com.simple.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//계산기 UI 및 결과값 셋팅.
		String exp = "0";
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("exp")) {
					exp = c.getValue();		//수식을 꺼내서 exp에 넣는다~
					break;
				}
			}			
		}
		
		// 출력 객체 설정
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();	//<html> 전체를 담고 있는 out. 태그,문자열 하나하나를 객체로 만들어서 넘긴다.
		
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Calculation</title>");

		//style태그
		out.write("<style>");
		out.write("#content{");
		out.write(" width : 200px;");
		out.write(" margin : 0px auto;");
		out.write("}");
		
		out.write("input{");
		out.write("width: 50px;");
		out.write("	height: 50px;");
		out.write("}");

		out.write(".output{");
		out.write("	height: 50px;");
		out.write("	background: #e9e9e9;");
		out.write("	font-size: 24px;");
		out.write("	font-weight: bold;");
		out.write("	text-align: right;");
		out.write("	padding: 0px 5px;");
		out.write("}");
		out.write("</style>");
		
		out.write("</head>");
		out.write("<body>");
		out.write("	<div id=content>");
		out.write("		<form  method=\"post\">");
		out.write("			<table>");
		out.write("				<tr>");
		out.printf("					<td class=\"output\" colspan=\"4\">%s</td>", exp);
		out.write("				</tr>");
		out.write("				<tr>");
		out.write("					<td><input type=\"submit\" name=\"operator\" value=\"CE\"/></td>");
		out.write("					<td><input type=\"submit\" name=\"operator\" value=\"C\"/></td>");
		out.write("					<td><input type=\"submit\" name=\"operator\" value=\"<-\"/></td>");
		out.write("					<td><input type=\"submit\" name=\"operator\" value=\"/\"/></td>");
		out.write("				</tr>");
		out.write("				<tr>");
		out.write("					<td><input type=\"submit\" name=\"value\" value=\"7\"/></td>");
		out.write("					<td><input type=\"submit\" name=\"value\" value=\"8\"/></td>");
		out.write("					<td><input type=\"submit\" name=\"value\" value=\"9\"/></td>");
		out.write("					<td><input type=\"submit\" name=\"operator\" value=\"*\"/></td>");
		out.write("				</tr>");
		out.write("				<tr>");
		out.write("					<td><input type=\"submit\" name=\"value\" value=\"4\"/></td>");
		out.write("					<td><input type=\"submit\" name=\"value\" value=\"5\"/></td>");
		out.write("					<td><input type=\"submit\" name=\"value\" value=\"6\"/></td>");
		out.write("					<td><input type=\"submit\" name=\"operator\" value=\"-\"/></td>");
		out.write("				</tr>");
		out.write("				<tr>");
		out.write("					<td><input type=\"submit\" name=\"value\" value=\"1\"/></td>");
		out.write("					<td><input type=\"submit\" name=\"value\" value=\"2\"/></td>");
		out.write("					<td><input type=\"submit\" name=\"value\" value=\"3\"/></td>");
		out.write("					<td><input type=\"submit\" name=\"operator\" value=\"+\"/></td>");
		out.write("				</tr>");
		out.write("				<tr>");
		out.write("					<td></td>");
		//value, operator, dot(.)
		out.write("					<td><input type=\"submit\" name=\"value\" value=\"0\"/></td>");
		out.write("					<td><input type=\"submit\" name=\"dot\" value=\".\"/></td>");
		out.write("					<td><input type=\"submit\" name=\"operator\" value=\"=\"/></td>");
		out.write("				</tr>");
		out.write("			</table>");
		out.write("		</form>");
		out.write("	</div>");
		out.write("</body>");
		out.write("</html>");
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//계산기 작업. form으로 post를 호출 했으니까 post으로 호출되기에 여기에서 작업을 해야한다
		Cookie[] cookies = request.getCookies();

		String value = request.getParameter("value"); // value는 숫자.
		String operator = request.getParameter("operator"); // operator는 연산자
		String dot = request.getParameter("dot"); // 실수 표현 .

		String exp = ""; // 누적하는건 값이 변하는게 아니라 객체가 새로이 생성되는것.

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("exp")) {
					exp = cookie.getValue(); // 저장소에서 꺼내온 걸 exp에 대입.
				}
			}
		}

		// 수식을 계산
		if (operator != null && operator.equals("=")) { // opertor가 값이 있고, 연산자가 =을 만나면,
			// 수식처리 엔진을 사용한다.
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

			// engine.eval(new FileReader("example.js"));

			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// 스크립트 예외처리.
				e.printStackTrace();
			}

		} else {
			// 수식을 쿠키에 저장.
			exp += (value == null) ?    "" : value;
			exp += (operator == null) ? "" : operator;
			exp += (dot == null) ?      "" : dot;
		}

		Cookie expCookie = new Cookie("exp", exp);
		
		//쿠키 삭제 기능.(프로그램 내에서 이방법밖에 없) C버튼 기능.
		if( (operator != null) && (operator.equals("C")) ) {	
			//c를 누르면 다 0으로 초기화 된다~ 쿠키값을 지움으로써 C기능을 작동하는 원리.
			expCookie.setMaxAge(0);			
		}
		expCookie.setPath(("/calculator"));	//WebProjectSetting에 /(기본루트)로 설정 되어 있지 않으면 오류.
		
		// 수식 쿠키 값을 클라이언트 pc에 저장
		response.addCookie(expCookie);
		response.sendRedirect("calculator");

	}

}
