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
//
//@WebServlet("/editurl")
//public class EditServlet extends HttpServlet {
//	private static final String query = "update BOOKDATA set BOOKNAME=?,BOOKEDITION=?,BOOKPRICE=? where id=?";
//	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
//		//get printwriter
//		PrintWriter pw = res.getWriter();
//		res.setContentType("text/html");
//		//get the id of record
//		int id = Integer.parseInt(req.getParameter("id"));
//		//get the edit data we want to edit
//		String bookName = req.getParameter("bookName");
//		String bookEdition = req.getParameter("bookEdition");
//		float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
//		//LOAD jdbc driver
//		try {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		}catch(ClassNotFoundException cnf) {
//			cnf.printStackTrace();
//		}
//		//generate conection
//		try(Connection con = DriverManager.getConnection("jdbc:mysql:///book","root","010200");
//				PreparedStatement pst = con.prepareStatement(query)){
//			pst.setString(1, bookName);
//			pst.setString(2, bookEdition);
//			pst.setFloat(3, bookPrice);
//			pst.setInt(4, id);
//			int count  = pst.executeUpdate();
//			if(count==1) {
//				pw.println("<h2>Record is edited Successfully</h2>");
//			}else {
//				pw.println("<h2>Record is not edited Successfully</h2>");
//			}
//			
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
//	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
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
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateBook")
public class EditServlet extends HttpServlet {
    private static final String query = "UPDATE BOOKDATA SET BOOKNAME=?, BOOKEDITION=?, BOOKPRICE=? WHERE ID=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        // Get and log received parameters for debugging
        String idParam = req.getParameter("id");
        String bookName = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");
        String bookPriceParam = req.getParameter("bookPrice");

        System.out.println("ID: " + idParam);
        System.out.println("Book Name: " + bookName);
        System.out.println("Book Edition: " + bookEdition);
        System.out.println("Book Price: " + bookPriceParam);

        // Check if any required parameters are missing or invalid
        if (idParam == null || idParam.trim().isEmpty() || 
            bookName == null || bookName.trim().isEmpty() || 
            bookEdition == null || bookEdition.trim().isEmpty() || 
            bookPriceParam == null || bookPriceParam.trim().isEmpty()) {

            pw.println("<h2>Error: All fields (ID, Name, Edition, Price) are required!</h2>");
            pw.println("<a href='home.html'>Go Back to Home</a>");
            return;
        }

        int id;
        float bookPrice;

        try {
            id = Integer.parseInt(idParam);
            bookPrice = Float.parseFloat(bookPriceParam);
        } catch (NumberFormatException e) {
            pw.println("<h2>Error: ID and Price must be valid numbers!</h2>");
            pw.println("<a href='home.html'>Go Back to Home</a>");
            return;
        }

        // Load JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
            pw.println("<h2>Error: Unable to load database driver!</h2>");
            pw.println("<a href='home.html'>Go Back to Home</a>");
            return;
        }

        // Execute the update query
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///book", "root", "010200");
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, bookName);
            pst.setString(2, bookEdition);
            pst.setFloat(3, bookPrice);
            pst.setInt(4, id);

            int count = pst.executeUpdate();

            if (count == 1) {
                pw.println("<h2>Record edited successfully!</h2>");
            } else {
                pw.println("<h2>Error: Record not found or failed to update.</h2>");
            }

        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h2>Database Error: " + se.getMessage() + "</h2>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h2>Error: " + e.getMessage() + "</h2>");
        }

        pw.println("<a href='home.html'>Go Back to Home</a>");
        pw.println("<br>");
        pw.println("<a href='bookList'>View Book List</a>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        doGet(req, res);
    }
}

