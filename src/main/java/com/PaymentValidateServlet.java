package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PaymentValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
	 	PrintWriter pw = response.getWriter();
		try {
			if(BookingClass.payment_mode.equalsIgnoreCase("card")) 
			{
				if((request.getParameter("cnumber").isBlank()) || (request.getParameter("CVV").isBlank()))  
				 {
						pw.println("Please Enter Valid Details......!"+"<br/><br/>");
						RequestDispatcher rd50=request.getRequestDispatcher("CardDetailsEnter.html");
						rd50.include(request, response); 
				 }
				else
				{
					RequestDispatcher rd50=request.getRequestDispatcher("PaymentConfirmation.html");
					rd50.include(request, response);
				}
			}
			else if(BookingClass.payment_mode.equalsIgnoreCase("UPI"))
			{
				if(request.getParameter("UPI").isBlank())  
				 {
						pw.println("Please Enter Valid Details......!"+"<br/><br/>");
						RequestDispatcher rd50=request.getRequestDispatcher("UPIDetailsEnter.html");
						rd50.include(request, response);
				 }
				else
				{
					RequestDispatcher rd50=request.getRequestDispatcher("PaymentConfirmation.html");
					rd50.include(request, response);
				}
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
