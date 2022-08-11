<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><h1>Product Details are:</h1></title>
</head>
<body>

	<table>
	<h1>Product Details are:</h1>
		<tr>
			<th>ProductId</th>
			<th>ProductName</th>
			<th>ProductCost</th>
			<th>Product Stock</th>
		</tr>
		
	<c:forEach var="product" items="${products}">
			<tr>
				<td>${product.productId}</td>
				<td>${product.productname}</td>
				<td>${product.productcost}</td>
				<td>${product.productstock}</td>
			</tr>
			
			
		</c:forEach>
	</table>
	<br/>
<a href="productportal.html">Home</a>
</body>
</html>