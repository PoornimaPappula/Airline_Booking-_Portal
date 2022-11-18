package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookingDetailsServlet
 */
public class BookingDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html"); 
		 PrintWriter pw=response.getWriter(); 
		 if(BookingClass.assign_once.equalsIgnoreCase("false"))
		 {
	        SearchClass.udate = request.getParameter("date");
	        SearchClass.usource = request.getParameter("from_location");
	        SearchClass.udestination = request.getParameter("to_location");
	        SearchClass.uarrivaltime = request.getParameter("arrival_time");
	        SearchClass.uairlinename = request.getParameter("Airline_Name");
	        SearchClass.price = Integer.parseInt(request.getParameter("price"));
	        SearchClass.totalseats = Integer.parseInt(request.getParameter("total_seats"));
			flight.flight_booking_id = Integer.parseInt(request.getParameter("airline_id"));
	        BookingClass.totalamount = SearchClass.no_of_passengers * SearchClass.price;
	        BookingClass.assign_once = "true";
		 }
			RequestDispatcher rd12=request.getRequestDispatcher("UserDetails.html");
			rd12.include(request, response);
	        try {
				 int i=1;
					
					//pw.println(""
				   // + "<form action='PaymentDetails.html'>");
				 do 
				 {
					 pw.println("&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; "+"Passenger"+i+" "+ "Deatils" + "<br/><br/>");
					i++;
					pw.println(""
					+ "<tr><td>"
		            + "<td>"+"<form action='PaymentDetails.html'>"
			        + "<label>Salutation:</label>"
					+"<select name='Salutation' style ='border: 4px;padding: 3px;border-radius: 5px;text-align-last:center;width: 205px;'>"
					+ "<option value='Mr'>Mr.</option>"
					+ "<option value='Ms'>Ms.</option>" 
					+ "<option value='Mrs'>Mrs.</option>"
					+"</select>"
					+"<br/>"
			        +"<label>Passenger Name:</label>" 	 	
	                + "<input type='Text' id='text' name='p_name' style ='border: 4px;padding: 3px;border-radius: 5px;text-align-last:center;width: 200px;'required/>" 
					+"<br/>"
			        +"<label>Passenger Age:</label>" 		
					+"<input type='number' name='p_age' value='18' step='1' min='0' max='200' style ='border: 4px;padding: 3px;border-radius: 5px;text-align-last:center;width: 200px;'required/><br/><br/>"
				    +"");
				 }while(i<=SearchClass.no_of_passengers);
				 pw.println(""
						 +"<input type='submit' class='submit' style ='margin-left:175px; border: 4px;padding: 5px;border-radius: 5px;text-align-last:center;width: 150px;' value='Continue to Payment'>"
						 +"</form>"
						 +"</td>"
						 +"</tr>");
			} catch (Exception e) {
				// TODO Auto-generate,l;d catch block
				e.printStackTrace();
			}
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
