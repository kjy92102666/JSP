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
		//���� UI �� ����� ����.
		String exp = "0";
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("exp")) {
					exp = c.getValue();		//������ ������ exp�� �ִ´�~
					break;
				}
			}			
		}
		
		// ��� ��ü ����
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();	//<html> ��ü�� ��� �ִ� out. �±�,���ڿ� �ϳ��ϳ��� ��ü�� ���� �ѱ��.
		
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Calculation</title>");

		//style�±�
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
		//���� �۾�. form���� post�� ȣ�� �����ϱ� post���� ȣ��Ǳ⿡ ���⿡�� �۾��� �ؾ��Ѵ�
		Cookie[] cookies = request.getCookies();

		String value = request.getParameter("value"); // value�� ����.
		String operator = request.getParameter("operator"); // operator�� ������
		String dot = request.getParameter("dot"); // �Ǽ� ǥ�� .

		String exp = ""; // �����ϴ°� ���� ���ϴ°� �ƴ϶� ��ü�� ������ �����Ǵ°�.

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("exp")) {
					exp = cookie.getValue(); // ����ҿ��� ������ �� exp�� ����.
				}
			}
		}

		// ������ ���
		if (operator != null && operator.equals("=")) { // opertor�� ���� �ְ�, �����ڰ� =�� ������,
			// ����ó�� ������ ����Ѵ�.
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

			// engine.eval(new FileReader("example.js"));

			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// ��ũ��Ʈ ����ó��.
				e.printStackTrace();
			}

		} else {
			// ������ ��Ű�� ����.
			exp += (value == null) ?    "" : value;
			exp += (operator == null) ? "" : operator;
			exp += (dot == null) ?      "" : dot;
		}

		Cookie expCookie = new Cookie("exp", exp);
		
		//��Ű ���� ���.(���α׷� ������ �̹���ۿ� ��) C��ư ���.
		if( (operator != null) && (operator.equals("C")) ) {	
			//c�� ������ �� 0���� �ʱ�ȭ �ȴ�~ ��Ű���� �������ν� C����� �۵��ϴ� ����.
			expCookie.setMaxAge(0);			
		}
		expCookie.setPath(("/calculator"));	//WebProjectSetting�� /(�⺻��Ʈ)�� ���� �Ǿ� ���� ������ ����.
		
		// ���� ��Ű ���� Ŭ���̾�Ʈ pc�� ����
		response.addCookie(expCookie);
		response.sendRedirect("calculator");

	}

}
