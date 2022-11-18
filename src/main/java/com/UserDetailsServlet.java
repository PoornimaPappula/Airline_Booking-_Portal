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


public class UserDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;      


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* response.setIntHeader("Refresh", 5); */
		 response.setContentType("text/html");
		
			RequestDispatcher rd2=request.getRequestDispatcher("UserDetails.html");
			rd2.forward(request, response);
			
	/*		String usource=request.getParameter("source");
			String udestination=request.getParameter("destination");
			String udate=request.getParameter("date");
					
			PrintWriter pw = response.getWriter();
			pw.print("Wlcome to database....!");
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway","root","Poori");
			PreparedStatement ps=con.prepareStatement("Select * from flight where from_location=? and to_location=?");
			ps.setString(1,request.getParameter("fromstation"));
			ps.setString(2, request.getParameter("tostation"));
			ResultSet rs = ps.executeQuery();
			if(rs.next()) 
			{
				//RequestDispatcher rd = request.getRequestDispatcher("SearchFlight.html");
				//rd.include(request, response);
				pw.println("<div class='main'><p1 class='menu'>Flights BetWeen Station "+request.getParameter("fromstation")+" and "+request.getParameter("tostation")+"</p1></div>");
				pw.println("<div class='tab'><table><tr><th>Flight Name</th><th>FLight No</th>"
						+ "<th>From Stn</th><th>To Stn</th><th>Seats</th><th>Fare (INR)</th></tr>");
				do {
						pw.println(""
						+ "<tr><td>"+rs.getString("airline_id")+"</td>"
						+ "<td>"+rs.getString("date")+"</td>"
					    + "<td>"+rs.getString("from_location")+"</td>"
						+ "<td>"+rs.getString("to_location")+"</td>"
						+ "<td>"+rs.getString("departure_time")+"</td>"
						+ "<td>"+rs.getString("arrival_time")+"</td>"
						+ "<td>"+rs.getString("price")+"</td>"
						+ "<td>"+rs.getString("total_seats")+" RS</td></tr>");
			}while(rs.next());
				pw.println("</table></div>");
			}
			else {
				pw.print("No database....!");
				//RequestDispatcher rd=request.getRequestDispatcher("SearchFlight.html");
				//rd.forward(request, response);
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
