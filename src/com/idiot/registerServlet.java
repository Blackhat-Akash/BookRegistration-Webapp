//package com.idiot;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/register")
//public class registerServlet extends HttpServlet {
//	private static final String query = "INSERT INTO BOOKDATA(BOOKNAME,BOOKEDITION,BOOKPRICE) VALUES(?,?,?)";
//	@Override
//	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException  {
//		//get printwriter
//		PrintWriter pw = res.getWriter();
//		res.setContentType("text/html");
//		//GET BOOK INFO
//		String bookName = req.getParameter("bookName");
//		String bookEdition = req.getParameter("bookEdition");
//		Float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
//		//LOAD jdbc driver
//		try {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		}catch(ClassNotFoundException cnf) {
//			cnf.printStackTrace();
//		}
//		//generate conection
//		try(Connection con = DriverManager.getConnection("jdbc:mysql:///book","root","010200");
//				PreparedStatement pst = con.prepareStatement(query)){
//			pst.setString(1,bookName);
//			pst.setString(2,bookEdition);
//			pst.setFloat(3,bookPrice);
//			int count = pst.executeUpdate();
//			if(count == 1) {
//				pw.println("<h2>Record is Registered Successfully</h2>");
//			}else {
//				pw.println("<h2>Record is not Registered Successfully</h2>");
//			}
//			
//		}catch(SQLException se) {
//			se.printStackTrace();
//			pw.println("<h1>" + se.getMessage() + "<h2>");
//		}catch(Exception e) {
//			e.printStackTrace();
//			pw.println("<h1>" + e.getMessage() + "<h2>");
//		}
//		pw.println("<a href='home.html'>Home</a>");
//		pw.println("<br>");
//		pw.println("<a href='bookList'>Book List</a>");
//	}
//	@Override
//	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
//		doGet(req,res);
//
//	}
//
//}
//



package com.idiot;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
    private static final String query = "INSERT INTO BOOKDATA(BOOKNAME, BOOKEDITION, BOOKPRICE) VALUES(?, ?, ?)";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // Get PrintWriter
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        // Fetch book details from request parameters
        String bookName = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");
        String bookPriceParam = req.getParameter("bookPrice");

        // Check for missing parameters and display an error if any are missing
        if (bookName == null || bookEdition == null || bookPriceParam == null || bookName.trim().isEmpty() || bookEdition.trim().isEmpty()) {
            pw.println("<h2>Error: Missing required fields.</h2>");
            pw.println("<a href='home.html'>Go to Home</a>");
            return;
        }

        // Parse the book price safely
        float bookPrice;
        try {
            bookPrice = Float.parseFloat(bookPriceParam);
        } catch (NumberFormatException e) {
            pw.println("<h2>Error: Invalid book price format.</h2>");
            pw.println("<a href='home.html'>Go to Home</a>");
            return;
        }

        // Load JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
            pw.println("<h1>Database driver not found.</h1>");
            return;
        }

        // Insert the data into the database
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "010200");
             PreparedStatement pst = con.prepareStatement(query)) {
            
            // Set parameters for the SQL query
            pst.setString(1, bookName);
            pst.setString(2, bookEdition);
            pst.setFloat(3, bookPrice);
            
            int count = pst.executeUpdate();
            
            if (count == 1) {
                pw.println("<h2>Record is Registered Successfully</h2>");
            } else {
                pw.println("<h2>Record was not Registered Successfully</h2>");
            }
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>Database error: " + se.getMessage() + "</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h1>");
        }

        // Provide navigation links
        pw.println("<a href='home.html'>Home</a>");
        pw.println("<br>");
        pw.println("<a href='bookList'>Book List</a>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        doGet(req, res);
    }
}
