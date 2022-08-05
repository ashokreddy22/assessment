package productdetails;

import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductDetails
 */
@WebServlet("/details")
public class ProductDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;
public void init(ServletConfig config) {
	
	try {
		System.out.println("ProductDetails.init() method. DB connection created");
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost/db", "root", "Reddy2028");
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("to Post");
		try(PreparedStatement statement = connection.prepareStatement("select* from ProductDetails1 where Productid=?");) {
			int ProductId=Integer.parseInt(request.getParameter("productId"));
			statement.setLong(1,ProductId);
			PrintWriter pw=response.getWriter();
			
			ResultSet results = statement.executeQuery();
			pw.println("<table>");
			pw.println("<tr>");
			pw.println("<th>ProductId</th>\t");
			pw.println("<th>productName</th>\t");
			pw.println("<th>product type</th>\t");
			pw.println("<th>Ram</th>\t\t");
			pw.println("<th>Battery(mah)</th>\t");
			pw.println("<th>cost</th>\t");
			pw.println("<th>stock</th>\t");
			pw.println("</tr>");
		    while(results.next()) {
		    	
		    	pw.println("<tr>");
		    	pw.println("<td>"+results.getInt(1)+"</td>\t");
				pw.println("<td>" + results.getString(2) + "</td>\t");
				pw.println("<td>" + results.getString(3) + "</td>\t");
				pw.println("<td>" + results.getInt(4)+ "</td>\t");
				pw.println("<td>" + results.getInt(5)+ "</td>\t");
				pw.println("<td>" + results.getInt(6)+ "</td>\t");
				pw.println("<td>" + results.getString(7) + "</td>\t");
				
				pw.println("</tr>");
		    }
				response.setContentType("text/html");
				
				pw.println("ProductDetails of ProductId is :"+ProductId);
				
		    	
		    
		    
		    pw.println("</table>");	
		    pw.write("<p><a href=\"product.html\">HomePage</a></p>");
			
	    	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 public void destroy() {
	 try {
		connection.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
 }
}
