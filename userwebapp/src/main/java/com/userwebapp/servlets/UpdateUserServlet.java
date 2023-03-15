package com.userwebapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (Connection	connection=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","Deepthi@02");
	    Statement statement=connection.createStatement();)
		{
			
	    int result=statement.executeUpdate("update user set password=+'"+password+"'where email='"+email+"'");   
		 PrintWriter out= response.getWriter(); 
		 if(result>0)
		 {
			 out.println("<h1>Password Updated</h1>");
		 }
		 else {
			 out.println("<h1>Error updating user password</h1>"); 
		 }
		}  catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
