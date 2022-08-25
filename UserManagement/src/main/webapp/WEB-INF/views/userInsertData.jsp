<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<center>
<body>
<h1>Registration of User</h1>
<form action="registeruser" method="post">
		<pre>
		Name: <input type="text" name="name" required />
		  Age:<input type="text" name="age" required />
	       Email: <input type="text" name="email" required /><br/>
		<input type="submit" name="Register" /></table>
		</pre>
	</form>
	
	<p><%= request.getAttribute("result") %></p>
	<a href="index.jsp">Home</a>

</body>
</center>
</html>