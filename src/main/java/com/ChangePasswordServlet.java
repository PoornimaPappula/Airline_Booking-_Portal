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

import com.AdminLoginServlet;

import java.io.IOException;
import java.io.PrintWriter;

public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; 

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
		 response.setContentType("text/html");
		
			
    	PrintWriter out = response.getWriter();

        String cpass = request.getParameter("cpassword");

        if (!AdminLoginServlet.isLoggedIn){
            out.println("You must login first");
    		RequestDispatcher rd5=request.getRequestDispatcher("AdminLogin.html");
    		rd5.include(request,response);	
        }
        else if (cpass.equals("")){
            out.println("Password can't be empty");
    		RequestDispatcher rd5=request.getRequestDispatcher("ChangePassword.html");
    		rd5.include(request,response);	
        }
        else if (AdminLoginServlet.isLoggedIn && !cpass.equals("")){
        	AdminLoginServlet.password = cpass;
            out.println("Password changed successfully");
    		RequestDispatcher rd5=request.getRequestDispatcher("Admin.html");
    		rd5.include(request,response);	
        }
        else {
            out.println("Sorry, Something went wrong");
    		RequestDispatcher rd5=request.getRequestDispatcher("Admin.html");
    		rd5.include(request,response);	
        }
        out.close();

        //out.close();
    }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
}
}

