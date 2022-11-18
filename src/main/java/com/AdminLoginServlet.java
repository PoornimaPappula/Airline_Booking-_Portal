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

import java.io.IOException;
import java.io.PrintWriter;

public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; 
    public static boolean isLoggedIn = false;
    public static String email = "admin";
    public static String password = "admin";

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 response.setContentType("text/html");
		
			
    	PrintWriter out = response.getWriter();

        String email = request.getParameter("uname");
        String pass = request.getParameter("upassword");

        if (email.equals(AdminLoginServlet.email) && pass.equals(AdminLoginServlet.password)){
            isLoggedIn = true;
            out.print("You have Loggedin Succesfully.....!");
   		 	RequestDispatcher rd4=request.getRequestDispatcher("Admin.html");
   		 	rd4.include(request,response);		
        }
        else {
            isLoggedIn = false;
            out.print("Login Failed : Incorrect email or Password");
   		 	RequestDispatcher rd5=request.getRequestDispatcher("AdminLogin.html");
   		 	rd5.include(request,response);	
        }

        //out.close();
    }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
}
}

