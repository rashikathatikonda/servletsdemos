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


@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    public AddUserServlet() {
        super();     
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
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
			
	    int result=statement.executeUpdate("insert into user values('"+firstname+"','"+lastname+"','"+email+"','"+password+"')");   
		 PrintWriter out= response.getWriter(); 
		 if(result>0)
		 {
			 out.println("<h1>User created in DB</h1>");
		 }
		 else {
			 out.println("<h1>Error creating user</h1>"); 
		 }
		}  catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
