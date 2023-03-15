package jdbcbasics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {
 public static void main(String[] args) {
	 Connection connection=null;
	 Statement statement=null;
	 ResultSet rs=null;
	try {
		connection=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","Deepthi@02");
    statement=connection.createStatement();
     rs=statement.executeQuery("select * from account");
	    while(rs.next()) {
	    int accono=rs.getInt(1);
	    String lastname=rs.getString(2);
	    String firstname=rs.getString(3);
	    int  bal=rs.getInt(4);	    	
	    System.out.println(accono+"|"+lastname+"|"+firstname+"|"+bal);
	    } 
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally {
		//cleanup 
	
		try {rs.close();statement.close();connection.close();} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}}
	
}
}

