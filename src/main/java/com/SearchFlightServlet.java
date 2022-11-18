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


public class SearchFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		    PrintWriter pw=response.getWriter();
	      
			SearchClass.no_of_passengers = Integer.parseInt(request.getParameter("number"));

			
			try {
			RequestDispatcher rd = request.getRequestDispatcher("ViewFlights.html");
			rd.include(request, response);	
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway","root","Poori");
			PreparedStatement ps=con.prepareStatement("Select * from flight where from_location=? and to_location=?");
			ps.setString(1,request.getParameter("source"));
			ps.setString(2, request.getParameter("destination"));
			ResultSet rs = ps.executeQuery();
			
			BookingClass.assign_once= "false";
			if(rs.next()) 
			{
				pw.println("<div><p1>Flights Between Station "+request.getParameter("source")+" and "+request.getParameter("destination")+"</p1></div>");
				pw.println("<div class='tab'><table align='left'><tr><th>Flight No</th><th>Date</th>"
						+ "<th>Source</th><th>Destination</th><th>Departure Time</th><th>Arrival Time</th><th>Fare (INR)</th> <th>Airline Name</th> <th>No of Seats</th></tr>");
				//pw.println("<div class='tab'><form action='BookFlightServlet' method='post'>");
				do {
						pw.println(""
						+ "<tr><td>"+rs.getString("airline_id")+"</td>"
						+ "<td>"+rs.getString("date")+"</td>"
					    + "<td>"+rs.getString("from_location")+"</td>"
						+ "<td>"+rs.getString("to_location")+"</td>"
						+ "<td>"+rs.getString("departure_time")+"</td>"
						+ "<td>"+rs.getString("arrival_time")+"</td>"
						+ "<td>"+rs.getString("price")+"</td>"
						+ "<td>"+rs.getString("Airline_Name")+"</td>" 
						+ "<td>"+rs.getString("total_seats")+"</td>" 
			            + "<td>"+"<form action='BookingDetailsServlet'>"
		                + "<input type='hidden' id='date' name='date' value=" + rs.getString("date")+">" 
		                + "<input type='hidden' id='from_location' name='from_location' value=" + rs.getString("from_location") +">" 
		                + "<input type='hidden' id='to_location' name='to_location' value="+rs.getString("to_location")+">" 
		                + "<input type='hidden' id='departure_time' name='departure_time' value=" + rs.getString("departure_time") +">" 
		                + "<input type='hidden' id='arrival_time' name='arrival_time' value=" + rs.getString("arrival_time") +">" 
		                + "<input type='hidden' id='price' name='price' value=" + rs.getString("price") +">"
		                + "<input type='hidden' id='Airline_Name' name='Airline_Name' value=" + rs.getString("Airline_Name") +">"
		                +"<input type='hidden' id='total_seats' name='total_seats' value=" + rs.getString("total_seats") +">"
		                +"<input type='hidden' id='airline_id' name='airline_id' value=" + rs.getString("airline_id") +">"
		                + "<input type='submit' class='submit' value='Book'>"
		                +"</form>"
		                +"</td>"
						+"</tr>");
			}while(rs.next());
				pw.println("</table></div><br/><br/>"); 
			}
			else {
				pw.print("No flights found....!");
			} 
				ps.close();
				con.close();
			} catch (Exception e) {
				System.err.println(e);
			}
			/* out.print("Click hear to go to <a href='HomePage.html'>Home page </a>"); */
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
