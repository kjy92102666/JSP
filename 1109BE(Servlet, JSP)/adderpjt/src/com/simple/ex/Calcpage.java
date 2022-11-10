package com.simple.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calcpage")
public class Calcpage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
//		out.write("<meta charset='UTF-8'>");도 가능.
		//out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		//out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
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
		out.write("		<form action=\"calc3\" method=\"post\">");
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

}
