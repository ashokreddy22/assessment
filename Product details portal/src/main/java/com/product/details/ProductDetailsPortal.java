package com.product.details;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/productportal")
public class ProductDetailsPortal extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		
		List<Product> data1=(List<Product>) session.getAttribute("productdetails");
		session.setAttribute("products",data1);
			RequestDispatcher rd=request.getRequestDispatcher("/productdetails.jsp");
			rd.forward(request,response);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		int productId=Integer.parseInt(request.getParameter("productid"));
		String productname=request.getParameter("productname");
		double productcost=Double.parseDouble(request.getParameter("productcost"));
		String productstock=request.getParameter("productstock");
		
		Product productdetail= new Product(productId, productname, productcost, productstock);
		List<Product> data=new ArrayList<>();
		data.add(productdetail);
		HttpSession session=request.getSession();
		session.setAttribute("productdetails",data);
		doGet(request,response);
	}

}
