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

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
    
    public DeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","Deepthi@02");
		}catch (SQLException e) {
			e.printStackTrace();
		}	 catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String email=request.getParameter("email");
		try (
				Statement statement = connection.createStatement();){
					int result = statement.executeUpdate("delete from user where email='"+email+"'");
					PrintWriter out = response.getWriter();
					if(result>0) {
						out.println("<h1>User Deleted</h1>");
					}
					else
					{
						out.println("<h1>Error Creating</h1>");
					}
					
				}
				catch(SQLException e) {
					e.printStackTrace();
				}				

	}
	public void destroy() {
		try {
			if(connection !=null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

