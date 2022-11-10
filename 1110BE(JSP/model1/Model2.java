package com.simple.ex;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Form�� ���� ���¿��� doGet-model1 ȣ��
 * 
 */
@WebServlet("/model2")
public class Model2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�� 1������ [�ڵ�]
		int num =0;
		String num_="0";	//defaultó��.
		
		num_ = request.getParameter("n");
		
		if((num_ != null && !num_.equals(""))){
			num = Integer.parseInt(num_);
		}else {
			num_="0";
		}
		
		//else �Ⱦ��� ���.
		String result="¦��";
		
		if(num % 2 != 0){
			result = "Ȧ��";
		}
		
		//request ����ҿ� �𵨰�(result)�� ����
		request.setAttribute("result", result);	//�����ϸ� �ٸ��̸� X
		request.setAttribute("num", num_);
		
		//������ �ּұ��� ���� �۾�.
		//Forward�� �Ƿ��� ���� �𵨰��� ������. Forward�� ��ü�� �ʿ�==>dispatcher
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("model1.jsp"); 	//���� ���� ��θ� ()�ȿ� �ۼ�.
		dispatcher.forward(request, response);
	}
	
		//���� �ȿ� ������ �ִ��۾�.
	   protected void doPost(HttpServletRequest request, HttpServletResponse response)
		         throws ServletException, IOException {
		      // TODO Auto-generated method stub
		      doGet(request, response);
		   }

}
