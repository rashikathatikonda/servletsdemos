package project2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import com.airline.models.Flights;


@WebServlet("/Flight_Selected")
public class Flight_Selected extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Flights> flights = new ArrayList<>();
	Set<String> set=new HashSet<String>();
     Connection connection;
     Statement statement;
     ResultSet resultSet;
    public Flight_Selected() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Resource(name="jdbc/airline")
	DataSource datasource;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flight_id=request.getParameter("flight");
		String Date=request.getParameter("Date");
		String No_of_persons=request.getParameter("No_of_persons");
		PrintWriter out=response.getWriter();
		System.out.println(flight_id);
		try {
			connection=datasource.getConnection();
			statement = connection.createStatement();
					resultSet = statement.executeQuery("select * from flights where id= '"+flight_id+"'");
				
					if (resultSet.next()) {
						
						int id = resultSet.getInt("id");
						String Name = resultSet.getString("Name");
						String Source = resultSet.getString("Source");
						String Destination = resultSet.getString("Destination");
						String Price = resultSet.getString("Price");
						Flights flight = new Flights(id, Name, Source, Destination,Price);
						 
					    flights.add(flight);
					}
					request.setAttribute("flights__", flights);
					request.setAttribute("date", Date);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Details.jsp");
					dispatcher.forward(request, response);
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void destroy() {
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

