<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
  <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flyway</title>
</head>
<body>
<a href=HomePage.jsp style="color:green;text-decoration:none ;font-size:35px;font-weight:bold;">FlyAway</a>
<br><br>
<%
	@SuppressWarnings("unchecked")
	HashMap<String,String> user=(HashMap<String,String>)session.getAttribute("user");
	if(user==null){
		response.sendRedirect("UserPage.jsp");
	}
%>
<p align="center"  style="color:cyan;font-size:40px;font-weight:bold"> Successfully Flight Booked</p>
<p align="center"  style="color:grey;font-size:70px;font-weight:bold,italic"> Thank you</p>
</body>
</html>