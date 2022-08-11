package com.product.details;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.samples.utils.HibernateUtil;

@WebServlet("/productportal")
public class ProductDetailsPortal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Transaction txn = null;
		Session session = null;
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
try {
			String productname = request.getParameter("productname");
			String productcost = request.getParameter("productcost");
			String productstock = request.getParameter("productstock");
			double cost = Double.parseDouble(productcost);
			Product productdetails = new Product(productname, cost, productstock);


			
				session = HibernateUtil.getSessionFactory().openSession();

				txn = session.getTransaction();

				txn.begin();

				session.save(productdetails);

				txn.commit();
				pw.println("<h1>Data Sussessfully entered into a database</h1><br/>");
				pw.println("<a href=\"product.html\">Home</a>");
			

		} catch (Exception ex) {
			if (txn != null) {
				txn.rollback();
			}
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

}
