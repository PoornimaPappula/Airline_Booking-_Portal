package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PaymentDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;      


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 response.setContentType("text/html");
			
		 	PrintWriter pw = response.getWriter();
			String pmode=request.getParameter("paymentmode");
			BookingClass.payment_mode = pmode;  
			try {
				if(pmode.equalsIgnoreCase("card")) 
				{
				 	RequestDispatcher rd30=request.getRequestDispatcher("CardDetailsEnter.html");
					rd30.include(request, response);
				}
				else if(pmode.equalsIgnoreCase("wallet"))
				{
				 	RequestDispatcher rd31=request.getRequestDispatcher("WalletDetailsEnter.html");
					rd31.include(request, response);
				}
				else
				{
				 	RequestDispatcher rd32=request.getRequestDispatcher("UPIDetailsEnter.html");
					rd32.include(request, response);
				}
			}catch (Exception e) {
		System.err.println(e);
	}
}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
