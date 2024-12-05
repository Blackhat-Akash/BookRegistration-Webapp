//package com.idiot;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

//@WebServlet("/bookList")
//public class BookListServlet extends HttpServlet {
//	private static final String query = "SELECT ID,BOOKNAME,BOOKEDITION,BOOKPRICE FROM BOOKDATA";
//	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
//		//get printwriter
//		PrintWriter pw = res.getWriter();
//		res.setContentType("text/html");
//		//LOAD jdbc driver
//		try {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		}catch(ClassNotFoundException cnf) {
//			cnf.printStackTrace();
//		}
//		//generate conection
//		try(Connection con = DriverManager.getConnection("jdbc:mysql:///book","root","010200");
//				PreparedStatement pst = con.prepareStatement(query)){
//			ResultSet rs = pst.executeQuery();
//			pw.println("<table border='1' align = 'center'>");
//			pw.println("<tr>");
//			pw.println("<th>Book Id</th>");
//			pw.println("<th>Book Name</th>");
//			pw.println("<th>Book Edition</th>");
//			pw.println("<th>Book Price</th>");
//			pw.println("<th>Edit</th>");
//			pw.println("<th>Delete</th>");
//			pw.println("</tr>");
//			while(rs.next()) {
//				pw.println("<tr>");
//				pw.println("<td>" + rs.getInt(1) +"</td>");
//				pw.println("<td>" + rs.getString(2) +"</td>");
//				pw.println("<td>" + rs.getString(3) +"</td>");
//				pw.println("<td>" + rs.getFloat(4) +"</td>");
//				pw.println("<td><a href='editScreen?id = " + rs.getInt(1)+ "'>Edit</a></td>");
//				pw.println("<td><a href='deleteurl?id = " + rs.getInt(1)+ "'>Delete</a></td>");
//				pw.println("</tr>");
//			}
//			pw.println("</table>");
//			
//		}catch(SQLException se) {
//			se.printStackTrace();
//			pw.println("<h1>" + se.getMessage() + "<h2>");
//		}catch(Exception e) {
//			e.printStackTrace();
//			pw.println("<h1>" + e.getMessage() + "<h2>");
//		}
//		pw.println("<a href='home.html'>Home</a>");
//	}
//	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
//		doGet(req,res);
//
//	}
//
//}



package com.idiot;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
    private static final String query = "SELECT ID, BOOKNAME, BOOKEDITION, BOOKPRICE FROM BOOKDATA";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // Get PrintWriter and set content type
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        // Load JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
            pw.println("<h2>Error loading database driver: " + cnf.getMessage() + "</h2>");
            return;
        }

        // Generate connection and execute query
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///book", "root", "010200");
             PreparedStatement pst = con.prepareStatement(query)) {

            // Execute query
            ResultSet rs = pst.executeQuery();

            // Start table for displaying book details
            pw.println("<table border='1' align='center'>");
            pw.println("<tr>");
            pw.println("<th>Book Id</th>");
            pw.println("<th>Book Name</th>");
            pw.println("<th>Book Edition</th>");
            pw.println("<th>Book Price</th>");
            pw.println("<th>Edit</th>");
            pw.println("<th>Delete</th>");
            pw.println("</tr>");

            // Loop through result set to display each row
            while (rs.next()) {
                pw.println("<tr>");
                pw.println("<td>" + rs.getInt(1) + "</td>");
                pw.println("<td>" + rs.getString(2) + "</td>");
                pw.println("<td>" + rs.getString(3) + "</td>");
                pw.println("<td>" + rs.getFloat(4) + "</td>");
                // Fix: Removed extra space after `id`
                pw.println("<td><a href='editScreen?id=" + rs.getInt(1) + "'>Edit</a></td>");
                // Fix: Ensure that the correct delete URL is used
                pw.println("<td><a href='deleteBook?id=" + rs.getInt(1) + "'>Delete</a></td>");
                pw.println("</tr>");
            }
            pw.println("</table>");

        } catch (SQLException se) {
            // Handle SQL exception
            se.printStackTrace();
            pw.println("<h2>Database Error: " + se.getMessage() + "</h2>");
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            pw.println("<h2>Unexpected Error: " + e.getMessage() + "</h2>");
        }

        // Link to home
        pw.println("<a href='home.html'>Home</a>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        doGet(req, res);
    }
}

