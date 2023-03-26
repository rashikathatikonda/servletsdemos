package project2;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.airline.models.Flights;
@WebServlet("/TravelDetailsServlet")
public class TravelDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedstmt;

	public TravelDetailsServlet() {
		super();
	}
   @Resource(name="jdbc/airline")
   DataSource datasource;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Date=request.getParameter("Date");
		String Source=request.getParameter("Source");
		String Destination=request.getParameter("Destination");
		String No_of_persons=request.getParameter("No_of_persons");
	   response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		List<Flights> flights = new ArrayList<>();
		try {
			Connection connection = datasource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from flights where Source= '"+Source+"' and Destination= '"+Destination+"'");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String Name = resultSet.getString("Name");
				String source = resultSet.getString("Source");
				String destination = resultSet.getString("Destination");
				String Price = resultSet.getString("Price");
				System.out.println(Name);
				Flights flight = new Flights(id, Name, Source, Destination,Price);
				 
					    flights.add(flight);
					
			}			
			request.setAttribute("flights__", flights);
			request.setAttribute("Date", Date);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Flight_Selection.jsp");
			dispatcher.forward(request, response);
			}catch(SQLException e) {
			e.printStackTrace();
		}
	}public void destroy() {
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
