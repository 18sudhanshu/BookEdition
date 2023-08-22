package maa.ebook;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/editurl")
public class EditScreen extends HttpServlet {
	private static final String Query="select BookName,BookEdition,BookPrice from stud where id=?";
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out= resp.getWriter();
		int id=Integer.parseInt(req.getParameter("id"));
	   
	   try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con=	DriverManager.getConnection("jdbc:mysql://localhost:3306/rajan", "root", "root");
	    PreparedStatement ps= con.prepareStatement(Query);
	    ps.setInt(1,id);
	    ResultSet rs= ps.executeQuery();
	    rs.next();
		out.println("<form action='editscreen?id="+id+"' method='post'>");
		out.println("<table align='center'>");
		out.println("<tr>");
		out.println("<td> Book Name </td>");
		out.println("<td><input type='text' name='bookname' value='"+rs.getString(1)+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Book Edition </td>");
		out.println("<td><input type='text' name='bookedition' value='"+rs.getString(2)+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Book price </td>");
		out.println("<td><input type='text' name='bookprice' value='"+rs.getString(3)+"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td><input type='submit' value='Edit'></td>");
		out.println("<td><input type='reset' value='cancel'></td>");
		out.println("</tr>");
		out.println("</table>");
		
//		out.println("/form");
	    
	   
	   
	   
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		out.println("<h1>"+e.getMessage()+"</h2>");
		e.printStackTrace();
	}
	   out.println("<a href=login.html>Home</a>");
	  	
	   out.println("<br>");
	   out.println("<a href='BookList'>Book List</a>");
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
