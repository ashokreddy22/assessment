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
<center>
<h1>Enter Payment Details</h1>

<div style="border:3px solid blue;width:35%;border-radius:20px;padding:20px" align="center">
<form action=BookFlight.jsp method=post>
	<label for=number>Card Number :-</label> <input type="text" name=number id=number required/><br><br>
	<label for=expire>Expiry :-</label> <input type="date" name=expire id=expire required/><br><br>
	<label for=cvc>CVC :-</label> <input type="text" name=cvc id=cvc required/><br><br>
	<label for=price>Amount :-</label> <input type="text" name=amount id=amount required/><br><br>
	<input type=submit value=PAY />
</form>
</div>
</center>
</body>

</html>