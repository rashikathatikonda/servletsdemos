<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String[] places = {"Mumbai","Hyderabad"};
pageContext.setAttribute("places",places);
%>
<%
for(String place: places){
	out.println(place);
}
%>
<p>Using tags</p>
<c:forEach var="place" items="${places}">
--> ${place} <br/>
</c:forEach>

</body>
</html>