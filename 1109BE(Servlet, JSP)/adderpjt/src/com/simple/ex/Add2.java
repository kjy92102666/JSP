package com.simple.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add2")
public class Add2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String[] names = request.getParameterValues("num");
		//return되는게 배열로 처리 되어야하니까
		int result=0;
		
		PrintWriter out = response.getWriter();
		
		for (int i = 0; i < 4; i++) {
			//names[i]="";
			int num = Integer.parseInt(names[i]);	//4개가 들어있을
			result += num;
		}
		
		out.print("<p> 결과 합 : " + result + "</p>");
	}

}
