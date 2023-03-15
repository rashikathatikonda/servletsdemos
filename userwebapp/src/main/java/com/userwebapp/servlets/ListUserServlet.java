package com.userwebapp.servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/listusers")
public class ListUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
    
    public ListUserServlet() {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery("select * from user");){
					PrintWriter out = response.getWriter();
					response.setContentType("text/html");
					out.println("<table border=1>");
					out.println("<tr>");
					out.println("<th>First Name</th>");
					out.println("<th>Last Name</th>");
					out.println("<th>Email</th>");
					out.println("</tr>");
					while(result.next()) {
						String firstname=result.getString(1);
						String lastname=result.getString(2);
						String email=result.getString(3);
						out.println("<tr>");
						out.println("<td>"+firstname+"</td>");
						out.println("<td>"+lastname+"</td>");
						out.println("<td>"+email+"</td>");
						out.println("</tr>");				
						}	
					out.println("<table>");
					out.println("<a href=\"listusers\">List Users</a>");
		}catch(SQLException e) {
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

