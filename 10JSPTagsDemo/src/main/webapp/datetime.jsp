<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    Time on the server is<%=new java.util.Date() %>
    <br></br>
    <%java.util.Date mydate=new java.util.Date(); %>
    Mydate=<%=mydate %>
    Time on the server is ${mynewdate}
</body>
</html>