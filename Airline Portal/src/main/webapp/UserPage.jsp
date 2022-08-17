<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body >
<br>
<a href="HomePage.jsp" style="color:green;text-decoration:none ;font-size:35px;font-weight:bold;">FlyAway</a>
<br><br>
<center>
<h2>User Login</h2>
<div style="border:2px solid blue;width:35%;border-radius:20px;padding:20px" align="center">
<form action="UserLogin" method="post" >
    <table >
    <tr>
    <td><label for="email">Email</label><br></td>
    <td><input type="email" name="email" id="email" required/></td>
    </tr>
    <tr>
    <td><label for="pass">Password</label><br></td>
    <td><input type="password" name="password" id="pass" required/></td>
    </tr>
    <tr>
    <td><input type="submit" value="submit" /></td>
    <td><input type="reset" /></td>
    </tr>
    </table>
</form>
</div>
</center>
<br><br>
<br><br>
<i>New User-Create account</i>
<h4><a href="UserRegistration.jsp" style="font-size:25;color:blue;">Create Account</a></h4>

<%
	String message=(String)session.getAttribute("message");
	if(message!=null){
%>
<p style="color:silver;"><%=message %></p>
<%
		session.setAttribute("message", null);
	}
%>
</body>
</html>