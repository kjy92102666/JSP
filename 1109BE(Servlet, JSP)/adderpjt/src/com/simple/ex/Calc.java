package com.simple.ex;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/calc")
public class Calc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.getWriter().append("Served at: ")
//		.append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");					
		response.setContentType("text/html; charset=UTF-8");	//문서가 뭔
		response.setCharacterEncoding("UTF-8");

		//response니까 화면에 출력한다.
		String x_ = request.getParameter("x");//클라이언트에서 날라오는 모든건 request에서 처리.
		String y_ = request.getParameter("y");//클라이언트에서 날라오는 모든건 request에서 처리.
		String op = request.getParameter("operator");
		
		int x = 0;
		int y = 0;
		int result = 0;
		double div =0.0;
		
		if(!x_.equals("")) {//값이 있으면, 값이 없는걸 받을 순 없으니까
			x = Integer.parseInt(x_);
		}
		
		if(!y_.equals("")) {
			y = Integer.parseInt(y_);
		}
		
		if(op.equals("덧셈")) {	
			//이 "덧셈"이 깨져서 인식이 안됐기 때문에 캐릭터셋 맞춰주는 설정을 해줘야한다.
			result = x + y;
			response.getWriter().print("결과 값 : "+result);
		}else if(op.equals("뺄셈")){
			result = x - y;
			response.getWriter().print("결과 값 : "+result);
		}else if(op.equals("곱셈")){
			result = x * y;
			response.getWriter().print("결과 값 : "+result);
		}else if(op.equals("나눗셈")){
			div = (x*1.0d) / (y*1.0d);
			response.getWriter().print("결과 값 : "+Math.round(div * 100) / 100.0);
		}
		
		//result = x - y;
		//response.getWriter().print("결과 값 : "+result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST Called!!");
		doGet(request, response);
	}

}