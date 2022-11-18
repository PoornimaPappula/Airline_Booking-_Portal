package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
			
		    PrintWriter pw=response.getWriter();
			RequestDispatcher rd = request.getRequestDispatcher("FlightAddConfirmation.html");
			rd.include(request, response);
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway","root","Poori");
			PreparedStatement ps=con.prepareStatement("insert into flight() values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1,request.getParameter("fid"));
			ps.setString(2,request.getParameter("fdate"));
			ps.setString(3,request.getParameter("ffrom"));
			ps.setString(4,request.getParameter("fto"));
			ps.setString(5,request.getParameter("fdtime"));
			ps.setString(6,request.getParameter("fatime"));
			ps.setString(7,(request.getParameter("fprice")));
			ps.setString(8,(request.getParameter("fseats")));
			ps.setString(9,request.getParameter("fname"));	 
			int action = ps.executeUpdate();
			if(action>0) 
			{
				pw.println("<div style=\"margin-top:78px; margin-left:518px;"+"<h3> Flight has been Added Succesfully.......! </h3></div>");
			}
			else { 
				pw.print("<h3> Flight not added. Please Try Again....!</h3>");
			} 
			ps.close();
			con.close(); 	 
			} catch (SQLException e) {
				pw.println("<h3> Please Enter Valid Deatails....!</h3>");
				System.err.println(e);
			} catch (Exception e) {
				pw.println("<h3> Exception Occured....!</h3>"); 
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			/* out.print("Click hear to go to <a href='HomePage.html'>Home page </a>"); */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
