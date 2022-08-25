<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body><center>
	<h1>User Update a data</h1>
	<form action="userUpdatedata" method="post">
		<pre>
                  Id: <input type="text" name="id" required />
		Name: <input type="text" name="name" required />
		  Age:<input type="text" name="age" required />
	       Email: <input type="text" name="email" required /><br/>
		<input type="submit" name="Register" />
		</pre>
</form>

	<p><%=request.getAttribute("result")%></p>
	<a href="index.jsp">Home</a>
	</center>
</body>
</html>