package com.simple.ex;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Form이 없는 상태에서 doGet-model1 호출
 * 
 */
@WebServlet("/model2")
public class Model2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//모델 1에서의 [코드]
		int num =0;
		String num_="0";	//default처리.
		
		num_ = request.getParameter("n");
		
		if((num_ != null && !num_.equals(""))){
			num = Integer.parseInt(num_);
		}else {
			num_="0";
		}
		
		//else 안쓰는 방법.
		String result="짝수";
		
		if(num % 2 != 0){
			result = "홀수";
		}
		
		//request 저장소에 모델값(result)을 저장
		request.setAttribute("result", result);	//가능하면 다른이름 X
		request.setAttribute("num", num_);
		
		//편지에 주소까지 적은 작업.
		//Forward로 피룡한 곳에 모델값을 보내기. Forward할 객체가 필요==>dispatcher
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("model1.jsp"); 	//보낼 곳의 경로를 ()안에 작성.
		dispatcher.forward(request, response);
	}
	
		//편지 안에 내용을 넣는작업.
	   protected void doPost(HttpServletRequest request, HttpServletResponse response)
		         throws ServletException, IOException {
		      // TODO Auto-generated method stub
		      doGet(request, response);
		   }

}
