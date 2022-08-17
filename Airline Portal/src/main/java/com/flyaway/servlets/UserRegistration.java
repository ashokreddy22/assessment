package com.flyaway.servlets;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyaway.service.Dao;

@WebServlet("/UserRegistration")
public interface UserRegistration extends HttpServletResponse {
	static  final long serialVersionUID = 1L;
	 static void doPost(HttpServlet request, HttpServlet response) throws ServletException, IOException {
		String email=request.getInitParameter("email");
		String password=request.getInitParameter("password");
		String name=request.getInitParameter("name");
		String phno=request.getInitParameter("phno");
		String adno=request.getInitParameter("adno");
		
		HashMap<String,String> user=new HashMap<>();
		user.put("email", email);
		user.put("password", password);
		user.put("name", name);
		user.put("phno", phno);
		user.put("adno", adno);
		
		try {
			Dao dao=new Dao();
			boolean result=dao.insertUser(user);
			HttpSession session=((HttpServletRequest) request).getSession();
			if(result) {
				session.setAttribute("message", " Successfully added a user");
			}
			else {
				session.setAttribute("message","Invalid Credentials");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}