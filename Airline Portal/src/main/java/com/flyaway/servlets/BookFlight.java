package com.flyaway.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyaway.service.Dao;

@WebServlet("/BookFlight")
public class BookFlight extends HttpServlet{
private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Dao dao = new Dao();
			List<String[]> users=dao.getConformation();			
			HttpSession session=request.getSession();
			session.setAttribute("users", users);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("ConformList.jsp");
	}

}