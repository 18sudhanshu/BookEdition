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
import java.sql.SQLException;

@WebServlet("/editscreen")
public class EditData extends HttpServlet {
	private static final String Query="update stud set BookName=?,BookEdition=?,BookPrice=? where id=?";
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   PrintWriter out= resp.getWriter();
	   int id=Integer.parseInt(req.getParameter("id"));
	   String bookname= req.getParameter("bookname");
	   String bookedition= req.getParameter("bookedition");
	   String bookprice=req.getParameter("bookprice");
	   
	   try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con=	DriverManager.getConnection("jdbc:mysql://localhost:3306/rajan", "root", "root");
	    PreparedStatement ps= con.prepareStatement(Query);
	    
	    ps.setString(1, bookname);
	    ps.setString(2,bookedition);
	    ps.setString(3,bookprice);
	    ps.setInt(4,id);
	    int count= ps.executeUpdate();
	    if(count>0) {
	    	out.println("Edit successfully");
	    }
	    else {
	    	out.println("Edit not Successfully");
	    }
	   
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		out.println("<h1>"+e.getMessage()+"</h2>");
		e.printStackTrace();
	}
	   out.println("<a href=login.html>Home</a>");
	   //out.println("<a href='BooKList'>show</a>");
	   out.println("<br>");
	   out.println("<a href='BookList'>Book List</a>");
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
