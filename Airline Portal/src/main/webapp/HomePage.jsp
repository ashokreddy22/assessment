<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<a href=HomePage.jsp style="color:green;text-decoration:none ;font-size:35px;font-weight:bold;">FlyAway</a>
<br>
</head>
<body >


<div align="right">
<a href="AdminPage.jsp">Admin Login</a>
</div>

<%
	@SuppressWarnings("unchecked")
	HashMap<String,String> user=(HashMap<String,String>)session.getAttribute("user");
	if(user!=null){
%>
<p>Welcome <%=user.get("name") %></p>
<a href="Logout">Logout</a>
<%
	}else{
%>
<a href="UserPage.jsp">User Login</a>
<%
	}
%>
<br><br>
<center>
<h1>Travel Details</h1>
<div style="border:5px solid blue;width:35%;border-radius:20px;padding:20px" align="center">
<form action="FlightList" method="post">
	<label for="from">From :-</label> <input type="text" name="from" id=from required/><br><br>
	<label for="to">To :-</label> <input type="text" name="to" id="to" required/><br><br>
	<label for="departure">Departure :-</label> <input type="date" name="departure" id="departure" required/><br><br>
<label for="passengers">No.of Passengers :-</label> <input type="number" name="passengers" id="passengers" required/><br><br>

	<input type="submit" value="Search" /> <input type="reset" />
</form>
</div>
</center>
</body>
</html>