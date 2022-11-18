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
 * Servlet implementation class PlacesListServlet
 */
public class PlacesListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		 response.setContentType("text/html");
			
		    PrintWriter pw=response.getWriter();
	
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway","root","Poori");
			PreparedStatement ps=con.prepareStatement("Select from_location,to_location from flight");
			ResultSet rs = ps.executeQuery();
			pw.println("Master List of Source and Destinations");
			pw.println(""); 
			if(rs.next()) 
			{
				pw.println("<div class='left'><table style='width:100%' align='left'><tr><th>Source</th><th>Destination</th></tr>");
				//pw.println("<div class='tab'><form action='BookFlightServlet' method='post'>");
				do {
						pw.println(""
						+ "<tr><td>"+rs.getString("from_location")+"</td>"
						+ "<td>"+rs.getString("to_location")+"</td></tr>");
			}while(rs.next());
				pw.println("</table></div> <br/><br/><br/><br/>"); 
			}
			else {
				pw.print("No Airlines found....!");
			} 
				ps.close();
				con.close();
			} catch (Exception e) {
				System.err.println(e);
			}
 		RequestDispatcher rd5=request.getRequestDispatcher("AdminList.html");
 		rd5.include(request,response);	
			/* out.print("Click hear to go to <a href='HomePage.html'>Home page </a>"); */
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
