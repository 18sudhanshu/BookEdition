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
@WebServlet("/BookList")
public class BookList extends HttpServlet {
	private static final String Query="select Id,BookName,BookEdition,BookPrice from stud";
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out= resp.getWriter();
	   
	   try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con=	DriverManager.getConnection("jdbc:mysql://localhost:3306/rajan", "root", "root");
	    PreparedStatement ps= con.prepareStatement(Query);
	    ResultSet rs= ps.executeQuery();
	    out.println("<table border='1' align='center'>");
	    out.println("<tr>");
	    out.println("<th>Id</th>");
	    out.println("<th>Book Name</th>");
	    out.println("<th>Book Edition</th>");
	    out.println("<th>Book Price</th>");
	    out.println("<th>Edit</th>");
	    out.println("<th>Delete</th>");
	    out.println("</tr>");
	    while(rs.next()) {
	    	out.println("<tr>");
	    	out.println("<td>"+rs.getInt(1)+"</td>");
	    	out.println("<td>"+rs.getString(2)+"</td>");
	    	out.println("<td>"+rs.getString(3)+"</td>");
	    	out.println("<td>"+rs.getString(4)+"</td>");
	    	out.println("<td><a href='editurl?id="+rs.getInt(1)+"'>Edit</a></td>");
	    	out.println("<td><a href='delete?id="+rs.getInt(1)+"'>Delete</a></td>");
	    	
	    	out.println("</tr>");
	    	
	    }
	    out.println("<table>");
	   
	   
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		out.println("<h1>"+e.getMessage()+"</h2>");
		e.printStackTrace();
	}
	   out.println("<a href=login.html>Home</a>");
	   
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}



