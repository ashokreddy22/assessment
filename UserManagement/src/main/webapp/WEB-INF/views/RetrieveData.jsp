<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<center>
<body>
	<h1>Retrieve Data</h1>
	<form action="retrieveData" method="post">
		<pre>
Id: <input type="text" name="id" required/><br/>
<input type="submit" name="Register" />
		</pre>
	</form>
<p><%= request.getAttribute("userdata") %></p>
	
	
	<a href="index.jsp">Home</a>
</body>
</center>
</html>