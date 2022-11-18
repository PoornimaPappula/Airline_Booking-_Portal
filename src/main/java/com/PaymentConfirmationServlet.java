package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentConfirmationServlet
 */
public class PaymentConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html"); 
		 
		 PrintWriter pw=response.getWriter(); 
		 String uOTP = request.getParameter("OTP");
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway","root","Poori");

				
			 if(uOTP.isBlank())  
			 {
					pw.println("Please Enter Valid OTP......!"+"<br/><br/>"); 
					RequestDispatcher rd11=request.getRequestDispatcher("PaymentConfirmation.html");
					rd11.include(request, response);
			 }
			 else 
			 {
				    BookingClass.payment_success = "TRUE";
				    
					BookingClass.booked_seats = SearchClass.no_of_passengers;
					flight.avaialable_seats = SearchClass.totalseats - BookingClass.booked_seats;
					PreparedStatement ps=con.prepareStatement("update flight set total_seats =? where airline_id=?");
					ps.setInt(1,flight.avaialable_seats);
					ps.setInt(2,flight.flight_booking_id);
					ps.executeUpdate();			
					
					pw.println("<h1>"+"Ticket Booked Succesfully......!"+"</h1>"+"<br/><br/>"); 
					 pw.println("<h3>"+"Payment of Rs." + BookingClass.totalamount + "\thas been succesfully done using\t"+BookingClass.payment_mode +"</h3>"+"<br/><br/>");
					 pw.println(SearchClass.no_of_passengers + "  Ticket/Tickets has booked succesfully between  " + SearchClass.usource + "  and  " + SearchClass.udestination
						+"  on date  "+SearchClass.udate		
						+ "<br/><br/>");
						RequestDispatcher rd12=request.getRequestDispatcher("ConfirmationPage.html");
						rd12.include(request, response);  
			 }
			// ps.close(); 
			 con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
