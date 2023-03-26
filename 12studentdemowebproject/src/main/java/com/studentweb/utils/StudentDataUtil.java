package com.studentweb.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class StudentDataUtil {
  Connection con=null;
  Statement stmt=null;
  ResultSet rs=null;
  public StudentDataUtil(Connection con) {
	  this.con=con;
  }
  public List<Student> getStudents(){
	  try {
		  stmt=con.createStatement();
		  ResultSet resultSet=stmt.executeQuery("select * from student order by id");
		  while(resultSet.next());{
			  int id=resultSet.getInt("id");
			  String fname=resultSet.getString("first_name");
			  String lname=resultSet.getString("last_name");
			  String email=resultSet.getString("email");
			  Student student=new Student(id,fname,lname,email);
			  students.add(student);
			 }
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }finally {
			 close(con,stmt,rs);
		 }
	  return students;	  
  }
  private void close(Connection con2,Statement stmt2,ResultSet rs2) {
	  try {
		  if(rs !=null) {
			  rs.close();
		  }
		  if(con !=null) {
			  rs.close();
		  }
		  if(stmt !=null) {
			  rs.close();
		  }
	  }catch(SQLException e) {
		  e.printStackTrace();
	  }
  }
}
