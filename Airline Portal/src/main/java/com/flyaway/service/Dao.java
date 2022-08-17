package com.flyaway.service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flyaway.utils.Util;
public class Dao {
	public Connection con=null;
	public Statement st=null;
	public Dao() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost/flyaway", "root", "Reddy2028");
		System.out.println("connection established with database");
		st=con.createStatement();
	}
	
public boolean checkAdmin(String email, String password) {
		
		try {
			//from a table getting a Admin details
			ResultSet rs=st.executeQuery("select *from admindetails where email='"+email+"' and password='"+password+"'");
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
public boolean changeAdminPassword(String email, String password) {

	try {
		//Updating  a  admin password 
		ResultSet rs=st.executeQuery("select * from admindetails where email='"+email+"'");
		if(!rs.next()) {
			return false;
		}
		st.execute("update admindetails set password='"+password+"' where email='"+email+"'");
		return true;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
}
public boolean insertFlight(HashMap<String, String> flight) throws SQLException {
	//insert into a flight details nameof flight,fromf=flightfrom etc...
	String query1 = "INSERT INTO flightdetails (name, fromf, tof, datef, timef, price) VALUES" + " ('"
			+ Util.fixSqlFieldValue(flight.get("name")) + "'," + " '" + Util.fixSqlFieldValue(flight.get("from")) + "'," + " '"
			+ Util.fixSqlFieldValue(flight.get("to")) + "'," + " '" + Util.fixSqlFieldValue(flight.get("date")) + "'," + " '"
			+ Util.fixSqlFieldValue(flight.get("time")) + "'," + " '" + Util.fixSqlFieldValue(flight.get("price")) + "')";
	
	System.out.println(flight.get("date"));
	System.out.println(flight.get("time"));
	try {
		st.execute(query1);
		return true;
	} catch (SQLException e) {
		System.out.print("error");
		e.printStackTrace();
	}
	return false;
}

public boolean insertUser(HashMap<String, String> user) {
//user registration details.
	String query="INSERT INTO usersdetails (email, password, name, phno, adno) values('"+user.get("email")+"','"+user.get("password")+"','"+user.get("name")+"','"+user.get("phno")+"','"+user.get("adno")+"')";                   
	
	try {
		st.execute(query);
		return true;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
}
public HashMap<String, String> checkUser(String email, String password) {
	
	HashMap<String,String> user=null;
	String query="select * from usersdetails where email='"+email+"' and password='"+password+"'";
	try {
		ResultSet rs=st.executeQuery(query);
		if(rs.next()) {
			user=new HashMap<>();
			user.put("name", rs.getString("name"));
			user.put("email",rs.getString("email"));
			user.put("phno",rs.getString("phno"));
			user.put("adno",rs.getString("adno"));
		}
		return user;
	} catch (SQLException e) {
		e.printStackTrace();
	}

	return user;
}
public List<String[]> getAvailableFlights(String f, String t, String d) {
	//getting a flight details of name of flight,source and destination date of flight,time and price.
	List<String[]> flights=new ArrayList<>();
	String query="SELECT * FROM flyaway.flightdetails where fromf='"+f+"' and tof='"+t+"' and datef='"+d+"'";
	try {
		ResultSet rs=st.executeQuery(query);
		
		if(rs.next()) {
			String[] flight=new String[6];
			flight[0]=rs.getString("name");
			flight[1]=rs.getString("fromf");
			flight[2]=rs.getString("tof");
			flight[3]=rs.getString("datef");
			flight[4]=rs.getString("timef");
			flight[5]=rs.getString("price");
			flights.add(flight);
			return flights;
		}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return null;
}

public List<String[]> getConformation() {
	//conformation of details of user.
	List<String[]> users=new ArrayList<>();
	String query="SELECT * FROM flyaway.usersdetails";
	try {
		ResultSet rs=st.executeQuery(query);
		
		if(rs.next()) {
			String[] user=new String[6];
			user[0]=rs.getString("email");
			user[1]=rs.getString("name");
			user[2]=rs.getString("phno");
			user[3]=rs.getString("adno");
			users.add(user);
			return users;
		}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
}


}

